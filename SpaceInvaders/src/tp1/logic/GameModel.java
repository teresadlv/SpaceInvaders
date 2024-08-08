package tp1.logic;

import tp1.control.InitialConfiguration;
import tp1.exceptions.GameException;

public interface GameModel {

	public void move(Move move) throws GameException;

	public void shootLaser() throws GameException;

	public void shootShockWave() throws GameException;

	public void reset(InitialConfiguration config) throws GameException;

	public void update();

	public void exit();

	public String infoToString();

	public void shootSuperLaser() throws GameException;

	public boolean isFinished();

}
