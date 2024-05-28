package logic;

public class Position {
	int row, col;
	String type;

	public Position(int row, int col, String type) {
		this.row = row;
		this.col = col;
		this.type = type;
	}

	public String toString() {
		return "[row =  " + row + ", col = " + col + ", type = " + type + "]";
	}

	public boolean equals(Position pos) {
		if (pos.row == row && pos.col == col && pos.type == type) {
			return true;
		}
		return false;
	}
}