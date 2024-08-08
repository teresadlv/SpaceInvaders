package tp1.exceptions;

public class OffWorldException extends GameException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public OffWorldException (String message) {
		super(message);
	}
	public OffWorldException(String message, Throwable cause) {
		super(message, cause);
	}
}
