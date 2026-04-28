package gameTwoPlayerMode.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gameTwoPlayerMode.gameModel.Cell;
import gameTwoPlayerMode.gameModel.Player;

// shows cell using cell model data
public class CellViewer extends JPanel{
	private static final long serialVersionUID = -8277865500149130067L;
	static Color[] color = {Color.white,new Color(242,99,70)};
	static String PATH = "data\\images\\";
	
	public CellViewer(int x, int y, Cell cell,int width, int height) {
		setPreferredSize(new Dimension(width,height));
		String path = PATH;
		if(x%2 == y%2)
			setBackground(color[0]);
		else
			setBackground(color[1]);
		
		if(cell.getHighlighted()) {
			if (cell.getPlayer() == Player.EMPTY) {
				path += "s.png";
			}
		}else {
			switch (cell.getPlayer()) {
			case PLAYER1:
				path += "p1.png";
				break;
			case PLAYER2:
				path += "p2.png";
				break;
			default:
				break;
			}
		}
		if(!path.equalsIgnoreCase(PATH)) {
			BufferedImage img = null;
			try {
			    img = ImageIO.read(new File(path));
			} catch (IOException e) {
			    e.printStackTrace();
			}
			
			Image scaledImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			ImageIcon imageIcon = new ImageIcon(scaledImage);
			add(new JLabel(imageIcon));
		}
	}
	
}
