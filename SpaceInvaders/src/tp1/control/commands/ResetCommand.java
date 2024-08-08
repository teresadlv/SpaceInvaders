package tp1.control.commands;

import java.io.IOException;

import tp1.control.InitialConfiguration;
import tp1.exceptions.GameException;
import tp1.logic.GameModel;
import tp1.view.Messages;

public class ResetCommand extends NoParamsCommand {

	private InitialConfiguration configuration;

	@Override
	public Command parse(String[] commandWords) throws GameException, IOException { //COMO AÑADO LA EXCEP
		ResetCommand reset = new ResetCommand();
		InitialConfiguration config;
		if (commandWords.length > 1) {
			config = InitialConfiguration.readFromFile(commandWords[1]);
		} else
			config = InitialConfiguration.NONE;
		reset.setConfig(config);
		return reset;
	}

	public boolean execute(GameModel game) throws GameException{
			game.reset(configuration);
			return true;
	}

	private void setConfig(InitialConfiguration config) {
		this.configuration = config;
	}

	@Override
	protected String getName() {
		return Messages.COMMAND_RESET_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_RESET_SHORTCUT;
	}

	@Override
	protected String getDetails() {
		return Messages.COMMAND_RESET_DETAILS;
	}

	@Override
	protected String getHelp() {
		return Messages.COMMAND_RESET_HELP;
	}

}