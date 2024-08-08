package tp1.control.commands;

import tp1.exceptions.GameException;
import tp1.logic.GameModel;
import tp1.view.Messages;

public class HelpCommand extends NoParamsCommand {

	@Override
	protected String getName() {
		return Messages.COMMAND_HELP_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_HELP_SHORTCUT;
	}

	@Override
	protected String getDetails() {
		return Messages.COMMAND_HELP_DETAILS;
	}

	@Override
	protected String getHelp() {
		return Messages.COMMAND_HELP_HELP;
	}

	@Override
	public boolean execute(GameModel game) throws GameException{
		
		System.out.println("Available commands: "+CommandGenerator.commandHelp());
		return false;
	}

}