package ES2021987.es2021987;
import java.util.List;

import javax.swing.JOptionPane;

import com.julienvey.trello.Trello;
import com.julienvey.trello.domain.Board;
import com.julienvey.trello.domain.Card;
import com.julienvey.trello.domain.Member;
import com.julienvey.trello.domain.Membership;
import com.julienvey.trello.domain.TList;
import com.julienvey.trello.impl.TrelloImpl;
import com.julienvey.trello.impl.http.ApacheHttpClient;

public class TrelloAPI 
{
	private String trelloKey= "a04256995af78e5ea7bff424d82cf477";
    private String trelloAccessToken="47fd71497e5ffcb377ea49fd0302a42f66ba0a411829da35dac1ade25025e501";
    private String boardID = "60eae4ca19ea426cbba3021a";
    Trello trelloApi = new TrelloImpl(trelloKey, trelloAccessToken, new ApacheHttpClient());
    private Board board = trelloApi.getBoard(boardID);
    private List<Member> nomes = board.fetchMembers();
    private List<TList> lists = board.fetchLists();
    
    public void teste3(String s, String card_name){
	    for (TList l : lists) {
	    	if(l.getName().equals(s)){
	    		Card c = new Card();
	    		c.setName(card_name);
	    		trelloApi.createCard(l.getId(),c);
	    		return;
	    	}
	    }
    }
    
    public String teste2(){ 	
    	 String string = "";
         for (TList list : lists) {
          	 string += list.getName() + "\r\n";
         }
         return string;
    }
    
	public String teste(){
		String string = "";
	      //  List<Board> member=trelloApi.getMemberBoards("ricardonunosilvapaulo"); //podem consultar o vosso nome_utilizador na opção "profile and visibility" da vossa conta no trello
	      //  for (Board  quadro: member) {
	    System.out.println(board.getName()+ " - id: " +board.getId());
	            //board = trelloApi.getBoard(quadro.getId());
	    for (Member names : nomes) {
	    	string += names.getUsername() + "\r\n";
	    	
	    }
	    //System.out.println(string);
	    return string;
	}
	
    public static void main( String[] args )
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
    }
}
