package com.stockinvest.common.util;

import javax.servlet.ServletContextEvent;

import org.apache.log4j.Logger;
import org.springframework.web.context.ContextLoaderListener;



/**
 *  Spring framework ContextLodaderListener
 * @author 한언주
 */
public class CommonInitializer extends ContextLoaderListener {

    private Logger logger = Logger.getLogger(CommonInitializer.class);

    /**
     * Implemented this javax.servlet.ServletContextListener method
     */
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            WCommon.distory(sce.getServletContext());

			if(logger.isDebugEnabled()){
				logger.debug("contextDestroyed.");
			}
        } catch (Throwable t) {
            logger.error("contextDestroyed error!!!!!!!!!!!!!!!!", t);
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
            logger.error("contextInitialized error!!!!!!!!!!!!!!!!", t);
            throw new RuntimeException(t);
        }
    }
}
