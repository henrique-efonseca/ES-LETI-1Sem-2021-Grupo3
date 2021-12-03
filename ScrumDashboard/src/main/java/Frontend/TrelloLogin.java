package Frontend;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.SystemColor;import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TrelloLogin extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private Trello trello;
	private Info info;
	private JTextField textField_2;
	private String trelloKey= "a04256995af78e5ea7bff424d82cf477";
			private String trelloAccessToken = "47fd71497e5ffcb377ea49fd0302a42f66ba0a411829da35dac1ade25025e501";
					private String boardID = "60eae4ca19ea426cbba3021a";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TrelloLogin frame = new TrelloLogin();
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
	public TrelloLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 612, 411);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(143, 104, 317, 25);
		textField.setBackground(SystemColor.menu);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(143, 168, 317, 25);
		textField_1.setColumns(10);
		textField_1.setBackground(SystemColor.menu);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setBounds(143, 235, 317, 25);
		textField_2.setColumns(10);
		textField_2.setBackground(SystemColor.menu);
		contentPane.add(textField_2);
		
		JLabel lblNewLabel = new JLabel("Key");
		lblNewLabel.setBounds(143, 65, 159, 43);
		lblNewLabel.setBackground(new Color(240, 240, 240));
		lblNewLabel.setForeground(SystemColor.scrollbar);
		lblNewLabel.setFont(new Font("DialogInput", Font.BOLD, 20));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("LOGIN");
		lblNewLabel_1.setBounds(248, 24, 135, 49);
		lblNewLabel_1.setFont(new Font("Bahnschrift", Font.BOLD, 35));
		lblNewLabel_1.setForeground(new Color(153, 204, 255));
		contentPane.add(lblNewLabel_1);
		
		JLabel lblAccessToken = new JLabel("Access Token");
		lblAccessToken.setBounds(143, 129, 159, 43);
		lblAccessToken.setForeground(SystemColor.scrollbar);
		lblAccessToken.setFont(new Font("DialogInput", Font.BOLD, 20));
		lblAccessToken.setBackground(SystemColor.menu);
		contentPane.add(lblAccessToken);
		
		JLabel lblBoardId = new JLabel("Board ID");
		lblBoardId.setBounds(143, 203, 159, 34);
		lblBoardId.setForeground(SystemColor.scrollbar);
		lblBoardId.setFont(new Font("DialogInput", Font.BOLD, 20));
		lblBoardId.setBackground(SystemColor.menu);
		contentPane.add(lblBoardId);
		
		JButton btnNewButton = new JButton("NEXT");
		btnNewButton.setBounds(360, 306, 100, 25);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			    trello = new Trello();
				//trello.iniciar(textField.getText(), textField_1.getText(), textField_2.getText());
			    trello.iniciar(trelloKey,trelloAccessToken,boardID);
				trello.getFrame().setVisible(true);
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
		btnCancel.setBounds(143, 306, 100, 25);
		contentPane.add(btnCancel);
		
	}
}
