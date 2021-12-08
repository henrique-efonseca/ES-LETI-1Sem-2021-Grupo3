package Frontend;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import com.julienvey.trello.domain.Member;

/**
 *Trello API 
 *@author Ricardo Paulo
 *@version 1.0
 */

public class PieChart {

	private Tabela tabela;
	private String info;
	private JPanel panel;
	
	/**
	 * Constructor of a PieChart
	 * @param t
	 * tabela
	 * @param info
	 * title
	 */
	public PieChart(Tabela t, String info) {
		// TODO Auto-generated constructor stub
		this.tabela=t;
		this.info=info;
	}
   /**
    * returns info of PieChart
    * @return
    */
	public String getInfo(){
		return this.info;
	}
	
	/**
	 * Creates a new Chart with a given list of names, a title, line(a) & column (b)
	 * @param nomes
	 * @param a
	 * @param b
	 * @param title
	 */
	public void createChart(List<Member> nomes,int a,int b, String title){
		JPanel panel;	
	    DefaultPieDataset dataset = new DefaultPieDataset();	    	
	    for(int x=0;x<nomes.size();x++)	{
	    	
	    	
	    	dataset.setValue((String)tabela.getObject(x,a),(Number)tabela.getObject(x,b));
	    }
		JFreeChart chart = ChartFactory.createPieChart(
		tabela.getTitle()+"\n"+title,   // chart title
		dataset,          // data
		true,             // include legend
		true,
		false);
				 	            
		this.panel=new ChartPanel(chart); 		    	

	}
	/**
	 * returns a PieChart
	 * @return
	 */
	public JPanel getPanel(){
		return this.panel;
	}
	
}
	
