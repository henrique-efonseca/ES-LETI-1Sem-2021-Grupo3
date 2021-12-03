package Frontend;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;
import java.util.List;

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
import java.awt.SystemColor;
import javax.swing.event.ChangeListener;

import Backend.TrelloAPI;

import javax.swing.event.ChangeEvent;
import javax.swing.JTable;

public class Trello {

	private JFrame frame;
	private final TrelloAPI app = new TrelloAPI();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Trello window = new Trello();
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

	public JFrame getFrame(){
		return this.frame;
	} 
	
	public void iniciar(String s1, String s2, String s3){
		app.setUp(s1,s2,s3);
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	private void initialize() {
		//app.teste();
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBounds(100, 100, 980, 877);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//--------------------------------------------------------//
		
		
		
		//--------------------------------------------------------//
		
		
		JLabel lblNewLabel = new JLabel("Dark Mode");
		lblNewLabel.setForeground(new Color(255, 215, 0));
		lblNewLabel.setFont(new Font("HP Simplified Jpan Light", Font.BOLD, 20));
		lblNewLabel.setBounds(837, 10, 119, 29);
		frame.getContentPane().add(lblNewLabel);
		
		JToggleButton tglbtnNewToggleButton = new JToggleButton("ON");
		tglbtnNewToggleButton.setForeground(Color.DARK_GRAY);
		tglbtnNewToggleButton.setBackground(SystemColor.menu);
		tglbtnNewToggleButton.setFont(new Font("Trebuchet MS", Font.BOLD, 17));
		tglbtnNewToggleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().setBackground(new Color(0,0,0));
			}
		});
		tglbtnNewToggleButton.setBounds(847, 40, 77, 28);
		frame.getContentPane().add(tglbtnNewToggleButton);
		
		//--------------------------------------------------------//
		
		JButton btnCreateCard = new JButton("Create Card");
		btnCreateCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String list_name = JOptionPane.showInputDialog("In what list would you like to add a card?");
				String card_name = JOptionPane.showInputDialog("Card name:");
				app.createBoard(list_name, card_name);
			}
		});
		btnCreateCard.setBounds(798, 107, 126, 28);
		frame.getContentPane().add(btnCreateCard);
		
	
		
		//--------------------------------------------------------//
		

		JLabel lblNewLabel_1 = new JLabel("Identificação do Projeto");
		lblNewLabel_1.setForeground(SystemColor.info);
		lblNewLabel_1.setFont(new Font("Century Gothic", Font.BOLD, 25));
		lblNewLabel_1.setBounds(323, 26, 332, 49);
		frame.getContentPane().add(lblNewLabel_1);
		
		
		JButton btnNewButton_1 = new JButton("Obter");
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 15));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, app.projectID(), "Identificação do Projeto:", JOptionPane.PLAIN_MESSAGE);
			}
		});
		btnNewButton_1.setBounds(396, 85, 143, 35);
		frame.getContentPane().add(btnNewButton_1);
		
		
		//--------------------------------------------------------//
		
		
		JLabel lblNewLabel_1_1 = new JLabel("Identificação dos elementos da equipa do projeto");
		lblNewLabel_1_1.setForeground(new Color(255, 250, 240));
		lblNewLabel_1_1.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(45, 157, 697, 49);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JButton membros = new JButton("Membros");
		membros.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		membros.setBounds(45, 204, 149, 40);
		membros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, app.members(), "Membros da board:", JOptionPane.PLAIN_MESSAGE);		
			}
		});
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(membros);
		
		//--------------------------------------------------------//
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Listas na board do Trello");
		lblNewLabel_1_1_1.setForeground(new Color(255, 250, 240));
		lblNewLabel_1_1_1.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lblNewLabel_1_1_1.setBounds(700, 186, 244, 49);
		frame.getContentPane().add(lblNewLabel_1_1_1);
		
		JButton btnNewButton_2 = new JButton("Listas");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, app.lists(), "Listas:", JOptionPane.PLAIN_MESSAGE);		
			}
		});
		btnNewButton_2.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		btnNewButton_2.setBounds(785, 242, 149, 40);
		frame.getContentPane().add(btnNewButton_2);
		
		//--------------------------------------------------------//

		
		JLabel lblNewLabel_1_1_2 = new JLabel("Data início do projeto");
		lblNewLabel_1_1_2.setForeground(new Color(255, 250, 240));
		lblNewLabel_1_1_2.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lblNewLabel_1_1_2.setBounds(45, 269, 697, 49);
		frame.getContentPane().add(lblNewLabel_1_1_2);
		
		JButton btnData = new JButton("Data");
		btnData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, app.projectDate(), "Data:", JOptionPane.PLAIN_MESSAGE);		
			}
		});
		btnData.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		btnData.setBounds(45, 312, 149, 40);
		frame.getContentPane().add(btnData);
		
		JLabel lblNewLabel_1_1_2_1 = new JLabel("Data início dos Sprints");
		lblNewLabel_1_1_2_1.setForeground(new Color(255, 250, 240));
		lblNewLabel_1_1_2_1.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lblNewLabel_1_1_2_1.setBounds(716, 306, 223, 49);
		frame.getContentPane().add(lblNewLabel_1_1_2_1);
		
		JButton btnData_1 = new JButton("Data");
		btnData_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, app.sprintDates(), "Data:", JOptionPane.PLAIN_MESSAGE);	
			}
		});
		btnData_1.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		btnData_1.setBounds(785, 354, 149, 40);
		frame.getContentPane().add(btnData_1);
		
		//--------------------------------------------------------//
		
		JLabel lblNewLabel_1_1_2_1_1 = new JLabel("Itens do Product Backlog");
		lblNewLabel_1_1_2_1_1.setForeground(new Color(255, 250, 240));
		lblNewLabel_1_1_2_1_1.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lblNewLabel_1_1_2_1_1.setBounds(45, 398, 299, 49);
		frame.getContentPane().add(lblNewLabel_1_1_2_1_1);
		
		JButton btnData_1_1 = new JButton("Itens");
		btnData_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, app.productBacklog(), "Products:", JOptionPane.PLAIN_MESSAGE);	
			}
		});
		btnData_1_1.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		btnData_1_1.setBounds(45, 441, 149, 40);
		frame.getContentPane().add(btnData_1_1);
		
		//--------------------------------------------------------//

		JLabel lblNewLabel_1_1_2_1_1_1 = new JLabel("Texto de cada Sprint");
		lblNewLabel_1_1_2_1_1_1.setForeground(new Color(255, 250, 240));
		lblNewLabel_1_1_2_1_1_1.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lblNewLabel_1_1_2_1_1_1.setBounds(45, 521, 299, 49);
		frame.getContentPane().add(lblNewLabel_1_1_2_1_1_1);
		
		JButton btnData_1_1_1 = new JButton("Texto");
		btnData_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextArea textArea = new JTextArea(app.sprintsText());
				JScrollPane scrollPane = new JScrollPane(textArea);  
				textArea.setLineWrap(true);  
				textArea.setWrapStyleWord(true); 
				scrollPane.setPreferredSize( new Dimension( 500, 500 ) );
				JOptionPane.showMessageDialog(null, scrollPane, "Sprints:", JOptionPane.PLAIN_MESSAGE);	
			}
		});
		btnData_1_1_1.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		btnData_1_1_1.setBounds(45, 565, 149, 40);
		frame.getContentPane().add(btnData_1_1_1);
		
		//--------------------------------------------------------//

		
		JLabel lblNewLabel_1_1_2_1_1_2 = new JLabel("Data dos testes realizados");
		lblNewLabel_1_1_2_1_1_2.setForeground(new Color(255, 250, 240));
		lblNewLabel_1_1_2_1_1_2.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lblNewLabel_1_1_2_1_1_2.setBounds(681, 415, 299, 49);
		frame.getContentPane().add(lblNewLabel_1_1_2_1_1_2);
		
		JButton btnData_1_2 = new JButton("Data");
		btnData_1_2.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		btnData_1_2.setBounds(785, 463, 149, 40);
		frame.getContentPane().add(btnData_1_2);
		
		//--------------------------------------------------------//

		
		JLabel lblNewLabel_1_1_2_1_1_1_1 = new JLabel("Horas de trabalho");
		lblNewLabel_1_1_2_1_1_1_1.setForeground(new Color(255, 250, 240));
		lblNewLabel_1_1_2_1_1_1_1.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lblNewLabel_1_1_2_1_1_1_1.setBounds(758, 531, 176, 49);
		frame.getContentPane().add(lblNewLabel_1_1_2_1_1_1_1);
		
		JButton btnData_1_1_1_1 = new JButton("Pie Chart");
		btnData_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Tabela> tabelas = app.tempoSprints();
				for(Tabela t : tabelas) JOptionPane.showMessageDialog(null, t.getTabela(), t.getTitle(), JOptionPane.PLAIN_MESSAGE);	
			}
		});
		btnData_1_1_1_1.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		btnData_1_1_1_1.setBounds(785, 579, 149, 40);
		frame.getContentPane().add(btnData_1_1_1_1);
		
		//--------------------------------------------------------//

		
		JLabel lblNewLabel_1_1_2_1_1_1_1_1 = new JLabel("Custo dos recursos humanos");
		lblNewLabel_1_1_2_1_1_1_1_1.setForeground(new Color(255, 250, 240));
		lblNewLabel_1_1_2_1_1_1_1_1.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lblNewLabel_1_1_2_1_1_1_1_1.setBounds(45, 636, 346, 49);
		frame.getContentPane().add(lblNewLabel_1_1_2_1_1_1_1_1);
		
		JButton btnData_1_1_1_1_1 = new JButton("Pie Chart");
		btnData_1_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Tabela> tabelas = app.recursosHumanos();
				for(Tabela t : tabelas) JOptionPane.showMessageDialog(null, t.getTabela(), t.getTitle(), JOptionPane.PLAIN_MESSAGE);	
			}
		});
		btnData_1_1_1_1_1.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		btnData_1_1_1_1_1.setBounds(45, 682, 149, 40);
		frame.getContentPane().add(btnData_1_1_1_1_1);
		
		//--------------------------------------------------------//
		
		
	}
}
