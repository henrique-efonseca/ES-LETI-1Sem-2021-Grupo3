package Frontend;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.kohsuke.github.GHEventPayload.Repository;

import Backend.GitHubAPI;
import Backend.TrelloAPI;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.SystemColor;

public class Github extends JFrame{

	private JFrame frame;
	private GitHubAPI githubApi;
	private Info mainScreen;
	private static String token;
	private static String repositoryName;
	//private final GithubAPI app = new GithubAPI();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Github window = new Github(token, repositoryName);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JFrame getFrame(){
		return this.frame;
	} 
	
	/**
	 * Create the application.
	 */
	public Github(String token, String repositoryName) {
		this.githubApi = new GitHubAPI();
		this.token = token;
		this.repositoryName = repositoryName;
		githubApi.login(token, repositoryName);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 658, 434);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		
		JButton readMeBtn = new JButton("Get readMe");
		readMeBtn.setBounds(33, 110, 133, 44);
		frame.getContentPane().add(readMeBtn);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(33, 342, 78, 29);
		frame.getContentPane().add(btnCancel);
		
		JLabel lblNewLabel = new JLabel("GitHub");
		lblNewLabel.setForeground(SystemColor.scrollbar);
		lblNewLabel.setFont(new Font("Bahnschrift", Font.BOLD, 40));
		lblNewLabel.setBounds(261, 24, 128, 44);
		frame.getContentPane().add(lblNewLabel);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				mainScreen = new Info();
				mainScreen.setVisible(true);
			}
		});
		
		
		readMeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					JOptionPane.showMessageDialog(null, githubApi.getReadMe(), "", JOptionPane.PLAIN_MESSAGE);
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
	}
}
