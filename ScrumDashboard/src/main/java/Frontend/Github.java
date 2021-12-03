package Frontend;

import java.awt.EventQueue;

import javax.swing.JFrame;

import Backend.TrelloAPI;

public class Github {

	private JFrame frame;
	//private final GithubAPI app = new GithubAPI();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Github window = new Github();
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
	public Github() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
