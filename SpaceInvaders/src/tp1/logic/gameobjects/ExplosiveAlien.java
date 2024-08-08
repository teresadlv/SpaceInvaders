package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;

public class ExplosiveAlien extends AlienShip {
	
	private static final int life = 2;
	private static final int damage = 1;
	private static final int points = 12;

	public ExplosiveAlien() {
		super(null, null, 2, 12, null);
	}

	public ExplosiveAlien(GameWorld game, Position pos, AlienManager alienManager) {
		super(game, pos, life, points, alienManager);
	}
	
	@Override
	public String getInfo() {
		StringBuilder buffer = new StringBuilder();
		buffer.append(Messages.alienDescription(Messages.EXPLOSIVE_ALIEN_DESCRIPTION, points , damage, life)).append(Messages.LINE_SEPARATOR);
		return buffer.toString();
	}

	@Override
	protected AlienShip copy(GameWorld game, Position pos, AlienManager am) {
		return new ExplosiveAlien(game, pos, am);
	}

	@Override
	public void onDelete() {
		this.alienManager.groupAliensDead();
	}

	@Override
	protected String getSymbol() {
		return Messages.EXPLOSIVE_ALIEN_SYMBOL;
	}
	@Override
	public boolean performAttack(GameItem other) {
		if(!isAlive()) {
				for (int i = -1; i <= 1; i++) {
					for (int j = -1; j <= 1; j++) {
						Position pos_aux = this.pos;
						this.pos = Position.adyacent(this.pos, i, j);
						if (!(i == 0 && j == 0) && (other.isOnPosition(this.pos))) {
							other.receiveExplotionDamage();
						}
						this.pos = pos_aux;
					}
				}
		}
		return false; //PARA QUE NO PARE AL DARLE A UNO, SI NO QUE SIGA RECORRIENDO
	}


}