package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.GameWorld;
import tp1.logic.Move;
import tp1.logic.Position;
import tp1.view.Messages;

public class DestroyerAlien extends AlienShip {

	private boolean puedeLanzarBomba;
	private static final int life = 1;
	private static final int damage = 1;
	private static final int points = 10;

	public DestroyerAlien(GameWorld game, Position pos, AlienManager alienManager) {
		super(game, pos, life, points, alienManager);
		puedeLanzarBomba = true;
	}

	public DestroyerAlien() {
		super(null, null, 1, 10, null);
	}
	@Override
	public String getInfo() {
		StringBuilder buffer = new StringBuilder();
		buffer.append(Messages.alienDescription(Messages.DESTROYER_ALIEN_DESCRIPTION, points , damage, life)).append(Messages.LINE_SEPARATOR);
		return buffer.toString();
	}

	@Override
	protected AlienShip copy(GameWorld game, Position pos, AlienManager am) {
		return new DestroyerAlien(game, pos, am);
	}

	@Override
	public String getSymbol() {
		return Messages.DESTROYER_ALIEN_SYMBOL;
	}

	private boolean canGenerateBomb() {
		return (game.getRandom().nextDouble() < game.getLevel().getBombFrequency());
	}

	public void canThrowBomb() {
		this.puedeLanzarBomba = true;
	}

	@Override
	public void computerAction() {
		if (this.puedeLanzarBomba) {
			if (canGenerateBomb()) {
				game.addObject(new Bomb(game, pos, 1, 1, Move.DOWN, true, this));
				this.puedeLanzarBomba = false;
			}
		}
	}

}
