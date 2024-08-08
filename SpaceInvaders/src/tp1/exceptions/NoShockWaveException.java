package tp1.exceptions;

public class NoShockWaveException extends GameException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NoShockWaveException (String message) {
		super(message);
	}
	public NoShockWaveException(String message, Throwable cause) {
		super(message, cause);
	}
}
