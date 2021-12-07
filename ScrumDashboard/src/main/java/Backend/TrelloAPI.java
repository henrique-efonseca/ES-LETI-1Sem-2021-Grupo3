package Backend;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

import Frontend.PieChart;
import Frontend.Sprint;
import Frontend.Tabela;

/**
 *Trello API 
 *@author Ricardo Paulo
 *@version 1.0
 */

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
    private List<Tabela> tempo = new ArrayList<>();
    private List<Sprint> sprints = new ArrayList<>();
    private HashMap<String, Double> totalTime = new HashMap<String, Double>();
    
   /**
    * Exports each team element information to a .csv file 
    * @throws IOException
    */
    
    public File toCSV() throws IOException{
    	List<Tabela> tabelas = new ArrayList<>();
    	tabelas.addAll(sprintTime());
    	tabelas.addAll(humanResourcesCost());
    	tabelas.addAll(artifact());
    	tabelas.addAll(noArtifact());
    	
    		File file = new File("Data.csv"); 
    		file.createNewFile();
        	try (FileWriter csv = new FileWriter(file)) {
				for(Tabela tabela: tabelas){
					//csv.write(tabela.getTitle()+"\n");
					for(int i = 0; i<nomes.size(); i++){
							for(int j = 0; j<tabela.getCol();j++) csv.write(tabela.getObject(i,j)+",");
							
						csv.write(tabela.getTitle()+"\n");
					}
					csv.write("\n");
				}
			}
			return file;				
        
	}
    
    
    public List<Tabela> noArtifact(){
    	HashMap<String, Double> horas = new HashMap<String, Double>();
    	HashMap<String, Integer> atividades = new HashMap<String, Integer>();
    	HashMap<String, Double> lixo = new HashMap<String, Double>();
    	int row = 0;

    	for(Member m: nomes){
    		horas.put(m.getUsername(),0.0);
    		atividades.put(m.getUsername(),0);
    		lixo.put(m.getUsername(),0.0);
    		row++;
    	}
    	tempo = new ArrayList<>();
	    	for(Card c : cards){
	    		int noArtifact=1;
	    		List<Label> labels = c.getLabels();
			    	for(Label l : labels){
			    			if(l.getName().equals("Commit")){
			    				noArtifact=0;
			    			}
			    	}
			    	if(noArtifact==1) {
	    				totalTime(c,horas);
			    		contarAtividades(c,atividades);
			    	}
			    	
			}   
	    	
	    	Tabela t = new Tabela("No Artifact", row+1, 4);
	    	t.addColumnName("Username",0);
	    	t.addColumnName("Numero atividades",1);
	    	t.addColumnName("Tempo total (horas)",2);
	    	t.addColumnName("Custo (€)",3);
	    	int x=0;
	    	
	    	for(Member m: nomes){
	    		t.addData(m.getUsername(),x,0);
	    		t.addData(atividades.get(m.getUsername()),x,1);
	    		t.addData(horas.get(m.getUsername())/60,x,2);
	    		t.addData((horas.get(m.getUsername())/60)*20,x,3);
	    		x++;
	    	}
		
	    int atividadesTotal=0;
    	double horasTotal=0.0;
    	double custoTotal=0.0;
    	for(Member m: nomes){
    		atividadesTotal+=atividades.get(m.getUsername());
    		horasTotal+=horas.get(m.getUsername());
    		custoTotal+=horas.get(m.getUsername())*20;
	    } 
    	t.addData("Total",x,0);
		t.addData(atividadesTotal,x,1);
		t.addData(horasTotal/60,x,2);
		t.addData(custoTotal/60,x,3);
	  	t.createScrollPane();
    	tempo.add(t);

    	return tempo;
    }
    
    
    public List<Tabela> artifact(){
    	HashMap<String, Double> horas = new HashMap<String, Double>();
    	HashMap<String, Integer> atividades = new HashMap<String, Integer>();

    	for(Member m: nomes){
    		horas.put(m.getUsername(),0.0);
    		atividades.put(m.getUsername(),0);
    	}
    
    	tempo = new ArrayList<>();
	    	for(Card c : cards){
	    		List<Label> labels = c.getLabels();
			    	for(Label l : labels)
			    			if(l.getName().equals("Commit")){
			    				totalTime(c,horas);
					    		contarAtividades(c,atividades);			    	
			    			}		    	
			}   
	    	
	    	Tabela t = new Tabela("Artifact", nomes.size()+1, 4);
	    	t.addColumnName("Username",0);
	    	t.addColumnName("Numero atividades",1);
	    	t.addColumnName("Tempo total (horas)",2);
	    	t.addColumnName("Custo (€)",3);
	    	
	    	int x=0;
	    	
	    	for(Member m: nomes){
	    		t.addData(m.getUsername(),x,0);
	    		t.addData(atividades.get(m.getUsername()),x,1);
	    		t.addData(horas.get(m.getUsername())/60,x,2);
	    		t.addData((horas.get(m.getUsername())/60)*20,x,3);
	    		x++;
	    	}
	    	
	    int atividadesTotal=0;
    	double horasTotal=0.0;
    	double custoTotal=0.0;
    	for(Member m: nomes){
    		atividadesTotal+=atividades.get(m.getUsername());
    		horasTotal+=horas.get(m.getUsername());
    		custoTotal+=horas.get(m.getUsername())*20;
	    } 
    	t.addData("Total",x,0);
		t.addData(atividadesTotal,x,1);
		t.addData(horasTotal/60,x,2);
		t.addData(custoTotal/60,x,3);
	  	t.createScrollPane();
    	tempo.add(t);

    	return tempo;
    }

    
    public void contarAtividades(Card c, HashMap<String, Integer> atividades){
      	
		List<Action> actions = c.getActions();
		for (Action a : actions){
			String temp = a.getData().getText();
			for(Member m : nomes){
				if(temp!=null 
					&& temp.contains("plus!") 
				    && ( temp.contains(m.getUsername())
			    	|| temp.contains("global"))){
						atividades.put(m.getUsername(),atividades.get(m.getUsername())+1);						
				}
			}
		}		    		
	return;
}
    
    
    public List<PieChart> pieChart(List<Tabela> tabelas, String info){
    	List<PieChart> charts = new ArrayList<>();
    	for(Tabela t: tabelas){	
    		for(int x=1; x<t.getCol(); x++){
    			PieChart pie = new PieChart(t,info);
    			String title="Realizadas";
    			if(x==2) title = "Estimadas";
    			pie.createChart(nomes,0,x,title);
    			charts.add(pie);
    		}
    	}
    	return charts;
    }
       
    public List<Tabela> humanResourcesCost(){
    	List<Tabela> cost = new ArrayList<>();
    	tempo = sprintTime();
    	for(Tabela temp : tempo){
    		Tabela t = new Tabela(temp.getTitle(), nomes.size(), 2);
    		t.addColumnName("Username",0);
	    	t.addColumnName("Custo recursos humanos (€)",1);
	    	t.createScrollPane();
    		for(int x=0; x<nomes.size();x++){
    			t.addData((String) temp.getObject(x,0),x,0);
    			for(int y = 1; y<2;y++) t.addData(((double) temp.getObject(x,y))*20.0,x,y);   				
    		}
    		cost.add(t);
    	}   	
    	return cost;
    }
   

    public List<Tabela> sprintTime(){
    
    	tempo = new ArrayList<>();
    	
    	for(Member m: nomes) totalTime.put(m.getUsername(),0.0);
       		
    	for(Sprint sprint: sprints){	
			

    		sprint.addTimes(nomes);		  
	    	Tabela t = new Tabela(sprint.getName(), nomes.size(), 3);
	    	
	    	t.addColumnName("Username",0);
	    	t.addColumnName("Tempo realizado (horas)",1);
	    	t.addColumnName("Tempo previsto (horas)",2);
	    	
	    	int x=0;   	
	    	for(Member m: nomes){
	    		t.addData(m.getUsername(),x,0);
	    		t.addData(sprint.getActualTime(m)/60,x,1);
	    		t.addData(sprint.getEstimatedTime(m)/60,x,2);
	    		x++;
	    	}
	    	
	    	t.createScrollPane();
	    	tempo.add(t);
	    }
    	
		Tabela t = new Tabela("Total", nomes.size(), 2);
		t.addColumnName("Username",0);
    	t.addColumnName("Tempo total (min)",1);
    	for (Card c : cards) totalTime(c,totalTime);  
    	int x=0;
    	for(Member m: nomes){
    		t.addData(m.getUsername(),x,0);
    		t.addData(totalTime.get(m.getUsername())/60,x,1);
    		x++;
	    }   	
    	t.createScrollPane();
    	
    	tempo.add(t);
    	return tempo;
    }
   
    public void totalTime(Card c, HashMap<String, Double> total){
      	
			List<Action> actions = c.getActions();
			for (Action a : actions){
				String temp = a.getData().getText();
				for(Member m : nomes){
					if(temp!=null 
						&& temp.contains("plus!") 
					    && ( temp.contains(m.getUsername())
				    	|| temp.contains("global"))){
							String[] arrOfStr = temp.split(" ", 3);
							String[] previstas_realizadas = arrOfStr[2].split("/",2);
							total.put(m.getUsername(),total.get(m.getUsername())+Double.parseDouble(previstas_realizadas[0]));
					}
				}
			}		    		
		return;
    }
   
   /**
     * returns the start and end date for each created function as a String
     * @return
     * the start and end date for each created function as a String
     */
    public String functionDate(){
    	String s="";
    	for (Card c : cards) {
    		List<Label> labels = c.getLabels();
			for (Label l : labels) if(l.getName().equals("function")) s+=c.getName()+":\n" + c.getDesc() + "\n\r";		
    	}
    	return s;
    }

   /**
     * Returns, for each sprint, the result conclusions text
     * @return
     * a String with every Sprint text
     */
    //MELHORAR COM O GETLIST PELO ID
    public String sprintsText(){
    	String txt = "";
    	for(Sprint sprint : sprints){
    		txt += sprint.getName().toUpperCase() + "\n\r";
    		String planning="PLANNING\n";
        	String review="REVIEW\n";
        	String retrospective="RETROSPECTIVE\n";
    		List<Card> cards = sprint.getCards();
    		for(Card card : cards){
    				if(card.getIdList().equals(getListId("Sprint Planning"))) planning+=card.getDesc();
    				if(card.getIdList().equals(getListId("Sprint Review"))) review+=card.getDesc();
    				if(card.getIdList().equals(getListId("Sprint Retrospective"))) retrospective+=card.getDesc();
    			}
    		
    		txt+=planning+"\n\r"+review+"\n\r"+retrospective+"\n\r";
    	}
    	return txt;
    }
   
   /**
     * Returns a String with every Product Backlog item for each Sprint
     * @return
     * every Product Backlog item for each Sprint as a String
     */
    public String productBacklog(){
    	String s="";
    	
    	for(Sprint sprint : sprints){
    		List<Card> cards = sprint.getCards();
    		s+=sprint.getText().toUpperCase();
    		for(Card card : cards)
    			if(card.getIdList().equals(getListId("Product Backlog"))) 
    				s+=card.getName()+"\n";
    		s+="\n";   		
    	}
    	return s;
    }
    
    public List<Card> getCardsOfList(String name){
    	List<Card> list = new ArrayList<>();
    	String id ="";
    	for (TList l : lists) if(l.getName().contains(name)) id = l.getId();
    	for(Card c : cards) if(c.getIdList().equals(id)) list.add(c);
    	return list;
    }
    
    private String getListId(String name){
    	for (TList l : lists) if(l.getName().contains(name)) return l.getId();
    	return "";
    }
    
    public void setSprints(){
    	List<Card> list = getCardsOfList("Sprint Planning");
    	for(Card c : list){ 
    		Sprint s = new Sprint(c.getName());
    		String[] data = c.getDesc().split("___",3);
    		s.setDate(data[1]);
    		sprints.add(s);
    	}
     	addCardsToSprint();
    } 
  
    /**
     * Returns a List<String> with every sprint
     * @return
     * a List<String> with every sprint
     */  
    public List<Sprint> getSprints(){
    	return sprints;	
    }
    
    public void addCardsToSprint(){
    	for(Card card : cards){
    		List<Label> labels = card.getLabels();
    		for(Label l : labels){
    			Sprint s = getSprint(l.getName());
    			if(s!=null) s.addCard(card);
    		}
    	}
    }
    
   
    public Sprint getSprint(String name){
    	for(Sprint s : sprints) if(s.getName().equals(name)) return s;
    	return null;
    }
    
   /**
     * returns the date project has started as a String
     * @return
     * project start date as a String
     */
    public String projectDate(){
    	for(Card c : cards) if(c.getName().equals("Data de inicio do projeto")) return c.getDue().toString();
    	return null;
    }	
    
<

    /**
     * Returns the project ID as a String
     * @return
     * Project ID as a String
     */
    public String projectID(){
    	 return board.getName()+ " - id: " +board.getId();
    }
  
    /**
     * Creates a new Card in an existing List
     * @param s
     * List name where the card will be created
     * @param card_name
     * New card name
     */   
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
  
    /**
     * Returns A string with all Lists at this Trello project
     * @return
     * All Lists in the Trello project as a String
     */    
    public String lists(){ 	
    	 String string = "";
         for (TList list : lists) {
          	 string += list.getName() + "\r\n";
         }
         return string;
    }
    
    /**
     * Returns a String with all project members
     * @return
     * All project members as a String
     */
	public String members(){
		String string = "";
	    for (Member names : nomes) {
	    	string += names.getUsername() + "\r\n";
	    	
	    }
	    return string;
	}
	
	/**
	 * Connects to a Trello project
	 * @param s1
	 * Trello key
	 * @param s2
	 * Trello Access Token
	 * @param s3
	 * Trello board ID
	 */
	public void setUp(String s1, String s2, String s3){
    	trelloKey = s1;
    	trelloAccessToken = s2;
    	boardID = s3;
    	trelloApi = new TrelloImpl(trelloKey, trelloAccessToken, new ApacheHttpClient());
    	board = trelloApi.getBoard(boardID);
    	nomes = board.fetchMembers();
    	lists = board.fetchLists();
    	cards = board.fetchCards();
    	setSprints();
    }
	
}

