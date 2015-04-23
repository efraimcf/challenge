package br.com.cowtysys.challenge.util.logger;

import org.slf4j.LoggerFactory;

public class CowtySysLoggerFactory {

	public static CowtySysLogger loggerMock = null;

	private CowtySysLoggerFactory() {
	}

	public static CowtySysLoggerFactory getInstance() {
		return new CowtySysLoggerFactory();
	}

	@SuppressWarnings("rawtypes")
	public CowtySysLogger getLogger(Class clazz) {
		if (loggerMock != null) {
			return loggerMock;
		} 
		return new CowtySysLoggerImpl(LoggerFactory.getLogger(clazz));
	}
}