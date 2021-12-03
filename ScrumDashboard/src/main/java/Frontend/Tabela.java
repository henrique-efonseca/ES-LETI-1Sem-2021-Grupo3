package Frontend;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Tabela {

	private JScrollPane scrollPane;
	private String title;
	private Object[][] data;
	private String[] col;
	private int coluna;
	private int linha;
	
	public Tabela(String title, int row, int col) {
		// TODO Auto-generated constructor stu
		this.title = title;
		this.data = new Object[row][col];
		this.col = new String[col];
		this.coluna=col;
		this.linha = row;
	}
	
	public void addData(Object o,int row, int col){
		this.data[row][col] = o;
	}
	public void addColumnName(String s, int c){
		col[c]=s;
	}
	
	public void createScrollPane(){
		JTable table = new JTable(data, col);
		this.scrollPane = new JScrollPane(table);
	}
	
	public JScrollPane getTabela(){
		return scrollPane;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public Object getObject(int r, int c){
		return data[r][c];
	}
	
	public int getCol(){
		return coluna;
	}
}
