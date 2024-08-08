package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;

public class UCMSuperLaser extends UCMWeapon {

	private static final int damage = 1;
	private final static int life = 1;
	
	public UCMSuperLaser(GameWorld game, Position pos, Boolean habilitado, UCMShip player) {
		super(game, pos, life, damage, habilitado, player);
	}
	@Override
	protected String getSymbol() {
		return Messages.SUPERLASER_SYMBOL;
	}

}
