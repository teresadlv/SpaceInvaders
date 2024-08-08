package tp1.logic;

import java.util.ArrayList;
import java.util.List;
import tp1.logic.gameobjects.GameItem;
import tp1.logic.gameobjects.GameObject;

public class GameObjectContainer {

	private List<GameObject> objects;

	public GameObjectContainer() {
		objects = new ArrayList<>();
	}

	public void add(GameObject object) {
		objects.add(object);
	}

	public void remove(GameObject object) {
		objects.remove(object);
	}

	public void computerActions() {
		int i = 0;
		while (i < this.objects.size()) {
			GameObject object = this.objects.get(i);
			object.computerAction();
			i++;
		}
	}

	public void automaticMoves() {
		for (int i = objects.size() - 1; i >= 0; i--) {
			GameObject object = objects.get(i);
			object.automaticMove();
			checkAttackTo(object);
		}
		removeDeadObjects();
	}

	private void removeDeadObjects() {
		for (int i = objects.size() - 1; i >= 0; i--) {
			GameObject object = objects.get(i);
			if (!object.isAlive()) {
				remove(object);
			}
		}
	}

	public void checkAttackTo(GameItem other) {
		int j = objects.indexOf(other);
		boolean salir = false;
		int i = 0;
		while (i < objects.size() && !salir) {
			GameObject object = objects.get(i);
			if (i != j && other.performAttack(object)) {
				salir = true;
			}
			i++;
		}
	}

	public void checkAttackBy(GameObject other) {
		int j = objects.indexOf(other);
		boolean salir = false;
		int i = 0;
		while (i < objects.size() && !salir) {
			GameObject object = objects.get(i);
			if (i != j && object.performAttack(other)) {
				salir = true;
			}
			i++;
		}
	}

	public String pintaLista(Position pos) {
		String str = "";
		for (GameObject object : objects) {
			if (object.isOnPosition(pos) && object.isAlive()) {
				str = object.toString();
			}
		}
		return str;
	}

}