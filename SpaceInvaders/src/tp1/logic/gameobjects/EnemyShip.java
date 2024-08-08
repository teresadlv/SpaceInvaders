package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.logic.Move;

public abstract class EnemyShip extends Ship {

	private int puntos;
	protected Move dir;
	protected int ciclosParaMover;

	public EnemyShip(GameWorld game, Position pos, int vida, int puntos) {
		super(game, pos, vida);
		this.dir = Move.LEFT;
		this.puntos = puntos;
		this.ciclosParaMover = 0;
	}

	protected void performMovement(Move dir) {
		this.pos = this.pos.move(dir);
	}

	@Override
	public String toString() {
		return " " + getSymbol() + "[" + String.format("%02d", getLife()) + "]";
	}

	@Override
	protected int getPuntos() {
		return this.puntos;
	}

	@Override
	public boolean receiveAttack(UCMWeapon weapon) {
		boolean recibido = false;
		int puntos = this.getPuntos();
		if (weapon.isOnPosition(this.pos) || weapon.getEnable()) {
			this.receiveDamage(weapon.getDamage());
			if (!this.isAlive()) {
				this.onDelete();
				this.game.receivePoints(puntos);
			}
			recibido = true;
		}
		return recibido;
	}

}
