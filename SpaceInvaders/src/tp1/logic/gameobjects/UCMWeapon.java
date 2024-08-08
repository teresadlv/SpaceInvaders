package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;

public abstract class UCMWeapon extends Weapon {

	protected UCMShip player;
	private static final Move dir = Move.UP;

	public UCMWeapon(GameWorld game, Position pos, int life, int damage, Boolean habilitado, UCMShip player) {
		super(game, pos, life, damage, dir, habilitado);
		this.player = player;
	}

	@Override
	public void onDelete() {
		super.onDelete();
		this.player.canShoot();
	}

	@Override
	public boolean performAttack(GameItem other) {

		boolean realizado = false;
		if (other.isOnPosition(this.pos)) {
			other.receiveAttack(this);
			this.onDelete();
			realizado = true;
		}
		return realizado;
	}

}
