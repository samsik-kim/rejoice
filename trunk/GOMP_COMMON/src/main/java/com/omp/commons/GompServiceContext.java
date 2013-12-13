package com.omp.commons;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.nio.channels.FileLock;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.TreeSet;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;

import com.omp.commons.commcode.CacheCommCode;
import com.omp.commons.communicate.CommunicatorFactory;
import com.omp.commons.persistence.dao.CommonDAO;
import com.omp.commons.persistence.dao.DaoConfig;
import com.omp.commons.ssc.SyncSignal;
import com.omp.commons.ssc.SyncSignalCaster;
import com.omp.commons.ssc.SyncSignalListener;
import com.omp.commons.text.LocalizeMessage;
import com.omp.commons.util.GLogger;
import com.omp.commons.util.config.ConfigProperties;


/**
 * 서비스에 필요한 초기화 기본 객체들
 * @author pat
 *
 */
public class GompServiceContext {
	

	/**
	 * 임시 파일 청소기
	 * @author pat
	 *
	 */
	private class TempfileCleaner
		implements Runnable {
		
		private Thread		thread;
		private boolean		runflag;
		private File		baseDir;
		private Log			logger;
		private FileFilter	fileFilter;
		private long		term;
		
		private TempfileCleaner(File baseDir, FileFilter fileFilter, long term) {
			this.thread		= new Thread(this, "TempfileCleanerThread");
			this.runflag	= true;
			this.baseDir	= baseDir;
			this.fileFilter	= fileFilter;
			this.logger		= LogFactory.getLog(this.getClass());
			this.term		= term;
			
			this.thread.setDaemon(true);
			this.thread.start();
		}
		
		@Override
		public void run() {
			if (Thread.currentThread() != this.thread) {
				return;
			}
			if (this.logger.isInfoEnabled()) {
				this.logger.info("TempfileCleaner " + this.baseDir + " worker start.");
			}
			try {
				do {
					this.doClean(this.baseDir, System.currentTimeMillis() - this.term, this.fileFilter);
					synchronized (this) {
						this.wait(1000 * 60 * 60);
					}
				} while (this.runflag);
			} catch (InterruptedException e) {
				throw new RuntimeException("TempfileCleaner " + this.baseDir + " thread interruped", e);
			}
			if (this.logger.isInfoEnabled()) {
				this.logger.info("TempfileCleaner " + this.baseDir + " worker stop.");
			}
		}
		
		private void doClean(File tdir, long baseTm, FileFilter ff) {
			File[]	files;
			
			if (ff != null) {
				files	= tdir.listFiles(ff);
			} else {
				files	= tdir.listFiles();
			}
			if (files == null) {
				return;
			}
			for (File file : files) {
				if (file.isDirectory()) {
					doClean(file, baseTm, ff);
				} else {
					if (file.lastModified() < baseTm) {
						file.delete();
						if (this.logger.isDebugEnabled()) {
							Date	lmd;
							
							lmd	= new Date(file.lastModified());
							if (this.logger.isDebugEnabled()) {
								this.logger.debug("old temp file " + file + " (lastmodified at " + lmd + ") deleted.");
							}
						}
					}
				}
			}
		}
		
		private void stop() {
			this.runflag	= false;
			synchronized (this) {
				this.notify();
			}
		}
	}

	private class SyncSignalGompServiceContextListener
		implements SyncSignalListener {
		
		private Log	logger	= LogFactory.getLog(this.getClass());

		@Override
		public void syncMessageRecived(SyncSignal ss) {
			String	signalType;
			
			signalType	= (String)ss.getType();
			if ("config".equals(signalType)) {
				if (this.logger.isDebugEnabled()) {
					this.logger.debug("conf update signal recived.");
				}
				GompServiceContext.this.conf.setNeedUpdate();
				GompServiceContext.this.conf	= new ConfigProperties();
				synchronized (GompServiceContext.this.configLoadHolder) {
					GompServiceContext.this.configLoadHolder[0]	= true;
					GompServiceContext.this.configLoadHolder.notifyAll();
				}
			} else if ("code".equals(signalType)) {
				if (this.logger.isDebugEnabled()) {
					this.logger.debug("code update signal recived.");
				}
				CacheCommCode.initialize(GompServiceContext.this.conf);
				synchronized (GompServiceContext.this.codeLoadHolder) {
					GompServiceContext.this.codeLoadHolder[0]	= true;
					GompServiceContext.this.codeLoadHolder.notifyAll();
				}
			}
		}
	}
	
	private ConfigProperties		conf;
	private List<TempfileCleaner>	cleaners	= new ArrayList<GompServiceContext.TempfileCleaner>();
	private SyncSignalCaster		ssc;
	private boolean					daoInited;
	private boolean[]				configLoadHolder;
	private boolean[]				codeLoadHolder;
	
	public GompServiceContext(String confResourcePath) {
		ConfigProperties 	conf;

		// config 초기화
		System.out.println("### Config Initializing.");
		try {
			ConfigProperties.doInitialize(confResourcePath);
			conf		= new ConfigProperties();
			this.conf	= conf;
		} catch (Exception e) {
			throw new RuntimeException("config initialize fail.", e);
		}
		
		// 임시작업폴더 생성
		System.out.println("### Storige Initializing.");
		try {
			File	tdir;
			File	odir;
			
			// 임시 폴더 루트 존재 검사
			tdir	= new File(conf.getString("omp.common.path.temp.root"));
			if (!tdir.isDirectory()) {
				throw new FileNotFoundException("temp root " + tdir.getAbsolutePath() + " is not exist or not directory.");
			}
			// 내부 공유폴더 존재 검사
			tdir	= new File(conf.getString("omp.common.path.share.base"));
			if (!tdir.isDirectory()) {
				throw new FileNotFoundException("internal share base " + tdir.getAbsolutePath() + " is not exist or not directory.");
			}
			// 서비스 공유폴더 존재 검사
			tdir	= new File(conf.getString("omp.common.path.http-share.base"));
			if (!tdir.isDirectory()) {
				throw new FileNotFoundException("service share base " + tdir.getAbsolutePath() + " is not exist or not directory.");
			}
			
			// 해당 서버의 임시 작업 폴더 생성
			tdir	= new File(conf.getString("omp.common.path.temp.base"));
			tdir.mkdirs();
			
			// 해당 서버의 임시 1회용 자료 공유 폴더 생성
			odir	= new File(conf.getString("omp.common.path.share.once"));
			odir.mkdirs();
		} catch (Exception e) {
			throw new RuntimeException("storige initialize fail.", e);
		}

		
		// log 디렉토리 초기화
		System.out.println("### Log dir Initializing.");
		try {
			File	tdir;
			
			tdir	= new File(conf.getString("omp.common.path.log.service.area"));
			if (!tdir.isDirectory()) {
				throw new FileNotFoundException("service log area base " + tdir.getAbsolutePath() + " is not exist ore not directory.");
			}
			if (!tdir.canWrite()) {
				throw new IOException("service log area base " + tdir.getAbsolutePath() + " is not permited write.");
			}
			
			tdir	= new File(conf.getString("omp.common.path.log.onm.area"));
			if (!tdir.isDirectory()) {
				throw new FileNotFoundException("onm log area base " + tdir.getAbsolutePath() + " is not exist ore not directory.");
			}
			if (!tdir.canWrite()) {
				throw new IOException("onm log area base " + tdir.getAbsolutePath() + " is not permited write.");
			}
		} catch (Exception e) {
			throw new RuntimeException("Log dir initialize fail.", e);
		}

		// log4j 초기화
		System.out.println("### Log4J Initializing.");
		try {
			PropertyConfigurator.configure(conf.export("log4j."));
		} catch (Exception e) {
			throw new RuntimeException("log4j initialize fail.", e);
		}
		
		// 미지정 지역화 메세지 여부 설정
		LocalizeMessage.setCollectUndefinedMessages("yes".equals(this.conf.getString("omp.common.switch.undefined-localize-message")));
		
	}

	/**
	 * 서비스 자원을 초기화 합니다.
	 * 코드 캐쉬 합니다.
	 * @param 사용하는 어플리케이션 식별자
	 */
	public void initServiceResource(String appId) {
		this.initServiceResource(appId, true);
	}
	
	
	/**
	 * 서비스 자원을 초기화 합니다.
	 * @param 사용하는 어플리케이션 식별자
	 * @param 코드캐쉬여부
	 */
	public void initServiceResource(String appId, boolean isCodeCache) {
		this.configLoadHolder	= new boolean[] {false};
		this.codeLoadHolder		= new boolean[] {false};
		
		// SyncSignalCaster 초기화
		System.out.println("### SyncSignalCaster Initializing.");
		try {
			String		work;
			InetAddress	joinAddress;
			int			bindPort;
			InetAddress	bindAddress;
			long		sscStartTm;
			
			work	= this.conf.getString("omp.common.module.ssc.join.address");
			if (StringUtils.isEmpty(work)) {
				throw new RuntimeException("config 'omp.common.module.ssc.join.address' must set for ssc join address.");
			}
			joinAddress	= InetAddress.getByName(work);
			work	= this.conf.getString("omp.common.module.ssc.bind.port");
			if (StringUtils.isEmpty(work)) {
				throw new RuntimeException("config 'omp.common.module.ssc.bind.port' must set for ssc bind port.");
			}
			bindPort	= Integer.parseInt(work);
			work	= this.conf.getString("omp.common.module.ssc.bind.address");
			if (StringUtils.isEmpty(work)) {
				bindAddress	= null;
			} else {
				bindAddress	= InetAddress.getByName(work);
			}
			// ttl 1 bufsize 2048 backlog 100
            this.ssc = new SyncSignalCaster(this.conf.getString("omp.common.id.unique") + "." + appId
            	, joinAddress, bindPort, bindAddress, 1, 2048, 100);
            this.ssc.addListener(new SyncSignalGompServiceContextListener());
            this.ssc.start();
            
    		sscStartTm	= System.currentTimeMillis() + 10000;
			this.ssc.doCast(new SyncSignal(this.ssc.getName(), "config", "reload"));
			synchronized (this.configLoadHolder) {
				while (!this.configLoadHolder[0]) {
					this.configLoadHolder.wait(1000);
					if (sscStartTm < System.currentTimeMillis()) {
						throw new RuntimeException("SyncSignalCaster '" + this.ssc.getName() + "' test cast fail.");
					}
				}
			}
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException("SyncSignalCaster initialize fail.", e);
		}
		
		
		// DaoConfig 초기화
		System.out.println("### DaoConfig Initializing.");
		try {
			Properties daoProps;

			daoProps = conf.export(null);
			DaoConfig.initialize(daoProps);
			this.daoInited	= true;
		} catch (Exception e) {
			throw new RuntimeException("DaoConfig initialize fail.", e);
		}
		
		// Communicator Factory 초기화
		System.out.println("### CommunicatorFactory Initializing.");
		try {
			CommunicatorFactory.initialize(conf);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("CommunicatorFactory initialize fail.", e);
		}
		System.out.println("### CommunicatorFactory Initialized.");
		
		if (isCodeCache) {
			// common code 초기화
			System.out.println("### CommonCode Initializing.");
			try {
				long		sscStartTm;
				
	    		sscStartTm	= System.currentTimeMillis() + (60000 * 5);
				this.ssc.doCast(new SyncSignal(this.ssc.getName(), "code", "reload"));
				synchronized (this.codeLoadHolder) {
					while (!this.codeLoadHolder[0]) {
						if (sscStartTm < System.currentTimeMillis()) {
							throw new RuntimeException("CommonCode initialize time out. (over 5 mins.)");
						}
						this.codeLoadHolder.wait(1000);
					}
				}
			} catch (Exception e) {
				throw new RuntimeException("CommonCode initialize fail.", e);
			}
		}
	}
	
	
	public ConfigProperties getConfig() {
		return conf;
	}
	
	public void addTempCleaner(File baseDir, FileFilter fileFilter, long term) {
		synchronized(this.cleaners) {
			this.cleaners.add(new TempfileCleaner(baseDir, fileFilter, term));
		}
	}
	
	/**
	 * 사용한 자원 정리
	 */
	public void destory() {
		RuntimeException	destoryFail	= null;
		
		// 데이터소스 정지
		if (this.daoInited) {
			System.out.println("### DataSource Closing.");
			try {
				((CommonDAO)DaoConfig.getDaoManager().getDao(CommonDAO.class)).closeDataSource();
			} catch (SQLException ignore) {
			} catch (Exception e) {
				destoryFail	= new RuntimeException("DataSource close fail", e);
			}
		}
		
		// SSC 정지
		if (this.ssc != null) {
			System.out.println("### SyncSignalCaster stoping.");
			try {
				this.ssc.stop();
			} catch (Exception e) {
				destoryFail	= new RuntimeException("SyncSignalCaster stop fail", e);
			}
		}
		
		// 클리너 종료
		if (this.cleaners.size() > 0) {
			System.out.println("### Temp file cleaners stoping.");
			try {
				synchronized(this.cleaners) {
					Iterator<TempfileCleaner> itr;
					
					itr	= this.cleaners.iterator();
					while (itr.hasNext()) {
						itr.next().stop();
					}
					this.cleaners.clear();
				}
			} catch (Exception e) {
				destoryFail	= new RuntimeException("Temp file cleaners stop fail", e);
			}
		}
		
		// 수집된 미정의 지역화 메세지 저장
		try {
			if (LocalizeMessage.isCollectUndefinedMessages()) {
				File					tdir;
				Iterator<Locale>		itr;
				Map<Locale, Properties>	udmMap;
				Properties				udmAll;
				File					udmAllFile;
				FileOutputStream		udmAllFlkFos;
				String					comment;
				
				System.out.println("### Undefined localized messages saving.");
				tdir			= new File(conf.getString("omp.common.path.undefined-localize-message"));
				tdir.mkdirs();
				comment			= "Writed by " + conf.getString("omp.common.id.unique");
				udmAll			= new Properties();
				udmAllFile		= new File(tdir, "UndefinedMessages_ALL.properties");
				udmAllFlkFos	= new FileOutputStream(new File(tdir, "UndefinedMessages_ALL.properties.lock"));
				try {
					FileLock	udmAllFlk;
					
					udmAllFlk	= udmAllFlkFos.getChannel().lock();
					try {
						FileInputStream			udmAllFis;
						
						//모든 지역 미정의 메세지 원본 읽기
						try {
							udmAllFis	= new FileInputStream(udmAllFile);
							try {
								udmAll.load(udmAllFis);
							} finally {
								udmAllFis.close();
							}
						} catch (FileNotFoundException ignore) {
						}
						
						udmMap	= LocalizeMessage.getUndefinedMessagesMap();
						// 지역별 미정의 메세지 저장
						itr		= udmMap.keySet().iterator();
						while (itr.hasNext()) {
							Locale				loc;
							Properties			props;
							
							loc		= itr.next();
							props	= udmMap.get(loc);
							if (props.size() > 0) {
								File				tfile;
								
								tfile	= new File(tdir, "UndefinedMessages_" + loc.toString() + ".properties");
								if (tfile.exists()) {
									Properties		storedProps;
									FileInputStream	fis;
									
									storedProps	= new Properties();
									fis			= new FileInputStream(tfile);
									try {
										storedProps.load(fis);
									} finally {
										fis.close();
									}
									storedProps.putAll(props);
									props	= storedProps;
								}
								
								storeNameSortPropperties(tfile, props, comment);
								udmAll.putAll(props);
								System.out.println("#### Undefined " + props.size() + " messages save to " +  tfile + " for locale " + loc);
							}
						}
						// 전체 미정의 메세지 저장
						storeNameSortPropperties(udmAllFile, udmAll, comment);
					} finally {
						udmAllFlk.release();
					}
				} finally {
					udmAllFlkFos.close();
				}
			}
		} catch (Exception e) {
			destoryFail	= new RuntimeException("Undefined localized messages save fail.", e);
		}
		
		// 컨피그 정리
		System.out.println("### Config destory.");
		try {
			ConfigProperties.destory();
		} catch (Exception e) {
			destoryFail	= new RuntimeException("config destory fail.", e);
		}
		
		if (destoryFail != null) {
			throw destoryFail;
		}
	}
	
	/**
	 * 프로퍼티명 기준 정렬후 저장
	 * @param propFile
	 * @param props
	 * @param comment
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void storeNameSortPropperties(File propFile, Properties props, String comment)
		throws UnsupportedEncodingException, IOException {
		PrintStream			ps;
		PrintWriter			pw;
		TreeSet<?>			sortedNames;
		
		sortedNames	= new TreeSet(props.keySet());
		ps			= new PrintStream(new FileOutputStream(propFile));
		try {
			Iterator<?>	its;
			
			if (comment != null) {
				ps.println(comment.replaceAll("^", "#"));
			}
			its	= sortedNames.iterator();
			while (its.hasNext()) {
				String	pn;
				
				pn	= (String)its.next();
				ps.print(this.escape4Properties(pn, true, true));
				ps.print('=');
				ps.println(this.escape4Properties(props.getProperty(pn), true, false));
			}
		} finally {
			ps.close();
		}
		pw	= new PrintWriter(new OutputStreamWriter(new FileOutputStream(propFile.getCanonicalPath() + ".messages"), "UTF-8"));
		try {
			Iterator<?>	its;
			
			if (comment != null) {
				pw.println(comment.replaceAll("^", "#"));
			}
			its	= sortedNames.iterator();
			while (its.hasNext()) {
				String	pn;
				
				pn	= (String)its.next();
				pw.println("[message--------------------------");
				pw.print("prop name : \"");
				pw.print(this.escape4Properties(pn, false, true));
				pw.println("\"");
				pw.print("prop text : \"");
				pw.print(this.escape4Properties(pn, true, false));
				pw.println("\"");
				pw.println(pn);
				pw.println("---------------------------message]");
			}
		} finally {
			pw.close();
		}
	}
	
	
	/**
	 * 프로퍼티용 이스케입
	 * @param src
	 * @param escapeUnicode
	 * @return
	 */
	public String escape4Properties(String src, boolean escapeUnicode, boolean isName) {
		StringBuffer	sb;
		int				size;
		
		if (src == null) {
			return null;
		}
		sb		= new StringBuffer();
		size	= src.length();
		for (int i=0; i<size; i++) {
			char cp;
			
			cp	= src.charAt(i);
			switch(cp) {
				case '\t':
					sb.append("\\t");
					break;
				case '\n':
					sb.append("\\n");
					break;
				case '\f':
					sb.append("\\f");
					break;
				case '\r':
					sb.append("\\r");
					break;
				case '!':
				case '#':
				case ':':
				case '=':
				case '\\':
					sb.append('\\').append(cp);
					break;
				default:
					if (isName && cp == ' ') {
						sb.append("\\ ");
					} else if (escapeUnicode && (cp < ' ' || cp > '~')) {
						sb.append("\\u").append(String.format("%04X", (int)cp));
					} else {
						sb.append(cp);
					}
			}
		}
		
		return sb.toString();
	}
	
	public SyncSignalCaster getSyncSignalCaster() {
		return this.ssc;
	}
}