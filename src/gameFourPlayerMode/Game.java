package gameFourPlayerMode;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import gameFourPlayerMode.gameModel.Board;
import gameFourPlayerMode.gameModel.Cell;
import gameFourPlayerMode.gameModel.GameSession;
import gameFourPlayerMode.gameModel.Player;
import gameFourPlayerMode.gameModel.TurnData;
import gameFourPlayerMode.view.GameWindow;

public class Game {
	public GameSession sessionData;
	public Board board;
	public TurnData turn;
	private GameWindow gameView;
	private ArrayList<Player> turnQ;

	// setting game session
	public Game(GameSession sessionData) {
		this.sessionData = sessionData.copyMain();
		this.board = new Board(sessionData.n, sessionData.m);
		this.turnQ = new ArrayList<Player>();
		turnQ.add(Player.PLAYER2);
		turnQ.add(Player.PLAYER3);
		turnQ.add(Player.PLAYER4);
		turnQ.add(Player.PLAYER1);
		this.turn = new TurnData(Player.PLAYER1);
	}

	// game logic
	public void cellClicked(int x, int y) {
		Cell cell = board.getCell(x, y);
		// if player did jump before
		if (turn.onJump) {
			// if player can jump from current cell to this cell
			if (cell.isInArray(turn.jumpableCells)) {
				board.move(turn.selectedCell, cell);
				deselectCell();
				selectCell(x, y);
			}
		} else {
			// if player havent select a cell yet
			if (turn.selectedCell == null) {
				if (cell.getPlayer() == turn.player) {
					selectCell(x, y);
				}
			} else {
				// if player can walk or jump to this cell from current cell
				if (cell.isInArray(turn.walkableCells) || cell.isInArray(turn.jumpableCells)) {
					// if player can walk to this cell
					if (cell.isInArray(turn.walkableCells)) {
						board.move(turn.selectedCell, cell);
						deselectCell();
						changeTurn();
						// if player can jump to this cell
					} else {
						turn.onJump = true;
						board.move(turn.selectedCell, cell);
						deselectCell();
						selectCell(x, y);
					}
					// if cant go to this cell from current cell
				} else {
					// if cell is another piece of same player
					if (cell.getPlayer() == turn.player && cell != turn.selectedCell) {
						deselectCell();
						cellClicked(x, y);
					} else
						deselectCell();
				}

			}
		}
		// updates game gui
		gameView.refresh();
	}

	// when pass button is pressed
	public void endMoveClicked() {
		changeTurn();
	}

	// changes turn
	public void changeTurn() {
		deselectCell();
		// change turn data
		sessionData.addMove(turn.player);
		
		//if this player won shows message
		if (board.haveWon(turn.player)) {
			sessionData.winner.add(turn.player);
			String msg = "#" + (sessionData.winner.size()) + " " + sessionData.getPlayerName(turn.player);
			JOptionPane.showMessageDialog(null, msg);
			turnQ.remove(turn.player);
		}
		
		// if noone is in list game is ended
		if (turnQ.size() == 0) {
			gameEnded();
		} else {
			//changes turn
			Player temp = turnQ.get(0);
			turn = new TurnData(temp);
			updateQ();

			// update gui
			gameView.refresh();
		}
	}

	// celects a cell
	public void selectCell(int x, int y) {
		// update model
		turn.selectedCell = board.getCell(x, y);
		turn.walkableCells = board.getWalkable(x, y);
		turn.jumpableCells = board.getJumpable(x, y);
		// highlight reachable cells
		if (!turn.onJump)
			board.highlightCells(turn.walkableCells);
		board.highlightCells(turn.jumpableCells);
	}

	// deselect selected cell
	public void deselectCell() {
		// if a cell is selected
		if (turn.selectedCell != null) {
			// inhighlight cells
			turn.selectedCell.setHighlighted(false);
			board.offCells(turn.walkableCells);
			board.offCells(turn.jumpableCells);
			// update model
			turn.selectedCell = null;
			turn.walkableCells = null;
			turn.jumpableCells = null;
		}
	}

	// when exit button is pressed
	public void exitCliked() {
		int choice = JOptionPane.showConfirmDialog(null, "Do you want to End Game ?");
		if (choice == JOptionPane.YES_OPTION)
			gameEnded();
	}

	// when restart button is pressed
	public void restartCliked() {
		int choice = JOptionPane.showConfirmDialog(null, "Do you want to Restart Game ?");
		if (choice == JOptionPane.YES_OPTION) {
			sessionData.restartGame = true;
			gameEnded();
		}
	}

	// returns session data
	public GameSession run() {
		this.gameView = new GameWindow(this);
		synchronized (this) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return sessionData;
	}
	
	//ending game
	private void gameEnded() {
		//empty turn list
		while(turnQ.size() > 0) {
			sessionData.winner.add(turnQ.get(0));
			turnQ.remove(0);
			sessionData.ended--;
		}
		
		//terminate window
		gameView.setVisible(false);
		gameView.dispose();

		synchronized (this) {
			notifyAll();
		}

	}

	// nafare ghabli ro mibare akhare saf :D
	void updateQ() {
		ArrayList<Player> temp = new ArrayList<Player>();
		for (int i = 1; i < turnQ.size(); i++)
			temp.add(turnQ.get(i));
		temp.add(turnQ.get(0));
		turnQ = temp;
	}
}
