package tp1.control.commands;

import java.io.IOException;

import tp1.exceptions.CommandParseException;
import tp1.exceptions.GameException;
import tp1.view.Messages;

public abstract class NoParamsCommand extends Command {

	@Override
	public Command parse(String[] commandWords) throws GameException, IOException{
		Command cmd = null;
		if (this.matchCommandName(commandWords[0])) {
			if(commandWords.length == 1) {
				cmd = this;
			}
			else {
				throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
			}
		}
		return cmd;
	}

}