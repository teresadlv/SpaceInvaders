package tp1.logic;

import java.util.Random;

import tp1.logic.gameobjects.GameObject;

public interface GameWorld {

	public void addObject(GameObject object);

	public void removeObject(GameObject object);

	public void receivePoints(int puntos);

	public void checkAttacksBy(GameObject object);

	public boolean existLaser();

	public Random getRandom();

	public Level getLevel();
	
	public void enableShockWave();
	
	public boolean getShockWaveState();

}
