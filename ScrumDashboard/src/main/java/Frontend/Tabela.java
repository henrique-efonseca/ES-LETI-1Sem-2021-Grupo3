package Frontend;

import java.io.IOException;

import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *Trello API 
 *@author Ricardo Paulo
 *@version 1.0
 */

public class Tabela {

	private JScrollPane scrollPane;
	private String title;
	private Object[][] data;
	private String[] col;
	private int coluna;
	private int linha;
	
	/**
	 * Constructor of a table
	 * @param title
	 * @param row
	 * @param col
	 * 
	 */
	
	public Tabela(String title, int row, int col) {
		// TODO Auto-generated constructor 
		this.title = title;
		this.data = new Object[row][col];
		this.col = new String[col];
		this.coluna=col;
		this.linha = row;
	}
	/**
	 * add object to the coordinates row & col
	 * @param row
	 * @param col
	 */
	public void addData(Object o,int row, int col){
		this.data[row][col] = o;
	}
	
	/**
	 * Add a name to a column on the coordinates of a certain object
	 * @param s
	 * @param c
	 */
	public void addColumnName(String s, int c){
		col[c]=s;
	}
	/**
	 * Create a new table
	 */
	public void createScrollPane(){
		JTable table = new JTable(data, col);
		this.scrollPane = new JScrollPane(table);
	}
	/**
	 * returns a table
	 * @return
	 * 
	 */
	
	public JScrollPane getTabela(){
		return scrollPane;
	}
	/**
	 * returns the title of a table
	 * @return
	 */
	public String getTitle(){
		return this.title;
	}
	/**
	 * 
	 * @param r
	 * @param c
	 * @return 
	 * returns the object with the coordinates r & c
	 */
	public Object getObject(int r, int c){
		return data[r][c];
	}
	/**
	 * returns a column
	 * @return
	 * 
	 */
	public int getCol(){
		return coluna;
	}
}
