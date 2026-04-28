package gameTwoPlayerMode.gameModel;

// turn model
public class TurnData {
	public Player player;
	public boolean onJump;
	public Cell selectedCell;
	public Cell[] walkableCells;
	public Cell[] jumpableCells;

	public TurnData(Player player) {
		this.player = player;
		this.onJump = false;
		this.selectedCell = null;
		this.walkableCells = null;
		this.jumpableCells = null;
	}
}
