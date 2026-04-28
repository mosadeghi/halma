package menus;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class Records extends JFrame {
	private static final long serialVersionUID = -1728148206716607819L;
	private Object object;
	private JTextArea textArea;
	public static String savePath = "data\\records.txt";

	public Records(Object object) {
			this.object = object;
			setSize(400, 600);
			setResizable(false);
			setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
			setTitle("HALMA || RECORDS");
			
			GridBagLayout gridBagLayout = new GridBagLayout();
			gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0 };
			gridBagLayout.rowHeights = new int[] { 0, 0 };
			gridBagLayout.columnWeights = new double[] { 1.0, 2.0, 1.0, Double.MIN_VALUE };
			gridBagLayout.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
			getContentPane().setLayout(gridBagLayout);

			JPanel panel = new JPanel();
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
			gbl_panel.rowWeights = new double[] { 1, 2, 7.5, 0.5, 1, Double.MIN_VALUE };
			panel.setLayout(gbl_panel);

			JPanel pnlTtitle = new JPanel();
			GridBagConstraints gbc_pnlTtitle = new GridBagConstraints();
			gbc_pnlTtitle.insets = new Insets(0, 0, 5, 0);
			gbc_pnlTtitle.gridx = 0;
			gbc_pnlTtitle.gridy = 1;
			panel.add(pnlTtitle, gbc_pnlTtitle);
			pnlTtitle.setLayout(new BorderLayout(0, 0));

			JLabel lblNewLabel = new JLabel("RECORDS");
			lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 25));
			pnlTtitle.add(lblNewLabel, BorderLayout.CENTER);

			JPanel pnlIButtons = new JPanel();
			GridBagConstraints gbc_pnlIButtons = new GridBagConstraints();
			gbc_pnlIButtons.insets = new Insets(0, 0, 5, 0);
			gbc_pnlIButtons.fill = GridBagConstraints.BOTH;
			gbc_pnlIButtons.gridx = 0;
			gbc_pnlIButtons.gridy = 2;
			panel.add(pnlIButtons, gbc_pnlIButtons);
			pnlIButtons.setLayout(new GridLayout(1, 1));
			
			JScrollPane scrollPane = new JScrollPane();
			pnlIButtons.add(scrollPane);
			
			this.textArea = new JTextArea();
			textArea.setFont(new Font("Monospaced", Font.PLAIN, 17));
			scrollPane.setViewportView(textArea);
			textArea.setEditable(false);
			
			ArrayList<String> records = Records.loadRecords();
			if(records.size() == 0)
				textArea.append("No Record !");
			else {
				for(String record:records)
					textArea.append(record+"\n");
			}
			
			JButton btnNewButton = new JButton("Back");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					synchronized (object) {
						object.notifyAll();
					}
				}
			});
			GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
			gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
			gbc_btnNewButton.gridx = 0;
			gbc_btnNewButton.gridy = 3;
			panel.add(btnNewButton, gbc_btnNewButton);

	}
	

	// show recoreds
	public void showRecords() {
		setVisible(true);
		synchronized (object) {
			try {
				object.wait();
			} catch (InterruptedException e) {
			}
		}
		setVisible(false);
		dispose();
	}
	
	//load records
	public static ArrayList<String> loadRecords() {
		ArrayList<String> records = new ArrayList<String>();
		try (FileInputStream file = new FileInputStream(savePath)){
			Scanner scn = new Scanner(file);
			while(scn.hasNextLine())
				records.add(scn.nextLine());
			scn.close();
		} catch (Exception e) {
		}
		return records;
	}
	
	//save records
	public static void saveRecord(String record) {
		ArrayList<String> records = loadRecords();
		records.add(0, record);
		
		try (FileWriter writer = new FileWriter(savePath)){
			for(String rec:records)
				writer.write(rec+"\n");
		} catch (Exception e) {
		}
	}
}
