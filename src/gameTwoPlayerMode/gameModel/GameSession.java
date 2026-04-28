package gameTwoPlayerMode.gameModel;

//session model
public class GameSession {
	public String playerOneName;
	public String playerTwoName;
	public int playerOnePlayedTurns;
	public int playerTwoPlayedTurns;
	public Player winner;
	public boolean restartGame;
	public int n;
	public int m;
	
	public GameSession(String name1, String name2, int n, int m) {
		this.n = n;
		this.m = m;
		this.playerOneName = name1;
		this.playerTwoName = name2;
		this.playerOnePlayedTurns = 0;
		this.playerTwoPlayedTurns = 0;
		this.winner = Player.EMPTY;
		restartGame = false;
	}
	
	// returns an object of Session data with same player names and m and n
	public GameSession copyMain() {
		return new GameSession(playerOneName, playerTwoName, n, m);
	}
}
