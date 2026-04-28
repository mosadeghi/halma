package gameTwoPlayerMode.gameModel;
import java.util.ArrayList;

// Board model
public class Board {
	private Cell[][] cells;
	private int n;
	private int m;
	private int l;

	public Board(int n, int m) {
		this.n = n;
		this.m = m;
		this.l = (m * m + m) / 2; // number of pieces each player have
		// making 2d array
		cells = new Cell[n][n];
		// adding Cell objects to it
		for (int y = 0; y < n; y++)
			for (int x = 0; x < n; x++)
				cells[x][y] = new Cell();

		// palce pieces of player 1
		for (Cell cell : getAreaOne()) {
			cell.setPlayer(Player.PLAYER1);
		}

		// palce pieces of player 2
		for (Cell cell : getAreaTwo()) {
			cell.setPlayer(Player.PLAYER2);
		}

	}
	
	//returns n
	public int getN() {
		return n;
	}

	//move a piece from a cell to other one
	public void move(Cell a,Cell b) {
		b.setPlayer(a.getPlayer());
		a.setPlayer(Player.EMPTY);
	}
	
	// return cell object in position x,y if it exists. else null
	public Cell getCell(int x, int y) {
		if (x >= 0 && x < n && y >= 0 && y < n)
			return cells[x][y];
		else
			return null;
	}

	// returns all cell objects located in first players house
	public Cell[] getAreaOne() {
		Cell[] selectedCells = new Cell[l];
		int i = 0;
		for (int x = n - 1; x >= n - m; x--)
			for (int y = n - 1; x + y >= 2 * n - m - 1; y--)
				selectedCells[i++] = getCell(x, y);
		return selectedCells;
	}

	// returns all cell objects located in second players house
	public Cell[] getAreaTwo() {
		Cell[] selectedCells = new Cell[l];
		int i = 0;
		for (int x = 0; x < m; x++)
			for (int y = 0; y < m - x; y++)
				selectedCells[i++] = getCell(x, y);
		return selectedCells;
	}

	//returns an array of cells that a piece can walk to from position (x0,y0)
	public Cell[] getWalkable(int x0, int y0) {
		Cell cell0 = getCell(x0, y0);
		ArrayList<Cell> temp = new ArrayList<Cell>();
		for (int y=y0-1; y <= y0+1;y++)
			for (int x =x0-1; x<= x0+1;x++) {
				Cell cell = getCell(x, y); 
				if (cell != null && cell != cell0 && cell.getPlayer() == Player.EMPTY)
					temp.add(getCell(x, y));
			}
		Cell[] cells = new Cell[temp.size()];
		cells = temp.toArray(cells);
		return cells;
	}

	//returns an array of cells that a piece can jump to from position (x0,y0)
	public Cell[] getJumpable(int x0, int y0) {
		Cell cell0 = getCell(x0, y0);
		ArrayList<Cell> temp = new ArrayList<Cell>();
		for (int y=y0-1; y <= y0+1;y++)
			for (int x =x0-1; x<= x0+1;x++) {
				Cell cell = getCell(x, y); 
				if (cell != null && cell != cell0 && cell.getPlayer() != Player.EMPTY) {
					Cell candid = getCell(2*x-x0,2*y-y0);
					if(candid != null && candid.getPlayer() == Player.EMPTY)
						temp.add(candid);
				}
			}
		Cell[] cells = new Cell[temp.size()];
		cells = temp.toArray(cells);
		return cells;
	}
	
	// return winner, if no one won yet returns Player.EMPTY
	public Player getWinner() {
		// check if player 1 won
		boolean playerOneWon = true;
		for (Cell cell : getAreaTwo())
			if (cell.getPlayer() != Player.PLAYER1) {
				playerOneWon = false;
				break;
			}
		if (playerOneWon)
			return Player.PLAYER1;

		// check if player 2 won
		boolean playerTwoWon = true;
		for (Cell cell : getAreaOne())
			if (cell.getPlayer() != Player.PLAYER2) {
				playerTwoWon = false;
				break;
			}
		if (playerTwoWon)
			return Player.PLAYER2;

		// return EMPTY if no one won yet
		return Player.EMPTY;
	}

	// highlight given cells
	public void highlightCells(Cell[] cells) {
		for (Cell cell : cells) {
			cell.setHighlighted(true);
		}
	}

	// uhighlight given cells
	public void offCells(Cell[] cells) {
		for (Cell cell : cells) {
			cell.setHighlighted(false);
		}
	}
}
