package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;

/**
 * 
 * Class that represents the laser fired by {@link UCMShip}
 *
 */
public class UCMLaser extends UCMWeapon {
	
	private static final int damage = 1;
	private final static int life = 1;

	public UCMLaser(GameWorld game, Position pos, boolean habilitado, UCMShip nave) {
		super(game, pos, life, damage, habilitado, nave);
	}

	@Override
	public String getSymbol() {
			return Messages.LASER_SYMBOL;
		
	}

}