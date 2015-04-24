package br.com.cowtysys.challenge.exception;

public class InvalidParameterValueException extends Exception {

	private static final long serialVersionUID = -5169201173751707000L;

	public InvalidParameterValueException() {
		super();
	}

	public InvalidParameterValueException(String message, Throwable cause) {
		super(message, cause);
		System.out.println(cause.getMessage());
	}

	public InvalidParameterValueException(String message) {
		super(message);
	}

	public InvalidParameterValueException(Throwable cause) {
		super(cause);
	}
}
