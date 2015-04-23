package br.com.cowtysys.challenge.util.logger;

import java.io.Serializable;

import org.slf4j.Logger;

public class CowtySysLoggerImpl implements CowtySysLogger, Serializable {

	private static final long serialVersionUID = -5366045918816575081L;

	private Logger logger; 

	public CowtySysLoggerImpl(Logger logger) {
		this.logger = logger;
	}

	@Override
	public void trace(String object) {
		if (logger.isTraceEnabled()) {
			logger.debug(object);
		}
	}

	@Override
	public void trace(String object, Throwable throwable) {
		if (logger.isTraceEnabled()) {
			logger.debug(object, throwable);
		}
	}

	@Override
	public void debug(String object) {
		if (logger.isDebugEnabled()) {
			logger.debug(object);
		}
	}

	@Override
	public void debug(String object, Throwable throwable) {
		if (logger.isDebugEnabled()) {
			logger.debug(object, throwable);
		}
	}

	@Override
	public void info(String object) {
		if (logger.isInfoEnabled()) {
			logger.info(object);
		}
	}

	@Override
	public void info(String object, Throwable throwable) {
		if (logger.isInfoEnabled()) {
			logger.info(object, throwable);
		}
	}

	@Override
	public void warn(String object) {
		if (logger.isWarnEnabled()) {
			logger.warn(object);
		}
	}

	@Override
	public void warn(String object, Throwable throwable) {
		if (logger.isWarnEnabled()) {		
			logger.warn(object, throwable);
		}
	}

	@Override
	public void error(String object) {
		if (logger.isErrorEnabled()) {		
			logger.error(object);
		}
	}

	@Override
	public void error(String object, Throwable throwable) {
		if (logger.isErrorEnabled()) {		
			logger.error(object, throwable);
		}
	}
}