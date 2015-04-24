package br.com.cowtysys.challenge.exception;

public class DestinationUnreachableException extends Exception {

	private static final long serialVersionUID = -5169201173751707000L;

	public DestinationUnreachableException() {
		super();
	}

	public DestinationUnreachableException(String message, Throwable cause) {
		super(message, cause);
		System.out.println(cause.getMessage());
	}

	public DestinationUnreachableException(String message) {
		super(message);
	}

	public DestinationUnreachableException(Throwable cause) {
		super(cause);
	}
}
