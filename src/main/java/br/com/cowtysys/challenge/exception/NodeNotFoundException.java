package br.com.cowtysys.challenge.exception;

public class NodeNotFoundException extends Exception {

	private static final long serialVersionUID = -5169201173751707000L;

	public NodeNotFoundException() {
		super();
	}

	public NodeNotFoundException(String message, Throwable cause) {
		super(message, cause);
		System.out.println(cause.getMessage());
	}

	public NodeNotFoundException(String message) {
		super(message);
	}

	public NodeNotFoundException(Throwable cause) {
		super(cause);
	}
}
