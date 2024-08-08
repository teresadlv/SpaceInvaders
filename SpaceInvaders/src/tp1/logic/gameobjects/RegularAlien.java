package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;

/**
 * 
 * Class representing a regular alien
 *
 */
public class RegularAlien extends AlienShip {

	private static final int life = 2;
	private static final int damage = 0;
	private static final int points = 5;
	
	public RegularAlien(GameWorld game, Position pos, AlienManager alienManager) {
		super(game, pos, life, points, alienManager);
	}
	
	@Override
	public String getInfo() {
		StringBuilder buffer = new StringBuilder();
		buffer.append(Messages.alienDescription(Messages.REGULAR_ALIEN_DESCRIPTION, points , damage, life)).append(Messages.LINE_SEPARATOR);
		return buffer.toString();
	}

	public RegularAlien() {
		super(null, null, 2, 5, null);
	}

	@Override
	public String getSymbol() {
		return Messages.REGULAR_ALIEN_SYMBOL;
	}

	@Override
	protected AlienShip copy(GameWorld game, Position pos, AlienManager am) {
		return new RegularAlien(game, pos, am);
	}

}