package com.omp.commons.ssc;

import java.io.*;
import java.net.*;
import java.util.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>Title: SyncSignalCaster</p>
 *
 * <p>Description: 동기화 신호 송수신 객체</p>
 *
 *
 * @author pat
 * @version 1.0
 */
public class SyncSignalCaster
    implements Runnable {

    private static final Object STOP_SIGNAL    	= new SyncSignalStop();

    /**
     * <p>Title: SyncSignalDeliver</p>
     *
     * <p>Description: 시그널 수신시, 리스너에게 시그널을
     * 전달하는 전달자.</p>
     *
     * <p>Copyright: Copyright (c) 2005</p>
     *
     * <p>Company: Patracyu</p>
     *
     * @author pat
     * @version 1.0
     */
    public class SyncSignalDeliver
        implements Runnable {

        private SyncSignalListener[]    listenerArray;
        private SyncSignal              ss;

        /**
         * 생성자
         * @param listenerArray SyncSignalListener[] 리스너의 배열
         * @param ss SyncSignal 수신된 시그널
         */
        private SyncSignalDeliver(SyncSignalListener[] listenerArray
            , SyncSignal ss) {
            this.listenerArray  = listenerArray;
            this.ss             = ss;
        }

        public void run() {
            for (int i=0; i<this.listenerArray.length; i++) {
                try {
                    this.listenerArray[i].syncMessageRecived(this.ss);
                } catch (Throwable t) {
                	SyncSignalCaster.this.logger.error(
                        "sync signal delivery fail. (to {" + i + ": " + this.listenerArray[i] + "})", t);
                }
            }
        }
    }

    private static final int    DEFAULT_BUF_SIZE    = 1024;
    private static final int    DEFAULT_BACK_LOG    = 100;

    private Log 						logger;
    private Object                  	syncObj;
    private int                     	recvCount;
    private String                  	name;
    private InetAddress             	group;
    private int                     	port;
    private Thread                  	thread;
    private boolean                 	runFlag;
    private MulticastSocket         	ms;
    private HashSet<SyncSignalListener>	listeners;
    private SyncSignalListener[]    	listenerArray;
    private int                     	bufferSize;


    /**
     * 생성자.
     * TTL은 1, 버퍼 크기는 1024 바이트 액셉트용 백 로그 크기는 100로 지정됩니다.
     * @param name String 캐스터 명
     * @param group InetAddress 멀티 캐스팅 그룹 주소
     * @param port int 포트 번호
     * @throws IOException 멀티캐스트 데이터그램 바인딩 실패시
     */
    public SyncSignalCaster(String name, InetAddress group, int port)
        throws IOException {
        this(name, group, port, null, 1, DEFAULT_BUF_SIZE, DEFAULT_BACK_LOG);
    }


    /**
     * 생성자
     * TTL은 1, 버퍼 크기는 1024 바이트 액셉트용 백 로그 크기는 100로 지정됩니다.
     * @param name String 캐스터 명
     * @param group InetAddress 멀티 캐스팅 그룹 주소
     * @param port int 포트 번호
     * @param bindAddress InetAddress 소켓 바인드 어드레스
     * @throws IOException 멀티캐스트 데이터그램 바인딩 실패시
     */
    public SyncSignalCaster(String      name,
                            InetAddress group,
                            int         port,
                            InetAddress bindAddress)
        throws IOException {
        this(name, group, port, bindAddress, 1, DEFAULT_BUF_SIZE
            , DEFAULT_BACK_LOG);
    }


    /**
     * 생성자
     * @param name String 캐스터 명
     * @param group InetAddress 멀티 캐스팅 그룹 주소
     * @param port int 포트 번호
     * @param bindAddress InetAddress 소켓 바인드 어드레스
     * @param ttl int 멀티 캐스트 데이터 그램의 time to live
     * @param bufferSize int 메세지의 송 수신에 사용할 최대 버퍼 싸이즈(바이트)
     * @param backLog int 동기화 신호 전달자의 수신 버퍼 여유 크기
     * @throws IOException 멀티캐스트 데이터그램 바인딩 실패시
     */
    public SyncSignalCaster(String      name,
                            InetAddress group,
                            int         port,
                            InetAddress bindAddress,
                            int         ttl,
                            int         bufferSize,
                            int         backLog)
        throws IOException {

    	this.logger			= LogFactory.getLog(this.getClass());
        this.syncObj        = new Object();
        this.recvCount      = 0;
        if (name == null) {
            throw new IllegalArgumentException("name can't be null");
        }
        this.name           = name;
        this.group          = group;
        if (port <= 0) {
            throw new IllegalArgumentException("port number must positive number.");
        }
        this.port           = port;
        if (bufferSize <= 0) {
            throw new IllegalArgumentException("bufferSize must positive number.");
        }
        this.bufferSize     = bufferSize;
        if (bindAddress == null) {
            this.ms             = new MulticastSocket(this.port);
        } else {
            this.ms             = new MulticastSocket(
                new InetSocketAddress(bindAddress, this.port));
        }

        this.ms.joinGroup(this.group);
        if (backLog <= 0) {
            throw new IllegalArgumentException(
                "backLog must positive number.");
        }
        this.ms.setReceiveBufferSize(bufferSize * backLog);
        this.ms.setTimeToLive(ttl);
        this.listeners      = new HashSet<SyncSignalListener>();
        this.listenerArray  = new SyncSignalListener[0];
    }


    /**
     * 리슨 서비스를 시작합니다.
     */
    public void start() {
        synchronized (this.syncObj) {
            if (this.runFlag) {
                throw new IllegalAccessError("start already.");
            } else  {
                this.thread = new Thread(this, "SyncSignalListenThread");
                this.thread.setDaemon(true);
                this.runFlag    = true;
                this.thread.start();
            }
        }
    }

    /**
     * 리슨 서비스를 종료합니다.
     */
    public void stop() {
        synchronized (this.syncObj) {
            if (this.runFlag) {
                this.runFlag    = false;
                try {
                    this.doCast(new SyncSignal(this.name
                        , SyncSignalCaster.STOP_SIGNAL
                        , SyncSignalCaster.STOP_SIGNAL));
                } catch (IOException ignore) {};
            } else {
                throw new IllegalAccessError("service not running.");
            }
        }
        while (this.thread.isAlive()) {
            try {
                this.thread.join(60000 * 5);
            } catch (InterruptedException e) {
            	throw new RuntimeException("ssc join interrupted.", e);
            }
            if (this.thread.isAlive()) {
            	throw new RuntimeException("ssc join timeout (over 5 mins)");
            }
        }
        this.thread = null;
    }

    /**
     * 리슨 서비스 수행 메쏘드
     */
    public void run() {
        if (Thread.currentThread() != this.thread) {
            return;
        }
        byte[]          buf;
        DatagramPacket  dpk;

        buf = new byte[this.bufferSize + 1];
        dpk = new DatagramPacket(buf, buf.length);
        while (this.runFlag) {
            try {
                SyncSignal              ss;
                ByteArrayInputStream    bi;
                ObjectInputStream       oi;

                // 데이터 그램 수신
                this.ms.receive(dpk);

                // 데이터 그램이 한번에 수신되지 않았거나 버퍼 크기보다 크게 수신되었을때
                // 무시
                if (dpk.getOffset() > 0 || dpk.getLength() > this.bufferSize) {
                    this.logger.warn(
                        "signal ignored. cause sygnal message to long."
                        + "( from: " + dpk.getAddress()
                        + " offset: " + dpk.getOffset()
                        + " recvSize: " + dpk.getLength()
                        + ")");
                    continue;
                }

                // 데이터 그램의 내용을 SyncSignal로 변환
                bi  = new ByteArrayInputStream(dpk.getData()
                    , 0, dpk.getLength());
                try {
                    oi = new ObjectInputStream(bi);
                    ss  = (SyncSignal)oi.readObject();
                } finally {
                    bi.close();
                }

                String  ssTo;

                // SyncSignal의 수신처가 모두가 아니거나 이 캐스터의 이름과 일치하지
                // 않으면 무시
                ssTo    = ss.getTo();
                if (ssTo != null && !ssTo.equals(SyncSignalCaster.this.name)) {
                    continue;
                }

                // 종료 메세지 검사
                if (ss.getType() instanceof SyncSignalStop) {
                    break;
                }

                // 배달 쓰래드 실행
                Thread  deliverThread;

                deliverThread   = new Thread(
                    new SyncSignalDeliver(this.listenerArray, ss),
                    "SyncSignalDeliveryThread" + (++this.recvCount));
                deliverThread.setDaemon(true);
                deliverThread.start();
            } catch (Throwable t) {
                this.logger.error("sync signal recive fail.", t);
            }
        }
    }

    /**
     * 동기화 메세지를 캐스팅 합니다.
     * @param ss SyncSignal 동기화 메세지
     */
    public void doCast(SyncSignal ss)
        throws IOException {
        byte[]                  data;
        DatagramPacket          dpk;
        ObjectOutputStream      oo;
        ByteArrayOutputStream   bo;

        bo  = new ByteArrayOutputStream();
        try {
            oo  = new ObjectOutputStream(bo);
            try {
                ss.setFrom(this.name);
                oo.writeObject(ss);
                oo.flush();
                data = bo.toByteArray();
                if (data.length > this.bufferSize) {
                    throw new IOException(
                        "전송할 메세지의 크기가 허용된 범위(" + this.bufferSize
                        + "Bytes)를 넘었습니다.");
                }
                dpk = new DatagramPacket(data, 0, data.length, this.group,
                    this.port);
                synchronized (this.syncObj) {
                    this.ms.send(dpk);
                }
            } finally {
                oo.close();
            }
        } finally {
            bo.close();
        }
    }

    /**
     * 동기화 메세지 리스너를 추가 합니다.
     * @param ssl SyncSignalListener
     */
    public void addListener(SyncSignalListener ssl) {
        synchronized (this.listeners) {
            SyncSignalListener[]    newListenerArray;

            this.listeners.add(ssl);
            newListenerArray    = new SyncSignalListener[this.listeners.size()];
            this.listeners.toArray(newListenerArray);
            this.listenerArray  = newListenerArray;
        }
    }

    /**
     * 동기화 메세지 리스너를 삭제합니다.
     * @param ssl SyncSignalListener
     * @return boolean 삭제여부
     */
    public boolean removeListener(SyncSignalListener ssl) {
        boolean                 rv;

        synchronized (this.listeners) {
            SyncSignalListener[]    newListenerArray;


            rv  = this.listeners.remove(ssl);
            if (rv) {
                newListenerArray    = new SyncSignalListener[this.listeners.size()];
                this.listeners.toArray(newListenerArray);
                this.listenerArray  = newListenerArray;
            }
        }
        return rv;
    }

    /**
     * 동기화 메세지 캐스터의 이름을 반환합니다.
     * @return String
     */
    public String getName() {
        return this.name;
    }
}
