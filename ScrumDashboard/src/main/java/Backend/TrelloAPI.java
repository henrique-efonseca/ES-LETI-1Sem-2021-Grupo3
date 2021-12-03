package Backend;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import com.julienvey.trello.Trello;
import com.julienvey.trello.domain.Action;
import com.julienvey.trello.domain.Board;
import com.julienvey.trello.domain.Card;
import com.julienvey.trello.domain.Label;
import com.julienvey.trello.domain.Member;
import com.julienvey.trello.domain.TList;
import com.julienvey.trello.impl.TrelloImpl;
import com.julienvey.trello.impl.http.ApacheHttpClient;

import Frontend.Tabela;

public class TrelloAPI 
{
	private String trelloKey;
    private String trelloAccessToken;
    private String boardID;
    private Trello trelloApi;
    private Board board;
    private List<Member> nomes;
    private List<TList> lists;
    private List<Card> cards;
    
      
    
    public List<JPanel> pieChart(List<Tabela> tabelas, int n, String info){
    	List<JPanel> charts = new ArrayList<>();
    	int x=0;
    	int count = 1;
    	for(Tabela t : tabelas){   	
	    	 while(count<=n){
	    		 if(count<t.getCol()){
		    		 DefaultPieDataset dataset = new DefaultPieDataset();
			    	 for(Member m: nomes){
			    		 dataset.setValue((String)t.getObject(x,0),(Number)t.getObject(x,count));
				    	 x++;
			    	 }
			    	 x=0;
			    	 String s = "";
			    	 if(count==1) s = "\nRealizado" ; else s="\nPrevisto";
			    	 JFreeChart chart = ChartFactory.createPieChart(
				 	            t.getTitle()+s,   // chart title
				 	            dataset,          // data
				 	            true,             // include legend
				 	            true,
				 	            false);
				 	            
				 	 charts.add(new ChartPanel(chart)); 
			    	 count++;  
	    		 }
	    	 }
	    	 count=1;
    	}
    	for(JPanel c : charts)
    	 JOptionPane.showMessageDialog(null, c, info, JOptionPane.PLAIN_MESSAGE);
    	return charts;
    }
    
    
    
  
    public List<String> getSprints(){
    	List<String> sprints = new ArrayList<>();
    	for(TList l: lists) 
    		if(l.getName().equals("Sprint Planning"))
    			for(Card c : cards) if(c.getIdList().equals(l.getId())) sprints.add(c.getName());
    		
    	return sprints;
    	
    } 

    public List<Tabela> recursosHumanos(){
    	List<String> sprints = getSprints(); 
    	HashMap<String, Integer> realizados = new HashMap<String, Integer>();
    	HashMap<String, Integer> previstos = new HashMap<String, Integer>();
    	int row = 0;

    	for(Member m: nomes){
    		realizados.put(m.getUsername(),0);
    		previstos.put(m.getUsername(),0);
    		row++;
    	}
    	
    	List<Tabela> tabelas = new ArrayList<>();
    	
    	for(String sprint: sprints){
    		resetMap(realizados);
    		resetMap(previstos);
	    	for(Card c : cards){
	    		List<Label> labels = c.getLabels();
			    	for(Label l : labels){
			    			if(l.getName().equals(sprint)){
					    		contarHoras(c,realizados,previstos);
			    			}
			    	}
			}   
	    	Tabela t = new Tabela(sprint, row, 2);
	    	int x=0;	    	
	    	for(Member m: nomes){
	    		t.addData(m.getUsername(),x,0);
	    		t.addData((realizados.get(m.getUsername())/60)*20,x,1);
	    		x++;
	    	}
	    	t.addColumnName("Username",0);
	    	t.addColumnName("Custo recursos humanos (€)",1);
	    	t.createScrollPane();
	    	tabelas.add(t);
	    	
	    }
    	resetMap(realizados);
		resetMap(previstos);
		
		Tabela t = new Tabela("Total", row, 2);
    	for (Card c : cards) contarHoras(c,realizados,previstos);  
    	int x=0;
    	for(Member m: nomes){
    		t.addData(m.getUsername(),x,0);
    		t.addData((realizados.get(m.getUsername())/60)*20,x,1);
    		x++;
	    } 
    	t.addColumnName("Username",0);
    	t.addColumnName("Custo recursos humanos (€)",1);
    	t.createScrollPane();
    	tabelas.add(t);
    	pieChart(tabelas,1,"CUSTO RECURSOS HUMANOS (€)");
    	return tabelas;
    }
   
    public List<Tabela> tempoSprints(){
    	List<String> sprints = getSprints(); 
    	HashMap<String, Integer> realizados = new HashMap<String, Integer>();
    	HashMap<String, Integer> previstos = new HashMap<String, Integer>();
    	int row = 0;

    	for(Member m: nomes){
    		realizados.put(m.getUsername(),0);
    		previstos.put(m.getUsername(),0);
    		row++;
    	}
    	
    	List<Tabela> tabelas = new ArrayList<>();
    	
    	for(String sprint: sprints){
    		resetMap(realizados);
    		resetMap(previstos);
	    	for(Card c : cards){
	    		List<Label> labels = c.getLabels();
			    	for(Label l : labels){
			    			if(l.getName().equals(sprint)){
					    		contarHoras(c,realizados,previstos);
			    			}
			    	}
			}   
	    	Tabela t = new Tabela(sprint, row, 3);
	    	int x=0;
	    	
	    	for(Member m: nomes){
	    		t.addData(m.getUsername(),x,0);
	    		t.addData(realizados.get(m.getUsername()),x,1);
	    		t.addData(previstos.get(m.getUsername()),x,2);
	    		x++;
	    	}
	    	t.addColumnName("Username",0);
	    	t.addColumnName("Tempo realizado (min)",1);
	    	t.addColumnName("Tempo previsto (min)",2);
	    	t.createScrollPane();
	    	tabelas.add(t);
	    }
    	resetMap(realizados);
		resetMap(previstos);
		pieChart(tabelas,2,"TOTAL DE TEMPO (MIN)");
		
		Tabela t = new Tabela("Total", row, 2);
    	for (Card c : cards) contarHoras(c,realizados,previstos);  
    	int x=0;
    	for(Member m: nomes){
    		t.addData(m.getUsername(),x,0);
    		t.addData(realizados.get(m.getUsername()),x,1);
    		x++;
	    } 
    	t.addColumnName("Username",0);
    	t.addColumnName("Tempo total (min)",1);
    	t.createScrollPane();
  
    	tabelas.add(t);        	
    	return tabelas;
    }
    
    public void resetMap(HashMap<String, Integer> map){
    	for(Member m: nomes){
    		map.put(m.getUsername(),0);
    	}
    }
    
   
    public void contarHoras(Card c, HashMap<String, Integer> realizados, HashMap<String, Integer> previstos ){
      	
			List<Action> a = c.getActions();
			for (Action a2 : a){
				String temp = a2.getData().getText();
				for(Member m : nomes){
					if(temp!=null 
						&& temp.contains("plus!") 
					    && ( temp.contains(m.getUsername())
				    	|| temp.contains("global"))){
							String[] arrOfStr = temp.split(" ", 3);
							String[] previstas_realizadas = arrOfStr[2].split("/",2);
							realizados.put(m.getUsername(),realizados.get(m.getUsername())+Integer.parseInt(previstas_realizadas[0]));
							previstos.put(m.getUsername(),previstos.get(m.getUsername())+Integer.parseInt(previstas_realizadas[1].replaceAll("\\s+","")));
					}
				}
			}		    		
		return;
    }
    
    
    public String sprintsText(){
    	String s="";
    	
    	for (TList l : lists) 
    		if(l.getName().contains("Sprint") && !l.getName().contains("Sprint Backlog")){
    			s += l.getName().toUpperCase() + ": \n\r";
    			for (Card c : cards) {
    				if(c.getIdList().equals(l.getId())) s +=  c.getName() + ": \n\r " + c.getDesc() + "\n\r";
    			}
    		}
    	return s;
    }
    
    public String productBacklog(){
    	String id="";
    	String s="";
    	HashMap<String, String> itens = new HashMap<String, String>();
    	List<String> sprints = getSprints();
    	for(String sprint: sprints){
    		itens.put(sprint,sprint.toUpperCase()+":\n");
    	}
    	
    	for (TList l : lists) if(l.getName().contains("Product Backlog")) id = l.getId();
    	
    	for (Card c : cards) {
    		if(c.getIdList().equals(id)){
    		
	    		List<Label> labels = c.getLabels();
	    		for(Label l2 : labels)
	    			 if(l2.getName().contains("Sprint")) itens.put(l2.getName(),itens.get(l2.getName())+"\n"+c.getName());
    		}
    	}
    	
    	for(String sprint: sprints){
    		s+=  itens.get(sprint) + "\n\r";
    	}
    	return s;
    }
    
    public String sprintDates(){
    	String s="";
    	
    	for (TList l : lists) 
    		if(l.getName().contains("Sprint"))
    			for (Card c : cards) 
    				if(c.getIdList().equals(l.getId())) s += c.getName()+ ":\n INICIO: " + c.getDateLastActivity().toString() +"\n FIM: " + c.getDue() + "\n\r";
    			
    	return s;
    }
    
    
    public String projectDate(){
    	for(Card c : cards) if(c.getName().equals("Data de inicio do projeto")) return c.getDue().toString();
    	return null;
    }	
    
    
    public String projectID(){
    	 return board.getName()+ " - id: " +board.getId();
    }
    
    public void createBoard(String s, String card_name){
	    for (TList l : lists) {
	    	if(l.getName().equals(s)){
	    		Card c = new Card();
	    		c.setName(card_name);
	    		trelloApi.createCard(l.getId(),c);
	    		return;
	    	}
	    }
    }
    
    public String lists(){ 	
    	 String string = "";
         for (TList list : lists) {
          	 string += list.getName() + "\r\n";
         }
         return string;
    }
    
	public String members(){
		String string = "";
	    for (Member names : nomes) {
	    	string += names.getUsername() + "\r\n";
	    	
	    }
	    return string;
	}
	
	public void setUp(String s1, String s2, String s3){
    	trelloKey = s1;
    	trelloAccessToken = s2;
    	boardID = s3;
    	trelloApi = new TrelloImpl(trelloKey, trelloAccessToken, new ApacheHttpClient());
    	board = trelloApi.getBoard(boardID);
    	nomes = board.fetchMembers();
    	lists = board.fetchLists();
    	cards = board.fetchCards();
    }
	
    /*public static void main( String[] args )
    {
    	String trelloKey= "a04256995af78e5ea7bff424d82cf477";
        String trelloAccessToken="47fd71497e5ffcb377ea49fd0302a42f66ba0a411829da35dac1ade25025e501";
        String boardID = "60eae4ca19ea426cbba3021a";
        Trello trelloApi = new TrelloImpl(trelloKey, trelloAccessToken, new ApacheHttpClient());
      
        Board board;
       // teste();
           // List<TList> lists = quadro.fetchLists();
           // for (TList lista : lists) {
           //     System.out.println(lista.getName()+"- "+ lista.getId()+"-"+lista.getIdBoard());
           // }
        //}
    }*/
}
