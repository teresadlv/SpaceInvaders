package tp1.logic;

import tp1.exceptions.CommandParseException;
import tp1.exceptions.GameException;
import tp1.view.Messages;

/**
 * Represents the allowed movements in the game
 *
 */
public enum Move {
	LEFT(-1, 0), LLEFT(-2, 0), RIGHT(1, 0), RRIGHT(2, 0), DOWN(0, 1), UP(0, -1), NONE(0, 0);

	private int x;
	private int y;

	private Move(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public Move parser(String comando) throws GameException{
		try {
			Move move = Move.valueOf(representative(comando.toUpperCase()));
			return move;
		} catch (IllegalArgumentException e) {
			throw new CommandParseException(Messages.DIRECTION_ERROR + comando.toUpperCase()+"\n"+Messages.ALLOWED_MOVES_MESSAGE);
		}
	}

	private static String representative(String str) throws GameException{
		String sol = "";
		switch (str.toUpperCase()) {
		case "LEFT":
			sol = "LEFT";
			break;
		case "LLEFT":
			sol = "LLEFT";
			break;
		case "RIGHT":
			sol = "RIGHT";
			break;
		case "RRIGHT":
			sol = "RRIGHT";
			break;
		}
		return sol;
	}

	public static Move reverseDirection(Move dir) {
		return (dir == Move.LEFT) ? Move.RIGHT : Move.LEFT;
	}

}