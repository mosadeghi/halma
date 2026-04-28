package menus;

import javax.swing.JFrame;

import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.SwingConstants;

import gameFourPlayerMode.gameModel.GameSession;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Settings4PlayerMode extends JFrame {
	private JPanel panel;
	private JTextField txtName1;
	private JTextField txtName2;
	private JTextField txtName3;
	private JTextField txtName4;
	
	private JTextField txtN;
	private JTextField txtM;
	GameSession data;
	Object obj;
	
	public Settings4PlayerMode(Object obj) {
		this.obj = obj;
		setSize(400, 600);
		setResizable(false);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setTitle("HALMA || SETTINGS");

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 2.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 0;
		getContentPane().add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 1, 2, 5, 0.5, 3.5, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JPanel pnlTtitle = new JPanel();
		GridBagConstraints gbc_pnlTtitle = new GridBagConstraints();
		gbc_pnlTtitle.insets = new Insets(0, 0, 5, 0);
		gbc_pnlTtitle.gridx = 0;
		gbc_pnlTtitle.gridy = 1;
		panel.add(pnlTtitle, gbc_pnlTtitle);
		pnlTtitle.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel("SET THE GAME");
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 25));
		pnlTtitle.add(lblNewLabel, BorderLayout.CENTER);

		JPanel pnlInput = new JPanel();
		GridBagConstraints gbc_pnlInput = new GridBagConstraints();
		gbc_pnlInput.insets = new Insets(0, 0, 5, 0);
		gbc_pnlInput.fill = GridBagConstraints.BOTH;
		gbc_pnlInput.gridx = 0;
		gbc_pnlInput.gridy = 2;
		panel.add(pnlInput, gbc_pnlInput);
		pnlInput.setLayout(new GridLayout(12, 0));

		JLabel lblName1 = new JLabel("Orange:");
		lblName1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlInput.add(lblName1);

		txtName1 = new JTextField();
		txtName1.setText("Player 1");
		txtName1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlInput.add(txtName1);
		txtName1.setColumns(10);

		JLabel lblName2 = new JLabel("Blue:");
		lblName2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlInput.add(lblName2);

		txtName2 = new JTextField();
		txtName2.setText("Player 2");
		txtName2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlInput.add(txtName2);
		txtName2.setColumns(10);
		
		JLabel lblPink = new JLabel("Pink:");
		lblPink.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlInput.add(lblPink);
		
		txtName3 = new JTextField();
		txtName3.setText("Player 3");
		txtName3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtName3.setColumns(10);
		pnlInput.add(txtName3);
		
		JLabel lblGreen = new JLabel("Green:");
		lblGreen.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlInput.add(lblGreen);
		
		txtName4 = new JTextField();
		txtName4.setText("Player 4");
		txtName4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtName4.setColumns(10);
		pnlInput.add(txtName4);

		JLabel lblN = new JLabel("N:");
		lblN.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlInput.add(lblN);

		txtN = new JTextField();
		txtN.setHorizontalAlignment(SwingConstants.CENTER);
		txtN.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtN.setText("8");
		pnlInput.add(txtN);
		txtN.setColumns(10);

		JLabel lblM = new JLabel("M:");
		lblM.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pnlInput.add(lblM);

		txtM = new JTextField();
		txtM.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtM.setText("3");
		txtM.setHorizontalAlignment(SwingConstants.CENTER);
		pnlInput.add(txtM);
		txtM.setColumns(10);

		JPanel pnlButtons = new JPanel();
		GridBagConstraints gbc_pnlButtons = new GridBagConstraints();
		gbc_pnlButtons.insets = new Insets(0, 0, 5, 0);
		gbc_pnlButtons.fill = GridBagConstraints.BOTH;
		gbc_pnlButtons.gridx = 0;
		gbc_pnlButtons.gridy = 3;
		panel.add(pnlButtons, gbc_pnlButtons);
		pnlButtons.setLayout(new GridLayout(1, 2));

		JButton btnStart = new JButton("START");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name1 = txtName1.getText();
				String name2 = txtName2.getText();
				String name3 = txtName3.getText();
				String name4 = txtName4.getText();
				String sn = txtN.getText();
				String sm = txtM.getText();
				checkSettings(name1, name2, name3, name4, sn, sm);
			}
		});
		btnStart.setFont(new Font("Tahoma", Font.PLAIN, 18));
		pnlButtons.add(btnStart);

		JButton btnCancel = new JButton("CANCEL");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				data = null;
				synchronized (obj) {
					obj.notifyAll();
				}
			}
		});

		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		pnlButtons.add(btnCancel);
	}

	public GameSession get() {
		setVisible(true);
		synchronized (obj) {
			try {
				obj.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		dispose();
		return data;
	}

	private void checkSettings(String name1, String name2, String name3, String name4, String sn, String sm) {
		if (name1.isEmpty() || name2.isEmpty() || name3.isEmpty() || name4.isEmpty() || sn.isEmpty() || sm.isEmpty()) {
			JOptionPane.showMessageDialog(null, "You have to fill all Panels.");
		}
		else {
			int n = 0, m = 0;
			boolean done = false;
			try {
				n = Integer.parseInt(sn);
				m = Integer.parseInt(sm);
				done = true;
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "You have to enter Integers.");
			}

			if (done) {
				if (n % 2 == 0 && n >= 4 && n <= 20 && m >= 1 && m <= n/2 - 1) {
					data = new GameSession(name1, name2, name3, name4, n, m);
					synchronized (obj) {
						obj.notifyAll();
					}
				}
				else if (n % 2 != 0){
					JOptionPane.showMessageDialog(null, "N should be even.");
				}else if (n < 4){
					JOptionPane.showMessageDialog(null, "N should be greater than or equal to 4.");
				}
				else if (m < 1){
					JOptionPane.showMessageDialog(null, "M should be greater than 0.");
				}else if (m > n/2-1){
					JOptionPane.showMessageDialog(null, "M should be less than half of N.");
				}
			}
		}

	}

}
