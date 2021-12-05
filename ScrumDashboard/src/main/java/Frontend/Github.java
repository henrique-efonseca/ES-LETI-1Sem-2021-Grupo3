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

public class Github extends JFrame{

	private JFrame frame;
	private GitHubAPI githubApi;
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
		readMeBtn.setBounds(464, 56, 133, 44);
		frame.getContentPane().add(readMeBtn);
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
