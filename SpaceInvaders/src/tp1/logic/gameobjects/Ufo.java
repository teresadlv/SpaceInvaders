package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;

/*
 * Clase Ufo
 */
public class Ufo extends EnemyShip {

	private boolean habilitado;
	private final static Position pos = new Position(Game.DIM_X, 0);
	private final static int points = 25;
	private final static int life = 1;
	private final static int damage = 0;

	// Constructor
	public Ufo(GameWorld game) {
		super(game, pos, life, points);
		this.habilitado = false;
	}

	/*
	 * Implementación y sobrescripción de métodos abstractos.
	 */
	
	public String getInfo() {
		StringBuilder buffer = new StringBuilder();
		buffer.append(Messages.alienDescription(Messages.UFO_DESCRIPTION, points , damage, life)).append(Messages.LINE_SEPARATOR);
		return buffer.toString();
	}
	@Override
	public String getSymbol() {
		return Messages.UFO_SYMBOL;
	}

	@Override
	public void onDelete() {
		game.removeObject(this);
		game.enableShockWave();
		nuevoUfo();
	}

	@Override
	public void automaticMove() {
		if (this.habilitado) {
			performMovement(dir);
			if (isOut()) {
				delete();
				game.removeObject(this);
				nuevoUfo();
			}
		}
	}

	private void delete() {
		super.onDelete();
	}

	private void nuevoUfo() {
		game.addObject(new Ufo(this.game));
	}

	@Override
	public void computerAction() {
		if (canGenerateRandomUfo()) {
			if (!this.habilitado) {
				enable();
			}
		}
	}

	private boolean canGenerateRandomUfo() {
		return game.getRandom().nextDouble() < game.getLevel().getUfoFrequency();
	}

	private void enable() {
		this.habilitado = true;
	}

}