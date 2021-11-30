package Backend;
import java.util.Date;
import java.util.List;

import com.julienvey.trello.Trello;
import com.julienvey.trello.domain.Board;
import com.julienvey.trello.domain.Card;
import com.julienvey.trello.domain.Label;
import com.julienvey.trello.domain.Member;
import com.julienvey.trello.domain.TList;
import com.julienvey.trello.impl.TrelloImpl;
import com.julienvey.trello.impl.http.ApacheHttpClient;

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
    
    
    
    /*
    public String sprintDate(){
    	
    	
    	for (TList l : lists) {
	    	if(l.getName().contains("Sprint")){
	    		cards = l.getCards();
	    		s += l.getName().toUpperCase() + ": \r\n" ;
	    		for (Card c : cards) {
					System.out.println(c.getName());

	    			s += c.getName()+ ": " + c.getDateLastActivity().toString();
	    		}
	    	}
	    }
    	return s;
    }
    */
    
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
    	String s="";
    	
    	for (TList l : lists) 
    		if(l.getName().contains("Product Backlog"))
    			for (Card c : cards) {
    				List<Label> ola = c.getLabels();
    				for(Label l2 : ola)
    				if(c.getIdList().equals(l.getId())) s +=  "ola : " + c.getPos() + "\n\r";
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
