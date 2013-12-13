package com.omp.commons.ssc;

import java.net.*;


/**
 * <p>Title: SyncSignalTester</p>
 *
 * <p>Description: SyncSignal 테스트 예제<br>
 * 수행시 인수가 어떤것이라도 있으면 100개의 메세지를 10번 전송한다.</p>
 *
 * @author pat
 * @version 1.0
 */
public class SyncSignalTester
    implements SyncSignalListener {

    private int count;

    public SyncSignalTester() {
        count   = 0;
    }

    /**
     * syncMessageRecived
     *
     * @param ss SyncSignal
     * @todo Implement this pat.app.ssc.SyncSignalListener method
     */
    public void syncMessageRecived(SyncSignal ss) {
        System.out.println("< " + (this.count++)
            + "'" + ss.getFrom()
            + "' type '" + ss.getType()
            + "' message '" + ss.getMessage() + "'");
        System.out.flush();
    }


    public static void main(String[] args) {
        try {
            SyncSignalTester    sst;
            SyncSignalCaster    ssc;

            System.out.println(
                "메세지 전송시가 아니라면 아무 키나 누르면 멈춥니다.");

            sst = new SyncSignalTester();
            ssc = new SyncSignalCaster("tester"
                , InetAddress.getByName("224.0.0.2")
                , 8888
                , null);
            ssc.addListener(sst);
            ssc.start();

            if (args.length > 0) {
                for (int j = 0; j < 10; j++) {
                    for (int i = 0; i < 100; i++) {
                        ssc.doCast(new SyncSignal("tester", "dummy"
                            , String.valueOf(j * 100 + i)));
                    }
                    Thread.sleep(1000);
                }
            }

            System.in.read();
            ssc.stop();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
