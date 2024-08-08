package tp1.control;

import static tp1.view.Messages.debug;

import java.io.IOException;
import java.util.Scanner;

import tp1.control.commands.Command;
import tp1.control.commands.CommandGenerator;
import tp1.exceptions.GameException;
import tp1.logic.Game;
import tp1.view.BoardPrinter;
import tp1.view.GamePrinter;
import tp1.view.Messages;

/**
 * Accepts user input and coordinates the game execution logic.
 */
public class Controller {

	private Game game;
	private Scanner scanner;
	private GamePrinter printer;

	public Controller(Game game, Scanner scanner) {
		this.game = game;
		this.scanner = scanner;
		printer = new BoardPrinter(game);
	}

	/**
	 * Show prompt and request command.
	 *
	 * @return the player command as words
	 */
	private String[] prompt() {
		System.out.print(Messages.PROMPT);
		String line = scanner.nextLine();
		String[] words = line.toLowerCase().trim().split("\\s+");

		System.out.println(debug(line));

		return words;
	}

	public void run() {
		Boolean printTheGame = true;

		while (!game.isFinished()) {
			if (printTheGame) {
				printGame();
			}
			try {
				printTheGame = false;

				String[] parameters = prompt();
				Command command = CommandGenerator.parse(parameters); // LANZA EXCEPTION
				printTheGame = command.execute(game); // LANZA EXCEPTION
			} catch (GameException | IOException e) {
				System.out.println(e.getMessage());
				Throwable cause = e.getCause();
				if (cause != null) {
					System.out.println(cause.getMessage());
				}
			}
		}
		if (printTheGame)
			printGame();
		printEndMessage();
	}

	/**
	 * Draws/prints the game
	 */
	private void printGame() {
		System.out.println(printer);
	}

	/**
	 * Prints the final message once the game is finished.
	 */
	public void printEndMessage() {
		System.out.println(printer.endMessage());
	}

}