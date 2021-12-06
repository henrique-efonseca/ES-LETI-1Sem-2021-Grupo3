package Frontend;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class GithubLogin extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private Github git;
	private Info mainScreen;
	private JTextField textField_2;
	private String token ="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";
	private String repo= "henrique-efonseca/ES-LETI-1Sem-2021-Grupo3";
  

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GithubLogin frame = new GithubLogin();
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
	public GithubLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 612, 411);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(250, 235, 215));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField_1 = new JTextField();
		textField_1.setBounds(143, 122, 317, 25);
		textField_1.setColumns(10);
		textField_1.setBackground(SystemColor.menu);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setBounds(143, 200, 317, 25);
		textField_2.setColumns(10);
		textField_2.setBackground(SystemColor.menu);
		contentPane.add(textField_2);
		
		JLabel lblNewLabel_1 = new JLabel("LOGIN");
		lblNewLabel_1.setBounds(245, 31, 138, 49);
		lblNewLabel_1.setFont(new Font("Bahnschrift", Font.BOLD, 35));
		lblNewLabel_1.setForeground(Color.DARK_GRAY);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblAccessToken = new JLabel("Access Token");
		lblAccessToken.setBounds(143, 85, 159, 43);
		lblAccessToken.setForeground(Color.DARK_GRAY);
		lblAccessToken.setFont(new Font("DialogInput", Font.BOLD, 20));
		lblAccessToken.setBackground(SystemColor.menu);
		contentPane.add(lblAccessToken);
		
		JLabel lblBoardId = new JLabel("Repository Name");
		lblBoardId.setBounds(143, 166, 240, 34);
		lblBoardId.setForeground(Color.DARK_GRAY);
		lblBoardId.setFont(new Font("DialogInput", Font.BOLD, 20));
		lblBoardId.setBackground(SystemColor.menu);
		contentPane.add(lblBoardId);
		
		JButton btnNewButton = new JButton("NEXT");
		btnNewButton.setBounds(360, 284, 100, 25);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			    git = new Github();
			    try {
			    	
					git.iniciar(token,repo);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				git.getFrame().setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("HP Simplified", Font.PLAIN, 14));
		btnNewButton.setBackground(new Color(255, 255, 255));
		contentPane.add(btnNewButton);
		
		JButton btnCancel = new JButton("CANCEL");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			    mainScreen = new Info();
			    mainScreen.setVisible(true);
			}
		});
		btnCancel.setFont(new Font("HP Simplified", Font.PLAIN, 14));
		btnCancel.setBackground(Color.WHITE);
		btnCancel.setBounds(143, 284, 100, 25);
		contentPane.add(btnCancel);
		
	}

}
