package com.omp.commons.util;

import java.text.MessageFormat;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.apache.log4j.spi.LoggerRepository;

import com.omp.commons.text.LocalizeMessage;

/**
 * 지역화 메세지 처리 하는 로거
 * @author pat
 *
 */
public class GLogger {

	private Logger	logger;

	public GLogger(Class<?> tcls) {
		this.logger	= Logger.getLogger(tcls);
	}
	
	public GLogger(String name) {
		this.logger	= Logger.getLogger(name);
	}
	
	public void l7dlog(Priority priority, String key, Throwable t) {
		this.log(priority, key, t);
	}

	public void l7dlog(Priority priority, String key, Object[] params,
			Throwable t) {
		this.log(priority, key, params, t);
	}

	public void trace(Object message) {
		this.log(Level.TRACE, message);
	}
	
	public void trace(String key, Object[] margs) {
		this.log(Level.TRACE, key, margs);
	}

	public void trace(Object message, Throwable t) {
		this.log(Level.TRACE, message, t);
	}

	public void trace(String key, Object[] margs, Throwable t) {
		this.log(Level.TRACE, key, margs, t);
	}
	
	public boolean isTraceEnabled() {
		return this.logger.isTraceEnabled();
	}

	public void assertLog(boolean assertion, String msg) {
		if (!assertion) {
			this.log(Level.ERROR, msg);
		}
	}

	public void debug(Object message) {
		this.log(Level.DEBUG, message);
	}

	public void debug(String key, Object[] args) {
		this.log(Level.DEBUG, key, args);
	}
	
	public void debug(Object message, Throwable t) {
		this.log(Level.DEBUG, message, t);
	}

	public void debug(String key, Object[] args, Throwable t) {
		this.log(Level.DEBUG, key, args, t);
	}
	
	public void error(Object message) {
		this.log(Level.ERROR, message);
	}

	public void error(String key, Object[] args) {
		this.log(Level.ERROR, key, args);
	}

	public void error(Object message, Throwable t) {
		this.log(Level.ERROR, message, t);
	}
	
	public void error(String key, Object[] args, Throwable t) {
		this.log(Level.ERROR, key, args, t);
	}

	public void fatal(Object message) {
		this.log(Level.FATAL, message);
	}

	public void fatal(String key, Object[] args) {
		this.log(Level.FATAL, key, args);
	}

	public void fatal(Object message, Throwable t) {
		this.log(Level.FATAL, message, t);
	}

	public void fatal(String key, Object[] args, Throwable t) {
		this.log(Level.FATAL, key, args, t);
	}

	public Level getEffectiveLevel() {
		return this.logger.getEffectiveLevel();
	}

	public LoggerRepository getLoggerRepository() {
		return this.logger.getLoggerRepository();
	}

	public void info(Object message) {
		this.log(Level.INFO, message);
	}

	public void info(String key, Object[] args) {
		this.log(Level.INFO, key, args);
	}

	public void info(Object message, Throwable t) {
		this.log(Level.INFO, message, t);
	}

	public void info(String key, Object[] args, Throwable t) {
		this.log(Level.INFO, key, args, t);
	}

	public boolean isDebugEnabled() {
		return this.logger.isDebugEnabled();
	}

	public boolean isEnabledFor(Priority level) {
		return this.logger.isEnabledFor(level);
	}

	public boolean isInfoEnabled() {
		return this.logger.isInfoEnabled();
	}

	public void log(Priority priority, Object message, Throwable t) {
		if (priority == Level.DEBUG) {
			this.logger.log(Level.DEBUG, message, t);
		} else {
			this.logger.log(priority, this.getMessageObject(message), t);
		}
	}

	public void log(Priority priority, String key, Object[] margs, Throwable t) {
		if (priority == Level.DEBUG) {
			this.logger.log(Level.DEBUG, new MessageFormat(key).format(margs), t);
		} else {
			this.logger.log(priority, LocalizeMessage.getLocalizedMessage(key, margs), t);
		}
	}
	
	
	public void log(Priority priority, Object message) {
		if (priority == Level.DEBUG) {
			this.logger.log(Level.DEBUG, message);
		} else {
			this.logger.log(priority, this.getMessageObject(message));
		}
	}

	public void log(Priority priority, String key, Object[] margs) {
		if (priority == Level.DEBUG) {
			this.logger.log(Level.DEBUG, new MessageFormat(key).format(margs));
		} else {
			this.logger.log(priority, LocalizeMessage.getLocalizedMessage(key, margs));
		}
	}
	
	
	public void log(String callerFQCN, Priority level, Object message,
			Throwable t) {
		if (level == Level.DEBUG) {
			this.logger.log(callerFQCN, Level.DEBUG, message, t);
		} else {
			this.logger.log(callerFQCN, level, this.getMessageObject(message), t);
		}
	}
	
	public void log(String callerFQCN, Priority level, String key, Object[] margs,
			Throwable t) {
		if (level == Level.DEBUG) {
			this.logger.log(callerFQCN, Level.DEBUG, new MessageFormat(key).format(margs), t);
		} else {
			this.logger.log(callerFQCN, level, LocalizeMessage.getLocalizedMessage(key, margs), t);
		}
	}

	public void warn(Object message) {
		this.log(Level.WARN, message);
	}

	public void warn(String key, Object[] args) {
		this.log(Level.WARN, key, args);
	}

	public void warn(Object message, Throwable t) {
		this.log(Level.WARN, message, t);
	}
	
	public void warn(String key, Object[] args, Throwable t) {
		this.log(Level.WARN, key, args, t);
	}

	private Object getMessageObject(Object src) {
		if (src == null) {
			return null;
		}
		if (src instanceof String) {
			return LocalizeMessage.getLocalizedMessage((String)src);
		} else if (src instanceof LocalizeMessage) {
			return ((LocalizeMessage)src).getLocalizedMessage();
		} else {
			return src;
		}
	}
}