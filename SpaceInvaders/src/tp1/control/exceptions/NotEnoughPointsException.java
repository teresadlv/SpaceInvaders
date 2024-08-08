package tp1.control.exceptions;

public class NotEnoughPointsException extends GameException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public NotEnoughPointsException (String message) {
		super(message);
	}
	public NotEnoughPointsException(String message, Throwable cause) {
		super(message, cause);
	}
}
