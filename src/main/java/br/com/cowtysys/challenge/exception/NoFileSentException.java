package br.com.cowtysys.challenge.exception;

public class NoFileSentException extends Exception {

	private static final long serialVersionUID = -5169201173751707000L;

	public NoFileSentException() {
		super();
	}

	public NoFileSentException(String message, Throwable cause) {
		super(message, cause);
		System.out.println(cause.getMessage());
	}

	public NoFileSentException(String message) {
		super(message);
	}

	public NoFileSentException(Throwable cause) {
		super(cause);
	}
}
