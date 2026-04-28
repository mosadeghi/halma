package menus;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class MainMenu extends JFrame {
	private static final long serialVersionUID = 4582507677782250797L;
	private ActionOption actionOption;
	private Object object;

	public MainMenu(Object object) {
		this.object = object;
		this.actionOption = ActionOption.CONTINUE;
		
		// Frame settings
		setTitle("HALMA || A BOARD GAME");
		setSize(400, 600);
		setResizable(false);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		
		// setting frames layout
		GridBagLayout framesLayout = new GridBagLayout();
		framesLayout.columnWidths = new int[] { 0, 0, 0, 0 };
		framesLayout.rowHeights = new int[] { 0, 0 };
		framesLayout.columnWeights = new double[] { 1.0, 2.0, 1.0, Double.MIN_VALUE };
		framesLayout.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		getContentPane().setLayout(framesLayout);

		
		// setting centered panel
		JPanel centeredPanel = new JPanel();
		GridBagConstraints gbc_centeredPanel = new GridBagConstraints();
		gbc_centeredPanel.insets = new Insets(0, 0, 0, 5);
		gbc_centeredPanel.fill = GridBagConstraints.BOTH;
		gbc_centeredPanel.gridx = 1;
		gbc_centeredPanel.gridy = 0;
		getContentPane().add(centeredPanel, gbc_centeredPanel);
		
		// setting centered panels layout
		GridBagLayout gbl_centeredPanel = new GridBagLayout();
		gbl_centeredPanel.columnWidths = new int[] { 0, 0 };
		gbl_centeredPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_centeredPanel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_centeredPanel.rowWeights = new double[] { 1, 2, 5, 0.5, 0.0, 3.5, Double.MIN_VALUE };
		centeredPanel.setLayout(gbl_centeredPanel);

		// setting Title panel
		JPanel pnlTtitle = new JPanel();
		GridBagConstraints gbc_pnlTtitle = new GridBagConstraints();
		gbc_pnlTtitle.insets = new Insets(0, 0, 5, 0);
		gbc_pnlTtitle.gridx = 0;
		gbc_pnlTtitle.gridy = 1;
		centeredPanel.add(pnlTtitle, gbc_pnlTtitle);
		//setting title panels layout
		pnlTtitle.setLayout(new BorderLayout(0, 0));

		//setting title
		JLabel lblNewLabel = new JLabel("HALMA");
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 25));
		pnlTtitle.add(lblNewLabel, BorderLayout.CENTER);

		// setting buttons container panel
		JPanel pnlIButtons = new JPanel();
		GridBagConstraints gbc_pnlIButtons = new GridBagConstraints();
		gbc_pnlIButtons.insets = new Insets(0, 0, 5, 0);
		gbc_pnlIButtons.fill = GridBagConstraints.BOTH;
		gbc_pnlIButtons.gridx = 0;
		gbc_pnlIButtons.gridy = 2;
		centeredPanel.add(pnlIButtons, gbc_pnlIButtons);
		pnlIButtons.setLayout(new GridLayout(8, 0));

		//vertical space
		Component verticalStrut_3 = Box.createVerticalStrut(20);
		pnlIButtons.add(verticalStrut_3);

		
		//setting Start game button
		JButton btnStart = new JButton("START 2PLAYER GAME");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionOption = ActionOption.PLAY2;
				synchronized (object) {
					object.notifyAll();
				}
			}
		});

		
		btnStart.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlIButtons.add(btnStart);

		
		//vertical space
		Component verticalStrut = Box.createVerticalStrut(20);
		pnlIButtons.add(verticalStrut);

		
		// setting records button
		JButton btnRecords = new JButton("RECORDS");
		btnRecords.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionOption = ActionOption.RECORDS;
				synchronized (object) {
					object.notifyAll();
				}
			}
		});
		
		JButton btnStartplayerGame = new JButton("START 4PLAYER GAME");
		btnStartplayerGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionOption = ActionOption.PLAY4;
				synchronized (object) {
					object.notifyAll();
				}
			}
		});
		btnStartplayerGame.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlIButtons.add(btnStartplayerGame);
		
		Component verticalStrut_2 = Box.createVerticalStrut(20);
		pnlIButtons.add(verticalStrut_2);
		
		
		btnRecords.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlIButtons.add(btnRecords);

		
		//vertical space
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		pnlIButtons.add(verticalStrut_1);

		
		// setting exit button
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionOption = ActionOption.EXIT;
				synchronized (object) {
					object.notifyAll();
				}
			}
		});

		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlIButtons.add(btnExit);

		
		// setting credit labels
		JLabel creditLabel1 = new JLabel("Designed and Programmed by");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 3;
		centeredPanel.add(creditLabel1, gbc_lblNewLabel_1);
		creditLabel1.setFont(new Font("Tahoma", Font.PLAIN, 17));

		
		JLabel creditLabel2 = new JLabel("Amir Mosadeghi");
		creditLabel2.setFont(new Font("Gentium Basic", Font.PLAIN, 19));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 4;
		centeredPanel.add(creditLabel2, gbc_lblNewLabel_2);
	}

	/**
	 * @return users selected option
	 */
	public ActionOption getOption() {
		setVisible(true);
		// wait for user to select an options
		synchronized (object) {
			try {
				object.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// terminates window
		setVisible(false);
		dispose();
		return actionOption;
	}
}