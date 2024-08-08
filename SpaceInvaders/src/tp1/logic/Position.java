package tp1.logic;

import java.util.Objects;

/**
 * 
 * Immutable class to encapsulate and manipulate positions in the game board
 * 
 */
public class Position {

	private final int col;
	private final int row;

	public Position(int x, int y) {
		this.col = x;
		this.row = y;
	}

	@Override
	public int hashCode() {
		return Objects.hash(col, row);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		return col == other.col && row == other.row;
	}

	private Position displace(Move dir) {
		Position p = new Position(this.col + dir.getX(), this.row + dir.getY());
		return p;
	}

	public Position move(Move dir) {
		return displace(dir);
	}

	public boolean hasInvalidPos() {
		return this.row < 0 || this.col < 0 || this.col >= Game.DIM_X || this.row >= Game.DIM_Y;
	}

	public boolean inBorder() {
		// Comprueba todos los bordes salvo la parte superior del tablero
		return this.col == 0 || this.col == Game.DIM_X - 1 || this.row == Game.DIM_Y - 1;
	}

	public boolean finalRow() {
		return this.row == Game.DIM_Y - 1;
	}

	public static Position adyacent(Position pos, int i, int j) {
		return new Position(pos.col + i, pos.row + j);
	}
	public String toString() {
		return "("+this.col+", "+this.row+")";
	}

}