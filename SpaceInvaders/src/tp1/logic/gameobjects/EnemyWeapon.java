package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;

public abstract class EnemyWeapon extends Weapon {

	public EnemyWeapon(GameWorld game, Position pos, int life, int damage, Move dir, Boolean habilitado) {
		super(game, pos, life, damage, dir, habilitado);
	}
	
	@Override
	public boolean performAttack(GameItem other) {
		boolean realizado = false;
		if(other.isOnPosition(this.pos)) {
			other.receiveAttack(this);	
			this.onDelete();
			realizado = true;
		}
		return realizado;
	}
	
}
