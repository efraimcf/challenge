package br.com.cowtysys.challenge.util.logger;

public interface CowtySysLogger {

	void trace(String object);
	void trace(String object, Throwable throwable);
	void debug(String object);
	void debug(String object, Throwable throwable);
	void info(String object);
	void info(String object, Throwable throwable);
	void warn(String object);
	void warn(String object, Throwable throwable);
	void error(String object);
	void error(String object, Throwable throwable);
}