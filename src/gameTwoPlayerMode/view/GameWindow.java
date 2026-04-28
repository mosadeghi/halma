package gameTwoPlayerMode.view;

import javax.swing.JFrame;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import gameTwoPlayerMode.Game;
import gameTwoPlayerMode.gameModel.Board;
import gameTwoPlayerMode.gameModel.GameSession;
import gameTwoPlayerMode.gameModel.Player;

import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;


public class GameWindow extends JFrame {
	private static final long serialVersionUID = -8538371649587893617L;
	private Game gameSession;
	private Board board;
	private GameSession sessionData;
	public int w;
	public JButton btnEndMove;
	public JPanel pnlBoard;
	private JPanel pnlControl;
	private JLabel turnMessage;
	private JPanel pnlMenu;

	public GameWindow(Game gameSession) {
		// fetching data
		this.gameSession = gameSession;
		this.board = gameSession.board;
		this.sessionData = gameSession.sessionData;
		// calculate window size
		int n = sessionData.n;
		int width = w = (n<8)?100*n:800;
		int height = (n<8)?width+100:900;
		width = w = (n<4)?150*n:width;
		height = (n<4)?width+100:height;
		
		setWindow();
	}

	private void setWindow() {
		//setSize(windowSize);
		setResizable(false);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setTitle("HALMA || "+sessionData.playerOneName+" VS. "+sessionData.playerTwoName);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{sessionData.n, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		pnlBoard = new JPanel();
		GridBagConstraints gbc_pnlBoard = new GridBagConstraints();
		gbc_pnlBoard.insets = new Insets(0, 0, 5, 0);
		gbc_pnlBoard.fill = GridBagConstraints.BOTH;
		gbc_pnlBoard.gridx = 0;
		gbc_pnlBoard.gridy = 0;
		getContentPane().add(pnlBoard, gbc_pnlBoard);
		pnlBoard.setLayout(new GridLayout(1, 1));
		this.pnlMenu = new JPanel();
		GridBagConstraints gbc_pnlMenu = new GridBagConstraints();
		gbc_pnlMenu.fill = GridBagConstraints.BOTH;
		gbc_pnlMenu.gridx = 0;
		gbc_pnlMenu.gridy = 1;
		getContentPane().add(pnlMenu, gbc_pnlMenu);
		pnlMenu.setLayout(new GridLayout(2,1));
		
		JPanel panel = new JPanel();
		pnlMenu.add(panel);
		
		this.turnMessage = new JLabel("FirstPlayers Turn");
		turnMessage.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(turnMessage);
		
		this.pnlControl = new JPanel();
		pnlMenu.add(pnlControl);
		
		btnEndMove = new JButton("PASS");
		btnEndMove.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				gameSession.endMoveClicked();
			}
		});
		pnlControl.add(btnEndMove);
		
		JButton btnRestart = new JButton("RESTART");
		btnRestart.setHorizontalAlignment(SwingConstants.RIGHT);
		btnRestart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				gameSession.restartCliked();
			}
		});
		pnlControl.add(btnRestart);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				gameSession.exitCliked();
			}
		});
		pnlControl.add(btnExit);
		
		pnlBoard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				gameSession.cellClicked(e.getX()/(w/sessionData.n), e.getY()/(w/sessionData.n));
			}
		});
		
		refresh();
		setVisible(true);
	}
	
	public void refresh() {
		//refresh board
		pnlBoard.removeAll();
		pnlBoard.add(new BoardDisplayer(board,gameSession,w,w));
		pnlBoard.repaint();
		
		if(gameSession.turn.onJump)
			btnEndMove.setEnabled(true);
		else
			btnEndMove.setEnabled(false);
		
		
		String playerName=null;
		int moves;
		if(gameSession.turn.player == Player.PLAYER1) {
			playerName = gameSession.sessionData.playerOneName;
			moves = gameSession.sessionData.playerOnePlayedTurns+1;
		}
		else {
			playerName = gameSession.sessionData.playerTwoName;
			moves = gameSession.sessionData.playerTwoPlayedTurns+1;
		}
			
		turnMessage.setText(playerName+"'s Turn "+moves);
			
		pnlMenu.revalidate();
		pack();
	}
}
