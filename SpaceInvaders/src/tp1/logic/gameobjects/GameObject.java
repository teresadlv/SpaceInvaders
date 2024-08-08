package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Position;

public abstract class GameObject implements GameItem {

	protected Position pos;
	protected int vida;
	protected GameWorld game;

	public GameObject(GameWorld game, Position pos, int vida) {
		this.game = game;
		this.pos = pos;
		this.vida = vida;
	}

	@Override
	public boolean isAlive() {
		return this.vida > 0;
	}

	@Override
	public boolean isOnPosition(Position pos) {
		if(isAlive())return this.pos.equals(pos);
		else return false;
	}

	protected int getLife() {
		return this.vida;
	}

	protected boolean isOut() {
		return this.pos.hasInvalidPos();
	}

	protected void receiveDamage(int damage) {
		this.vida -= damage;
	}

	protected abstract String getSymbol();

	protected abstract int getDamage();

	protected abstract int getArmour();

	protected abstract int getPuntos();

	@Override
	public String toString() {
		return getSymbol();
	}

	public abstract void onDelete();

	public abstract void automaticMove();

	
	public void computerAction() {
		// Implementacion vacia
	};

	@Override
	public boolean performAttack(GameItem other) {
		return false;
	}

	@Override
	public boolean receiveAttack(EnemyWeapon weapon) {
		boolean recibido = false;
		if (weapon.isOnPosition(this.pos)) {
			this.receiveDamage(weapon.getDamage());
			if (!this.isAlive()) {
				this.onDelete();
			}
			recibido = true;
		}
		return recibido;
	}

	@Override
	public boolean receiveAttack(UCMWeapon weapon) {
		return false;
	}

	@Override
	public void receiveExplotionDamage() {
		this.receiveDamage(1);
	}

}