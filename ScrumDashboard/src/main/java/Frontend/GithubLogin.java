package Frontend;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Backend.GitHubAPI;

public class GithubLogin extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private GitHubAPI githubApi;
	private Github git;
	private Info info;
	private JTextField textField_2;
	private String githubToken= "token";

  

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
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JTextField tokenLoginField = new JTextField();
		tokenLoginField.setBounds(143, 145, 317, 25);
		tokenLoginField.setColumns(10);
		tokenLoginField.setBackground(SystemColor.menu);
		contentPane.add(tokenLoginField);
		
		final JTextField repositoryNameField = new JTextField();
		repositoryNameField.setBounds(143, 215, 317, 25);
		repositoryNameField.setColumns(10);
		repositoryNameField.setBackground(SystemColor.menu);
		contentPane.add(repositoryNameField);
		
		JLabel lblNewLabel_1 = new JLabel("LOGIN");
		lblNewLabel_1.setBounds(245, 31, 138, 49);
		lblNewLabel_1.setFont(new Font("Bahnschrift", Font.BOLD, 35));
		lblNewLabel_1.setForeground(SystemColor.scrollbar);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblAccessToken = new JLabel("Access Token");
		lblAccessToken.setBounds(143, 104, 159, 43);
		lblAccessToken.setForeground(SystemColor.scrollbar);
		lblAccessToken.setFont(new Font("DialogInput", Font.BOLD, 20));
		lblAccessToken.setBackground(SystemColor.menu);
		contentPane.add(lblAccessToken);
		
		JLabel lblBoardId = new JLabel("Repository Name");
		lblBoardId.setBounds(143, 181, 240, 34);
		lblBoardId.setForeground(SystemColor.scrollbar);
		lblBoardId.setFont(new Font("DialogInput", Font.BOLD, 20));
		lblBoardId.setBackground(SystemColor.menu);
		contentPane.add(lblBoardId);
		
		JButton btnNewButton = new JButton("NEXT");
		btnNewButton.setBounds(360, 274, 100, 25);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			    git = new Github(tokenLoginField.getText(), repositoryNameField.getText());
			    //trello.iniciar(textField.getText(), textField_1.getText(), textField_2.getText());
			    //git.iniciar(trelloKey,trelloAccessToken,boardID);
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
				info= new Info();
				info.setVisible(true);
			}
		});;
		btnCancel.setFont(new Font("HP Simplified", Font.PLAIN, 14));
		btnCancel.setBackground(Color.WHITE);
		btnCancel.setBounds(143, 277, 100, 25);
		contentPane.add(btnCancel);
		
	}

}
