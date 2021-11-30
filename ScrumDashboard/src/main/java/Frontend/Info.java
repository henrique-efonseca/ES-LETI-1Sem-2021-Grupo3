package Frontend;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Info extends JFrame {

	private JPanel contentPane;
	private TrelloLogin trello;

	/**
	 * Launch the appli
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Info frame = new Info();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Info() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 658, 434);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("TRELLO");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				trello= new TrelloLogin(); 
				trello.setVisible(true);
			}
		});
		btnNewButton.setForeground(Color.DARK_GRAY);
		btnNewButton.setBackground(new Color(255, 255, 240));
		btnNewButton.setFont(new Font("Arial Black", Font.BOLD, 25));
		btnNewButton.setBounds(0, 0, 316, 397);
		contentPane.add(btnNewButton);
		
		JButton btnGithub = new JButton("GITHUB");
		btnGithub.setForeground(Color.WHITE);
		btnGithub.setFont(new Font("Arial Black", Font.BOLD, 25));
		btnGithub.setBackground(Color.DARK_GRAY);
		btnGithub.setBounds(315, 0, 329, 397);
		contentPane.add(btnGithub);
	}
}