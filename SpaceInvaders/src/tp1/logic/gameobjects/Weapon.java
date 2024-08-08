package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;

public abstract class Weapon extends GameObject {

	protected int damage;
	protected Move dir;
	protected boolean habilitado;

	public Weapon(GameWorld game, Position pos, int life, int damage, Move dir, Boolean habilitado) {
		super(game, pos, life);
		this.damage = damage;
		this.dir = dir;
		this.habilitado = habilitado;
	}

	@Override
	protected int getDamage() {
		return this.damage;
	}

	@Override
	public void onDelete() {
		this.receiveDamage(vida);
		this.habilitado = false;
	}

	protected boolean getEnable() {
		return this.habilitado;
	}

	protected void enable() {
		this.habilitado = true;
	}

	@Override
	protected int getArmour() {
		return 0;
	}

	@Override
	protected int getPuntos() {
		return 0;
	}

	@Override
	public void automaticMove() {
		performMove();
		if (isOut()) {
			onDelete();
		}
	}

	protected void performMove() {
		this.pos = this.pos.move(this.dir);
	}
	

}
