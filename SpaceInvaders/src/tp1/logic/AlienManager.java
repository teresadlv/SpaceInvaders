package tp1.logic;

import tp1.control.InitialConfiguration;
import tp1.exceptions.GameException;
import tp1.exceptions.InitializationException;
import tp1.logic.gameobjects.AlienShip;
import tp1.logic.gameobjects.DestroyerAlien;
import tp1.logic.gameobjects.RegularAlien;
import tp1.logic.gameobjects.ShipFactory;
import tp1.logic.gameobjects.Ufo;
import tp1.view.Messages;

public class AlienManager {

	private Level level;
	private GameWorld game;
	private boolean squadInFinalRow;
	private int shipsOnBorder;
	private boolean onBorder;
	private int remainingGroupAliens; // VARIABLE QUE CONTROLA EL MOVMIENTO DE LOS ALIENS QUE SE MUEVEN CONJUNTAMENTE
	private int remainingAliens; // VARIABLE QUE INDICA EL TOTAL DE ALIENS
	private Ufo ufo; //DECLARAMOS AQUI EL UFO PARA PODER ADEMAS DEVOLVER SU INFO

	public AlienManager(GameWorld game, Level level) {
		this.level = level;
		this.game = game;
		this.ufo = new Ufo(this.game);
		this.remainingAliens = 0;
		this.shipsOnBorder = 0;
		this.onBorder = false;
		this.squadInFinalRow = false;
		this.remainingGroupAliens = 0;
	}

	// INITIALIZER METHODS
	public GameObjectContainer initialize(InitialConfiguration conf) throws GameException {
		GameObjectContainer container = new GameObjectContainer();
		if (conf == InitialConfiguration.NONE || conf == null) {
			initializeRegularAliens(container);
			initializeDestroyerAliens(container);
		} else {
			costumizedInitialization(container, conf);
		}
		initializeOvni(container);
		return container;
	}

	private void initializeRegularAliens(GameObjectContainer container) {
		int capacidad = level.getRegularAliens();
		int i = 0, j = 2, k = 1;
		while (i < capacidad) {
			if (i == 4) {
				j = 2;
				k++;
			}
			container.add(new RegularAlien(this.game, new Position(j, k), this));
			this.remainingAliens++;
			this.remainingGroupAliens++;
			j++;
			i++;
		}
	}

	private void initializeDestroyerAliens(GameObjectContainer container) {
		int capacidad = level.getDestroyerAliens();
		boolean esVacio = (capacidad == 2);
		int posX;
		int posY = (level.getRegularAliens() / 4) + 1;

		for (int j = 0; j < capacidad; j++) {
			if (esVacio) {
				posX = Game.DIM_X / 2 - 1 + j;
			} else {
				posX = Game.DIM_X / 2 - 2 + j;
			}
			Position pos = new Position(posX, posY);
			container.add(new DestroyerAlien(this.game, pos, this));
			this.remainingAliens++;
			this.remainingGroupAliens++;
		}
	}

	private void initializeOvni(GameObjectContainer container) { 
		container.add(this.ufo);
	}

	private void costumizedInitialization(GameObjectContainer container, InitialConfiguration conf)
			throws NumberFormatException, GameException {
		for (String shipDescription : conf.getShipDescription()) {
			String[] words = shipDescription.toLowerCase().trim().split("\s+");

			// PRIMERO COMPROBAMOS QUE WORDS TENGA EL NUMERO DE PARAMETROS CORRECTO
			if (words.length == 1) {
				throw new InitializationException(Messages.INITIAL_CONFIGURATION_ERROR + "\n"
						+ Messages.INCORRECT_ENTRY.formatted(words[0].toUpperCase()));
			} else if (words.length == 2) {
				throw new InitializationException(Messages.INITIAL_CONFIGURATION_ERROR + "\n"
						+ Messages.INCORRECT_ENTRY.formatted(words[0].toUpperCase() + " " + words[1].toUpperCase()));
			} else if (words.length > 3) {
				throw new InitializationException(Messages.INITIAL_CONFIGURATION_ERROR);
			}

			// DESPUES COMPROBAMOS QUE LOS NUMEROS INTRODUCIDOS SEAN DEL TIPO INTEGER
			try {
				Integer.parseInt(words[1]);
				Integer.parseInt(words[2]);
			} catch (NumberFormatException e) {
				throw new InitializationException(Messages.INITIAL_CONFIGURATION_ERROR + "\n"
						+ Messages.INVALID_POSITION.formatted(words[1].toUpperCase(), words[2]));
			}

			// AHORA COMPROBAMOS QUE LOS INTEGERS SEAN VALIDOS
			if (outOfBounds(words[1]) || outOfBounds(words[2])) {
				throw new InitializationException(Messages.INITIAL_CONFIGURATION_ERROR + "\n"
						+ Messages.OFF_WORLD_POSITION.formatted("(" + words[1] + ", " + words[2] + ")"));
			} else { // DEVOLVEMOS SI ES CORRECTO
				AlienShip ship = ShipFactory.spawnAlienShip(words[0], game,
						new Position(Integer.valueOf(words[1]), Integer.valueOf(words[2])), this);
				container.add(ship);
				this.remainingAliens++;
				this.remainingGroupAliens++;
			}

		}
	}

	public String getUfoInfo() { // PARA DEVOLVER LA INFO DEL UFO YA QUE LA FACTOY NO LA TIENE
		StringBuilder buffer = new StringBuilder();
		buffer.append(ufo.getInfo());
		return buffer.toString();
	}

	public boolean outOfBounds(String w) {
		return Integer.valueOf(w) == Game.DIM_X || Integer.valueOf(w) == Game.DIM_Y || Integer.valueOf(w) < 0;
	}

	public void decreaseOnBorder() {
		this.shipsOnBorder--;
		if (this.shipsOnBorder == 0)
			this.onBorder = false;
	}

	public void shipOnBorder() {
		if (!this.onBorder) {
			this.onBorder = true;
			this.shipsOnBorder = this.remainingGroupAliens;
		}
	}

	// comprobacion tablero

	public boolean onBorder() {
		return this.onBorder;
	}

	// consultoras
	public int getRemainingAliens() {
		return this.remainingAliens;
	}

	public int getRemainingDesAndReg() {
		return this.remainingGroupAliens;
	}

	public int getShipsOnBorder() {
		return this.shipsOnBorder;
	}

	// comprobacion
	public boolean allAlienDead() {
		return getRemainingAliens() == 0;
	}

	public void alienDead() {
		this.remainingAliens--;
	}

	public void groupAliensDead() {
		this.remainingGroupAliens--;
	}

	public int getCyclesToMove() {
		return this.level.getNumCyclesToMoveOneCell();
	}

	public void setSquadInFinalRow() {
		this.squadInFinalRow = true;
	}

	public boolean finalRowReached() {
		return squadInFinalRow;
	}

	public double getBombFrequency() {
		return this.level.getBombFrequency();
	}

	public int getSpeed() {
		return this.level.getNumCyclesToMoveOneCell();
	}

}