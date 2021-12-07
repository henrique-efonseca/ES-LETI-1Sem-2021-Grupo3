package Frontend;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.kohsuke.github.GHTag;

import Backend.GitHubAPI;
import Backend.TrelloAPI;

public class Github {

	private JFrame frame;
	private final GitHubAPI app = new GitHubAPI();	
	private String repo;
	
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

	public void iniciar(String s1, String s2) throws IOException{
		repo=s2;
		app.setUp(s1,s2);
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(253, 245, 230));
		frame.setBounds(100, 100, 423, 554);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//--------------------------------------------------------//
		
		
		JLabel lblNewLabel_1_2 = new JLabel("REPOSITORIO");
		lblNewLabel_1_2.setForeground(Color.DARK_GRAY);
		lblNewLabel_1_2.setFont(new Font("Century Gothic", Font.BOLD, 30));
		lblNewLabel_1_2.setBounds(109, 26, 223, 49);
		frame.getContentPane().add(lblNewLabel_1_2);
		
		JButton btnNewButton_1_1 = new JButton("Nome");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					JOptionPane.showMessageDialog(null, app.getRepoName(), "Nome:", JOptionPane.PLAIN_MESSAGE);
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1_1.setForeground(new Color(245, 255, 250));
		btnNewButton_1_1.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 15));
		btnNewButton_1_1.setBackground(Color.DARK_GRAY);
		btnNewButton_1_1.setBounds(132, 74, 143, 43);
		frame.getContentPane().add(btnNewButton_1_1);
		
		//--------------------------------------------------------//
		
		JLabel lblNewLabel_1 = new JLabel("Conteudo do README");
		lblNewLabel_1.setBounds(94, 140, 223, 49);
		lblNewLabel_1.setForeground(Color.DARK_GRAY);
		lblNewLabel_1.setFont(new Font("Century Gothic", Font.BOLD, 20));
		frame.getContentPane().add(lblNewLabel_1);
		
		
		JButton btnNewButton_1 = new JButton("Read me");
		btnNewButton_1.setForeground(new Color(245, 255, 250));
		btnNewButton_1.setBounds(132, 186, 143, 43);
		btnNewButton_1.setBackground(Color.DARK_GRAY);
		btnNewButton_1.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 15));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					JOptionPane.showMessageDialog(null, app.getREADME(), "READ ME:", JOptionPane.PLAIN_MESSAGE);
				} catch (HeadlessException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		frame.getContentPane().add(btnNewButton_1);
		
		
		//--------------------------------------------------------//
		
		
		JLabel lblNewLabel_1_1 = new JLabel("Lista de Commits");
		lblNewLabel_1_1.setBounds(121, 252, 177, 49);
		lblNewLabel_1_1.setForeground(Color.DARK_GRAY);
		lblNewLabel_1_1.setFont(new Font("Century Gothic", Font.BOLD, 20));
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JButton commit = new JButton("Commit");
		commit.setForeground(new Color(245, 255, 250));
		commit.setBackground(Color.DARK_GRAY);
		commit.setBounds(132, 293, 143, 43);
		commit.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		commit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					JTextArea textArea = new JTextArea(app.commits());
					JScrollPane scrollPane = new JScrollPane(textArea);  
					textArea.setLineWrap(true);  
					textArea.setWrapStyleWord(true); 
					scrollPane.setPreferredSize( new Dimension( 500, 500 ) );
					JOptionPane.showMessageDialog(null, scrollPane, "Commits:", JOptionPane.PLAIN_MESSAGE);
				} catch (HeadlessException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}		
			}
		});
		
		frame.getContentPane().add(commit);
		
		//--------------------------------------------------------//

		
		JButton btnTag = new JButton("Tag");
        btnTag.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	HashMap<GHTag, String> tags_dates;
				try {
					tags_dates = app.getTags();
					for (GHTag tag : tags_dates.keySet()) {
						JOptionPane.showMessageDialog(null,"Tag: " + tag.getName() + "\n" + "Date: " + tags_dates.get(tag) , "Tag:", JOptionPane.PLAIN_MESSAGE);
	    				
	    			}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    			
    			
    			
            }
        });
        btnTag.setForeground(new Color(240, 248, 255));
        btnTag.setBackground(Color.DARK_GRAY);
        btnTag.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
        btnTag.setBounds(132, 405, 143, 43);
        frame.getContentPane().add(btnTag);

        JLabel lblNewLabel_1_1_1 = new JLabel("Lista de todas as tags");
        lblNewLabel_1_1_1.setForeground(Color.DARK_GRAY);
        lblNewLabel_1_1_1.setFont(new Font("Century Gothic", Font.BOLD, 20));
        lblNewLabel_1_1_1.setBounds(100, 357, 217, 49);
        frame.getContentPane().add(lblNewLabel_1_1_1);
        }
		
		

	
	
	
	
	
	
	
	/*
	 * 
	 * 
	 * HashMap<GHTag, String> tags_dates = api.getTags();
			
			
			for (GHTag tag : tags_dates.keySet()) {
				System.out.println("Tag: " + tag.getName() );
				System.out.println("Date: " + tags_dates.get(tag) );
			}
	 * 
	 * */
	 
	
}
