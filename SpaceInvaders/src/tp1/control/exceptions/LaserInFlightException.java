package tp1.control.exceptions;

public class LaserInFlightException extends GameException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public LaserInFlightException (String message) {
		super(message);
	}
	public LaserInFlightException(String message, Throwable cause) {
		super(message, cause);
	}

}
