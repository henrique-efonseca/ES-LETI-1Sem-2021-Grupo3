package GUI;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import ES2021987.es2021987.TrelloAPI;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.DropMode;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JToggleButton;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.Font;

public class ScrumDashboard {

	private JFrame frame;
	private final TrelloAPI app = new TrelloAPI();
	private JLabel label;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScrumDashboard window = new ScrumDashboard();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ScrumDashboard() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//app.teste();
		frame = new JFrame();
		frame.setBounds(100, 100, 980, 726);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton membros = new JButton("Membros");
		membros.setBounds(25, 71, 126, 28);
		membros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, app.teste(), "Membros da board:", JOptionPane.PLAIN_MESSAGE);		
			}
		});
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(membros);
		
		JToggleButton tglbtnNewToggleButton = new JToggleButton("Dark");
		tglbtnNewToggleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().setBackground(new Color(0,0,0));
			}
		});
		tglbtnNewToggleButton.setBounds(25, 33, 90, 28);
		frame.getContentPane().add(tglbtnNewToggleButton);
		
		JButton btnNewButton = new JButton("Lists");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, app.teste2(), "Lists:", JOptionPane.PLAIN_MESSAGE);
			}
		});
		btnNewButton.setBounds(25, 109, 126, 28);
		frame.getContentPane().add(btnNewButton);
		
		
		
	}
}
