package tp1.control.commands;

import tp1.exceptions.CommandParseException;
import tp1.exceptions.GameException;
import tp1.logic.GameModel;
import tp1.logic.Move;
import tp1.view.Messages;

public class MoveCommand extends Command {

	private Move move;

	public MoveCommand() {
	}

	protected MoveCommand(Move move) {
		this.move = move;
	}

	@Override
	protected String getName() {
		return Messages.COMMAND_MOVE_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_MOVE_SHORTCUT;
	}

	@Override
	protected String getDetails() {
		return Messages.COMMAND_MOVE_DETAILS;
	}

	@Override
	protected String getHelp() {
		return Messages.COMMAND_MOVE_HELP;
	}

	@Override
	public Command parse(String[] commandWords) throws GameException {
		Command cmd = null;
		Move move = Move.NONE;
		if (this.matchCommandName(commandWords[0])) {
			if (commandWords.length == 1) {
				throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
			}
			else cmd = new MoveCommand(move.parser(commandWords[1]));
		}
		return cmd;
	}

	@Override
	public boolean execute(GameModel game) throws GameException{
		game.move(move);
		game.update();
		return true;
	}

}