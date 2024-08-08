package tp1.control.commands;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import tp1.exceptions.CommandParseException;
import tp1.exceptions.GameException;
import tp1.view.Messages;

public class CommandGenerator {

	private static final List<Command> availableCommands = Arrays.asList(new HelpCommand(), new MoveCommand(),
			new NoneCommand(), new ShootCommand(), new ShockWaveCommand(), new ListCommand(), new ExitCommand(),
			new ResetCommand(), new SuperLaserCommand());

	public static Command parse(String[] commandWords) throws GameException, FileNotFoundException, IOException{
		if(commandWords[0].isEmpty())
			return new NoneCommand();
		for (Command c : availableCommands) {
			if(commandWords.length > 2) {
				throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
			}
			else {
				if (c.matchCommandName(commandWords[0])) {
					return c.parse(commandWords);
				}
			}
			
		}
		throw new CommandParseException(Messages.UNKNOWN_COMMAND);
	}

	public static String commandHelp() {
		StringBuilder commands = new StringBuilder();
		commands.append("\n");
		for (Command c : availableCommands) {
			commands.append(c.helpText()).append("\n");
		}
		return commands.toString();
	}

}