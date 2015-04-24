package br.com.cowtysys.challenge.exception;

public class EdgeAlreadyRegisteredException extends Exception {

	private static final long serialVersionUID = -5169201173751707000L;

	public EdgeAlreadyRegisteredException() {
		super();
	}

	public EdgeAlreadyRegisteredException(String message, Throwable cause) {
		super(message, cause);
		System.out.println(cause.getMessage());
	}

	public EdgeAlreadyRegisteredException(String message) {
		super(message);
	}

	public EdgeAlreadyRegisteredException(Throwable cause) {
		super(cause);
	}
}
