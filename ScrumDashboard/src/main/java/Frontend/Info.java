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
	private GithubLogin git;
	/**
	 * Launch the application
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
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.DARK_GRAY);
		btnNewButton.setFont(new Font("Arial Black", Font.BOLD, 25));
		btnNewButton.setBounds(0, 0, 329, 397);
		contentPane.add(btnNewButton);
		
		JButton btnGithub = new JButton("GITHUB");
		btnGithub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				git= new GithubLogin(); 
				git.setVisible(true);
			}
		});
		btnGithub.setForeground(Color.DARK_GRAY);
		btnGithub.setFont(new Font("Arial Black", Font.BOLD, 25));
		btnGithub.setBackground(new Color(250, 240, 230));
		btnGithub.setBounds(325, 0, 319, 397);
		contentPane.add(btnGithub);
	}
}