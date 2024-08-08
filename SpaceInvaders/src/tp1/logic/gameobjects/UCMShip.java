package tp1.logic.gameobjects;

import tp1.logic.Position;
import tp1.view.Messages;
import tp1.exceptions.GameException;
import tp1.exceptions.LaserInFlightException;
import tp1.exceptions.NotEnoughPointsException;
import tp1.exceptions.OffWorldException;
import tp1.logic.Game;
import tp1.logic.GameWorld;
import tp1.logic.Move;

/*
 * Clase que representa la nave del jugador en el juego.
 */
public class UCMShip extends Ship {

	private boolean puedeDispararLaser;
	private int puntos;

	public UCMShip(GameWorld game) {
		super(game, new Position(Game.DIM_X / 2, Game.DIM_Y - 1), 3);
		this.puedeDispararLaser = true;
	}

	/*
	 * Implementación y sobrescripción de métodos abstractos.
	 */
	@Override
	public String getSymbol() {
		String str = "";
		if (isAlive()) {
			str = Messages.UCMSHIP_SYMBOL;
		} else {
			str = Messages.UCMSHIP_DEAD_SYMBOL;
		}
		return str;
	}

	/*
	 * Métodos propios de la clase UCMShip
	 */
	/**
	 * Mueve la nave en la dirección especificada si es una dirección válida y si la
	 * nueva posición resultante está dentro del tablero.
	 * 
	 * @param dir Dirección en la que se moverá la nave.
	 */
	public void moverEnDireccion(Move dir) throws GameException {
		if (!this.pos.move(dir).hasInvalidPos()) {
			this.pos = this.pos.move(dir);
		} else {
			throw new OffWorldException(
					Messages.MOVEMENT_ERROR + "\n" + Messages.OFF_WORLD_MESSAGE.formatted(dir, this.pos.toString()));
		}
	}

	public void canShoot() {
		this.puedeDispararLaser = true;
	}

	public void createLaser() throws GameException {
		boolean haDisparado = puedeDispararLaser;

		if (!haDisparado) {
			throw new LaserInFlightException(Messages.LASER_ERROR + "\n" + Messages.LASER_ALREADY_SHOT);
		} else {
			game.addObject(new UCMLaser(game, pos, true, this));
			puedeDispararLaser = false;
		}
	}


	public void shootSuperLaser() throws GameException {
		boolean haDisparado = puedeDispararLaser;

		if (this.puntos < 5) {
			throw new NotEnoughPointsException(Messages.NOT_ENOUGH_POINTS_ERROR.formatted(this.puntos, 5));
		} else if (!haDisparado) {
			throw new LaserInFlightException(Messages.SUPERLASER_ERROR + "\n" + Messages.LASER_ALREADY_SHOT);
		} else {
			addPoints(-5);
			game.addObject(new UCMSuperLaser(game, pos, true, this));
			this.puedeDispararLaser = false;
			
		}
	}

	public String getInfo() {
		StringBuilder buffer = new StringBuilder();
		buffer.append(Messages.ucmShipDescription(Messages.UCMSHIP_DESCRIPTION, 1, 3)).append(Messages.LINE_SEPARATOR);
		return buffer.toString();
	}

	public String stateToString() {
		StringBuilder buffer = new StringBuilder();
		buffer.append("Life: ").append(getLife()).append(Messages.LINE_SEPARATOR).append(Messages.SCORE).append(" ")
				.append(this.puntos).append(Messages.LINE_SEPARATOR).append("ShockWave: ");
		if (game.getShockWaveState()) {
			buffer.append("ON");
		} else
			buffer.append("OFF");
		buffer.append(Messages.LINE_SEPARATOR);
		return buffer.toString();
	}

	public void addPoints(int puntos) {
		this.puntos += puntos;
	}

	public boolean existLaser() {
		return this.puedeDispararLaser;
	}

	public static String allowedMoves(String s) {
		String move = Move.LEFT + s + Move.LLEFT + s + Move.RIGHT + s + Move.RRIGHT;
		return move.toLowerCase();

	}

}