package tp1.control.commands;

import java.io.FileNotFoundException;
import java.io.IOException;

import tp1.exceptions.GameException;
import tp1.logic.GameModel;

/**
 * Represents a user action in the game.
 *
 */
public abstract class Command {

	protected abstract String getName();

	protected abstract String getShortcut();

	protected abstract String getDetails();

	protected abstract String getHelp();

	/**
	 * Execute the command.
	 * 
	 * @param game Where to execute the command.
	 * 
	 * @return {@code ExecutionResult} representing if command was successful and if
	 *         board must be printed
	 */
	public abstract boolean execute(GameModel game) throws GameException;

	public abstract Command parse(String[] commandWords) throws GameException, FileNotFoundException, IOException;

	protected boolean matchCommandName(String name){
		return getShortcut().equalsIgnoreCase(name) || getName().equalsIgnoreCase(name);
	}

	public String helpText() {
		return getDetails() + " : " + getHelp();
	}
}