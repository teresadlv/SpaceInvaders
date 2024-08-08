package tp1.control.exceptions;

public class NotAllowedMoveException extends GameException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public NotAllowedMoveException(String message) {
		super(message);
	}
	public NotAllowedMoveException(String message, Throwable cause) {
		super(message, cause);
	}

}
