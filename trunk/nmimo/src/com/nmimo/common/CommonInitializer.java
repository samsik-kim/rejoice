package com.nmimo.common;

import javax.servlet.ServletContextEvent;

import org.apache.log4j.Logger;
import org.springframework.web.context.ContextLoaderListener;

public class CommonInitializer extends ContextLoaderListener {

    private Logger  LOG = Logger.getLogger(CommonInitializer.class);

    /**
     * Implemented this javax.servlet.ServletContextListener method
     */
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            WCommon.distory(sce.getServletContext());
            LOG.info("contextDestroyed.");
        } catch (Throwable t) {
            LOG.error("contextDestroyed error!!!!!!!!!!!!!!!!", t);
            throw new RuntimeException(t);
        }
    }

    /**
     * @todo Implemented this javax.servlet.ServletContextListener method
     */
    public void contextInitialized(ServletContextEvent sce) {
        try {
        	super.contextInitialized(sce);        	
        	
            WCommon.initialize(sce.getServletContext());
        } catch (Throwable t) {
            LOG.error("contextInitialized error!!!!!!!!!!!!!!!!", t);
            throw new RuntimeException(t);
        }
    }
}