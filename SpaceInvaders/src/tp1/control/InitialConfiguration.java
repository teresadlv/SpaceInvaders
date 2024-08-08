package tp1.control;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import tp1.exceptions.GameException;
import tp1.exceptions.InitializationException;
import tp1.view.Messages;

import java.io.BufferedReader;
import java.io.FileReader;

import java.io.FileNotFoundException;
import java.io.IOException;

public class InitialConfiguration {

	private static List<String> descriptions;
	public static final InitialConfiguration NONE = new InitialConfiguration();

	private InitialConfiguration() {
	}

	private InitialConfiguration(List<String> description) {
		descriptions = description;
	}

	public List<String> getShipDescription() {
		return Collections.unmodifiableList(descriptions);
	}

	@SuppressWarnings("resource")
	public static InitialConfiguration readFromFile(String filename) throws GameException, IOException {
		List<String> description = new ArrayList<>();
		try { 
			FileReader f = new FileReader(filename);
			String s = "";
			BufferedReader b = new BufferedReader(f); 
				while ((s = b.readLine()) != null) {
					description.add(s);
				}
		} catch (FileNotFoundException e) {
			throw new InitializationException(Messages.FILE_NOT_FOUND.formatted(filename));
		}
		return new InitialConfiguration(description);
	}

}
