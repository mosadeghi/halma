package gameTwoPlayerMode;

import javax.swing.JOptionPane;

import gameTwoPlayerMode.gameModel.Board;
import gameTwoPlayerMode.gameModel.Cell;
import gameTwoPlayerMode.gameModel.GameSession;
import gameTwoPlayerMode.gameModel.Player;
import gameTwoPlayerMode.gameModel.TurnData;
import gameTwoPlayerMode.view.GameWindow;

public class Game {
	public GameSession sessionData;
	public Board board;
	public TurnData turn;
	private GameWindow gameView;

	// setting game session
	public Game(GameSession sessionData) {
		this.sessionData = sessionData.copyMain();
		this.board = new Board(sessionData.n, sessionData.m);
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
		// win check
		if (board.getWinner() != Player.EMPTY)
			sessionData.winner = board.getWinner();

		deselectCell();
		// change turn data
		if (turn.player == Player.PLAYER1) {
			sessionData.playerOnePlayedTurns++;
			turn = new TurnData(Player.PLAYER2);
		} else {
			sessionData.playerTwoPlayedTurns++;
			turn = new TurnData(Player.PLAYER1);
		}
		//update gui
		gameView.refresh();

		// ends game if there is a winner
		if (sessionData.winner != Player.EMPTY)
			gameEnded();
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
			//update model
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

	
	private void gameEnded() {
		// end game message
		String message;
		switch (sessionData.winner) {
		case PLAYER1:
			message = sessionData.playerOneName + " won the game in " + sessionData.playerOnePlayedTurns + " turns.";
			break;
		case PLAYER2:
			message = sessionData.playerTwoName + " won the game in " + sessionData.playerTwoPlayedTurns + " turns.";
			break;
		default:
			message = "NO WINNER !";
			break;
		}

		if (sessionData.winner != Player.EMPTY)
			JOptionPane.showMessageDialog(null, message);

		// terminate window
		gameView.setVisible(false);
		gameView.dispose();

		synchronized (this) {
			notify();
		}

	}
}
