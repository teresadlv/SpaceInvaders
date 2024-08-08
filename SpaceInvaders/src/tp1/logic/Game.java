package tp1.logic;

import java.util.Random;
import tp1.control.InitialConfiguration;
import tp1.exceptions.GameException;
import tp1.exceptions.NoShockWaveException;
import tp1.logic.gameobjects.GameObject;
import tp1.logic.gameobjects.ShipFactory;
import tp1.logic.gameobjects.ShockWave;
import tp1.logic.gameobjects.UCMShip;
import tp1.logic.gameobjects.Weapon;
import tp1.view.Messages;

public class Game implements GameStatus, GameModel, GameWorld {

	public static final int DIM_X = 9;
	public static final int DIM_Y = 8;

	private Level level;
	private long seed;
	private Random rand;
	private int cycle;
	private AlienManager alienManager;
	private UCMShip player;
	private GameObjectContainer lista;
	private boolean shockWave;

	private static boolean end;

	public Game(Level level, long seed) throws GameException {
		this.level = level;
		this.seed = seed;
		initGame(InitialConfiguration.NONE);
	}

	private void initGame(InitialConfiguration config) throws GameException {
		this.rand = new Random(seed);
		this.cycle = 0;
		alienManager = new AlienManager(this, level);
		this.lista = alienManager.initialize(config);
		this.player = new UCMShip(this);
		lista.add(player);
		this.shockWave = false;
	}

	/*----- Métodos de la interfaz GameModel -----*/

	@Override
	public void move(Move move) throws GameException {
		this.player.moverEnDireccion(move);
	}

	@Override
	public void shootLaser() throws GameException {
		this.player.createLaser();
	}

	@Override
	public void shootSuperLaser() throws GameException {
		this.player.shootSuperLaser();
	}

	@Override
	public void shootShockWave() throws GameException {
		if (!shockWave) {
			throw new NoShockWaveException(Messages.SHOCKWAVE_ERROR);
		} else {
			weaponAttack(new ShockWave(this));
			this.shockWave = false;
		}
	}

	private void weaponAttack(Weapon weapon) {
		this.lista.checkAttackTo(weapon);
	}

	public boolean getShockWaveState() {
		return this.shockWave;
	}

	@Override
	public void enableShockWave() {
		this.shockWave = true;
	}

	@Override
	public void reset(InitialConfiguration config) throws GameException {
		initGame(config);
	}

	@Override
	public void update() {
		this.cycle++;
		this.lista.computerActions();
		this.lista.automaticMoves();
	}

	@Override
	public void exit() {
		end = true;
	}

	@Override
	public String infoToString() {
		StringBuilder buffer = new StringBuilder();
		buffer.append(ShipFactory.getInfoAlienShip()).append(this.player.getInfo()).append(alienManager.getUfoInfo());
		return buffer.toString();
	}

	@Override
	public boolean isFinished() {
		return playerWin() || aliensWin() || end;
	}

	/*----- Métodos de la interfaz GameStatus -----*/

	@Override
	public String positionToString(int col, int row) {
		Position pos = new Position(col, row);
		return this.lista.pintaLista(pos);
	}

	@Override
	public String stateToString() {
		return this.player.stateToString();
	}

	@Override
	public boolean playerWin() {
		return (this.alienManager.allAlienDead());
	}

	@Override
	public boolean aliensWin() {
		return (!player.isAlive() || this.alienManager.finalRowReached());
	}

	@Override
	public int getCycle() {
		return this.cycle;
	}

	@Override
	public int getRemainingAliens() {
		return alienManager.getRemainingAliens();
	}

	/*----- Métodos de la interfaz GameWorld -----*/

	@Override
	public void addObject(GameObject object) {
		this.lista.add(object);
	}
	
	@Override
	public void removeObject(GameObject object) {
		this.lista.remove(object);
	}

	@Override
	public void receivePoints(int puntos) {
		this.player.addPoints(puntos);
	}

	/*
	 * Metodo que comprueba la collision cuando las naves descienden
	 */
	@Override
	public void checkAttacksBy(GameObject object) {
		this.lista.checkAttackBy(object);
	}

	@Override
	public boolean existLaser() {
		return this.player.existLaser();
	}

	@Override
	public Random getRandom() {
		return this.rand;
	}

	@Override
	public Level getLevel() {
		return this.level;
	}

}