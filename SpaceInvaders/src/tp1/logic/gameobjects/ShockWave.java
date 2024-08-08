package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;

public class ShockWave extends UCMWeapon {

	private int usosRestantes;
	private static final int damage = 1;
	private static final int life = 0;

	public ShockWave(GameWorld game) {
		super(game, new Position(Game.DIM_X, Game.DIM_Y), life, damage, true, null);
		this.usosRestantes = 1;
	}

	@Override
	public boolean performAttack(GameItem other) {
		other.receiveAttack(this);
		return false;
	}

	@Override
	protected String getSymbol() {
		return Messages.SHOCKWAVE_SYMBOL;
	}

	@Override
	public void onDelete() {
		this.habilitado = false;
		this.usosRestantes--;
	}

	@Override
	public void enable() {
		this.habilitado = true;
	}

	@Override
	public boolean getEnable() {
		return this.usosRestantes > 0 && this.habilitado;
	}

}