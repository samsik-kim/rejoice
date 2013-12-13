package com.omp.servlet;

import java.io.File;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import pat.neocore.io.filefilter.SimpleFileFilter;

import com.omp.commons.GompServiceContext;
import com.omp.commons.util.GLogger;
import com.omp.commons.util.config.ConfigProperties;
import com.omp.commons.util.config.ConfigUpdateEventListener;

/**
 * GOMP 관련 웹 컨택스트 초기화 슈퍼 클래스
 * @author pat
 *
 */
public abstract class AbstractContextInitialzeListener
	implements ServletContextListener {
	
	
	private class ContextConfigUpdateListener
		implements ConfigUpdateEventListener {
		
		@Override
		public void configUpdated(ConfigProperties conf) {
			Log	logger;
			
			
			logger	= LogFactory.getLog(this.getClass());
			if (logger.isDebugEnabled()) {
				logger.debug("ServletContext Config update.");
				logger.debug("---------------------------------------------------------------------\n"
						+ conf.getDumpText() + "\n"
						+ "-------------------------------------------------------------------------");
			}
			AbstractContextInitialzeListener.this.sctx.setAttribute("CONF", conf.getStaticProperties());
		}
	}

	private	GompServiceContext		gsctx;
	private GLogger					logger	= new GLogger(this.getClass());
	private ServletContext 			sctx;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try {
			ConfigProperties	conf;
			
			this.sctx 		= sce.getServletContext();

			System.out.println("# Context Initialize START");

			// 컨테이너 정보 설정
			System.setProperty("omp." + this.getContextId() + ".context.rootpath", sctx.getRealPath("/"));

			// config 초기화
			String	configResourcePath	= sctx.getInitParameter("ConfigProperties.resourcePath");
			if (configResourcePath == null) {
				throw new RuntimeException("please set servlet context init parameter ConfigProperties.resourcePath for ConfigProperties");
			}
			ConfigProperties.addListener(new ContextConfigUpdateListener());
			this.gsctx	= new GompServiceContext(configResourcePath);
			try {
				conf	= this.gsctx.getConfig();
				
				// 임시폴더 클리너 설정
				this.gsctx.addTempCleaner(new File(conf.getString("omp.common.path.temp.base")), null, 1000 * 60 * 60 * 24);
				// 1회용 자료 클리너 설정
				this.gsctx.addTempCleaner(new File(conf.getString("omp.common.path.share.once")), null, 1000 * 60 * 60 * 24 * 7);
				// 업로드용 임시 폴더 정리
				this.gsctx.addTempCleaner((File)sctx.getAttribute("javax.servlet.context.tempdir"), new SimpleFileFilter("upload_", ".tmp"), 1000 * 60 * 60 * 24);
				
				// 추가 초기화
				this.gsctx.initServiceResource("webapp." + this.getContextId());
				sctx.setAttribute("SSC", this.gsctx.getSyncSignalCaster());
				
				this.initExtra(sce);
				
				System.out.println("# Context Initialize COMPLETED.");
			} catch (Throwable t) {
				this.gsctx.destory();
				this.gsctx	= null;
				throw t;
			}
		} catch (Throwable t) {
			t.printStackTrace();
			if (t instanceof Error) {
				throw (Error)t;
			} else if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			} else {
				throw new RuntimeException(t);
			}
		}
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		try {
			System.out.println("# Context Destroy START.");
			
			// 추가 정리
			this.destoryExtra(sce);
			
			if (this.gsctx != null) {
				this.gsctx.destory();
			}
			
			this.sctx.removeAttribute("CONF");
			this.sctx.removeAttribute("SSC");
			this.sctx	= null;
			
			System.out.println("# Context Destroy COMPLETED.");
			
		} catch (Throwable t) {
			t.printStackTrace();
			if (t instanceof Error) {
				throw (Error)t;
			} else if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			} else {
				throw new RuntimeException(t);
			}
		}
	}
	
	protected abstract void destoryExtra(ServletContextEvent sce) throws Exception;
	
	/**
	 * 컨택스트 아이디 반환
	 * @return
	 */
	protected abstract String getContextId();
	
	/**
	 * 추가 초기화
	 * @param sce
	 * @throws Exception
	 */
	protected abstract void initExtra(ServletContextEvent sce) throws Exception;
	
	/**
	 * 주어진 객체의 맴버 필드를 맵으로 구성한 후 어플레케이션 컨택스트에 설정합니다.
	 * @param id
	 * @param obj
	 */
	protected void setConstants2ApplicationContext(ServletContextEvent sce, String id, Object obj) {
		Map<String, Object>	conMap;
		Field[]				flds;
		
		flds	= obj.getClass().getFields();
		if (flds == null) {
			return;
		}
		conMap	= new HashMap<String, Object>();
		
		for (Field fld: flds) {
			try {
				conMap.put(fld.getName(), fld.get(obj));
			} catch (Exception ignore) {}
		}
		sce.getServletContext().setAttribute(id, conMap);
	}
}
