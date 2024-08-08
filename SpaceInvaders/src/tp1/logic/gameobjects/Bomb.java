package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;

public class Bomb extends EnemyWeapon {

	private DestroyerAlien destroyerAlien;

	public Bomb(GameWorld game, Position pos, int life, int damage, Move dir, boolean habilitado,
			DestroyerAlien destroyerAlien) {
		super(game, pos, life, damage, dir, habilitado);
		this.destroyerAlien = destroyerAlien;
	}

	@Override
	public String getSymbol() {
		return Messages.BOMB_SYMBOL;
	}

	@Override
	public void onDelete() {
		super.onDelete();
		this.destroyerAlien.canThrowBomb();
	}

}