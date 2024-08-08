package tp1.logic.gameobjects;

import tp1.logic.AlienManager;
import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.logic.Move;

public abstract class AlienShip extends EnemyShip {

	protected AlienManager alienManager;

	public AlienShip(GameWorld game, Position pos, int vida, int puntos, AlienManager alienManager) {
		super(game, pos, vida, puntos);
		if (game != null)
			this.ciclosParaMover = alienManager.getSpeed();
		this.alienManager = alienManager;
	}

	protected abstract AlienShip copy(GameWorld game, Position pos, AlienManager alienManager);

	protected boolean matchShipSymbol(String input) {
		return getSymbol().equalsIgnoreCase(input);
	}

	@Override
	public void onDelete() {
		alienManager.groupAliensDead();
		alienManager.alienDead();
	}

	@Override
	public void automaticMove() {
		if (isAlive()) {
			if (this.ciclosParaMover == 0) {
				performMovement(dir);
				if (isInBorder()) {
					this.alienManager.shipOnBorder();
				}
				this.ciclosParaMover = alienManager.getCyclesToMove();
			} else if (alienManager.onBorder()) {
				this.alienManager.decreaseOnBorder();
				if (this.alienManager.getShipsOnBorder() >= 0) {
					descent();
					this.dir = Move.reverseDirection(dir);
				}
			} else
				this.ciclosParaMover--;
		} else { //PARA QUE SI MUERE UN ALIEN NO SE ROMPA EL MOVIMIENTO 
			if (alienManager.onBorder()) {
				this.alienManager.decreaseOnBorder();
				if (this.alienManager.getShipsOnBorder() > 0) {
					descent();
					this.dir = Move.reverseDirection(dir);
				}
			}
		}
		if(this.pos.finalRow()) {
			this.alienManager.setSquadInFinalRow();
		}
	}

	private boolean isInBorder() {
		return this.pos.inBorder();
	}

	private void descent() {
		performMovement(Move.DOWN);
	}
	
	public String getInfo() {
		//IMPLEMENTACIÓN VACÍA
		return " ";
	}

}
