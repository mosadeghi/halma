package gameFourPlayerMode.gameModel;

import java.util.ArrayList;

//session model
public class GameSession {
	public String playerOneName;
	public String playerTwoName;
	public String playerThreeName;
	public String playerFourName;

	public int playerOnePlayedTurns;
	public int playerTwoPlayedTurns;
	public int playerThreePlayedTurns;
	public int playerFourPlayedTurns;

	public ArrayList<Player> winner;
	public boolean restartGame;
	public int n;
	public int m;
	public int ended = 4;

	public GameSession(String name1, String name2, String name3, String name4, int n, int m) {
		this.n = n;
		this.m = m;
		this.playerOneName = name1;
		this.playerTwoName = name2;
		this.playerThreeName = name3;
		this.playerFourName = name4;

		this.playerOnePlayedTurns = 0;
		this.playerTwoPlayedTurns = 0;
		this.playerThreePlayedTurns = 0;
		this.playerFourPlayedTurns = 0;

		this.winner = new ArrayList<Player>();
		restartGame = false;
	}

	// add to move number of player p
	public void addMove(Player p) {
		if (p == Player.PLAYER1)
			playerOnePlayedTurns++;
		else if (p == Player.PLAYER2)
			playerTwoPlayedTurns++;
		else if (p == Player.PLAYER3)
			playerThreePlayedTurns++;
		else if (p == Player.PLAYER4)
			playerFourPlayedTurns++;
	}

	// get number of moves of player p
	public int getMove(Player p) {
		switch (p) {
		case PLAYER1:
			return playerOnePlayedTurns;
		case PLAYER2:
			return playerTwoPlayedTurns;
		case PLAYER3:
			return playerThreePlayedTurns;
		case PLAYER4:
			return playerFourPlayedTurns;
		default:
			return 0;
		}
	}

	// get name of player p
	public String getPlayerName(Player p) {
		switch (p) {
		case PLAYER1:
			return playerOneName;
		case PLAYER2:
			return playerTwoName;
		case PLAYER3:
			return playerThreeName;
		case PLAYER4:
			return playerFourName;
		default:
			return null;
		}
	}

	//returns an object of gamesession with same names and n and m.
	public GameSession copyMain() {
		return new GameSession(playerOneName, playerTwoName, playerThreeName, playerFourName, n, m);
	}
}
