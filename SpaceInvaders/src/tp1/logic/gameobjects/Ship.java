package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Position;

public abstract class Ship extends GameObject {

	public Ship(GameWorld game, Position pos, int vida) {
		super(game, pos, vida);
	}

	@Override
	protected int getDamage() {
		return 0;
	}

	@Override
	protected int getPuntos() {
		return 0;
	}

	@Override
	protected int getArmour() {
		return 0;
	}

	@Override
	public void onDelete() {
		//Implementacion vacia
	}

	@Override
	public void automaticMove() {
		// Implementacion vacia porque la nave UCM se mueve con los comandos
	}

}