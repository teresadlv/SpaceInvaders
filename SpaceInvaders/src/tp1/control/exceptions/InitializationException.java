package tp1.control.exceptions;

public class InitializationException extends GameException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public InitializationException(String message) {
		super(message);
	}
	public InitializationException(String message, Throwable cause) {
		super(message, cause);
	}

}
