package org.butterspy.method;

public class MethodNotAllowedException extends RuntimeException {

	private static final long serialVersionUID = 6594311595942699578L;
	
	public MethodNotAllowedException() {
	}

	public MethodNotAllowedException(String message) {
		super(message);
	}

	public MethodNotAllowedException(Throwable cause) {
		super(cause);
	}

	public MethodNotAllowedException(String message, Throwable cause) {
		super(message, cause);
	}

	public MethodNotAllowedException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
