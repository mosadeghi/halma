package gameTwoPlayerMode.view;

import java.awt.GridLayout;
import javax.swing.JPanel;

import gameTwoPlayerMode.Game;
import gameTwoPlayerMode.gameModel.Board;

// show board using board model data
public class BoardDisplayer extends JPanel {
	private static final long serialVersionUID = 7572580951678776020L;
	private int cellWidth;
	private int cellHeight;

	public BoardDisplayer(Board board, Game gameSession, int width,int height) {
		int n = board.getN();
		this.cellWidth = (int)(width/n);
		this.cellHeight = (int)(height/n);
		
		setLayout(new GridLayout(n,n));
		for (int y=0;y<n;y++)
			for(int x=0;x<n;x++)
				add(new CellViewer(x,y,board.getCell(x, y),cellWidth,cellHeight));
	}
}
