package gameFourPlayerMode.gameModel;

public class Cell {
	Player player;
	boolean highlighted;

	public Cell() {
		player = Player.EMPTY;
		highlighted = false;
	}

	// changes type of piece in here
	public void setPlayer(Player player) {
		this.player = player;
	}

	// returns type of piece in here
	public Player getPlayer() {
		return player;
	}

	// returns true if board is highlighted
	public boolean getHighlighted() {
		return highlighted;
	}

	// sets highlight
	public void setHighlighted(boolean b) {
		highlighted = b;
	}

	// returns true if this cell is in give array
	public boolean isInArray(Cell[] array) {
		for (Cell cell : array)
			if (cell == this)
				return true;
		return false;
	}

	public static boolean allAreP(Cell[] cells, Player p) {
		for (Cell cell : cells)
			if (cell.getPlayer() != p)
				return false;
		return true;
	}
}
