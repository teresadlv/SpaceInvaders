package tp1.logic.gameobjects;

import java.util.Arrays;
import java.util.List;

import tp1.exceptions.GameException;
import tp1.exceptions.InitializationException;
import tp1.logic.AlienManager;
import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;

public class ShipFactory {

	private static final List<AlienShip> AVAILABLE_ALIEN_SHIPS = Arrays.asList(new RegularAlien(), new DestroyerAlien(),
			new ExplosiveAlien());

	public static AlienShip spawnAlienShip(String input, GameWorld game, Position pos, AlienManager alienManager) throws GameException{
		for (AlienShip s : AVAILABLE_ALIEN_SHIPS) {
			if(s.matchShipSymbol(input)) {
				return s.copy(game, pos, alienManager);
			}
		}
		throw new InitializationException(Messages.INITIAL_CONFIGURATION_ERROR+"\n"+Messages.UNKNOWN_SHIP.formatted(input.toUpperCase()));
	}
	public static String getInfoAlienShip() {
		StringBuilder buffer = new StringBuilder();
		for (AlienShip s : AVAILABLE_ALIEN_SHIPS) {
				buffer.append(s.getInfo());
		}
		return buffer.toString();
	}
}
