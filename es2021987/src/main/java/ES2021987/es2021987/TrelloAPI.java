package ES2021987.es2021987;
import java.util.List;

import com.julienvey.trello.Trello;
import com.julienvey.trello.domain.Board;
import com.julienvey.trello.domain.Member;
import com.julienvey.trello.domain.Membership;
import com.julienvey.trello.domain.TList;
import com.julienvey.trello.impl.TrelloImpl;
import com.julienvey.trello.impl.http.ApacheHttpClient;

/**
 * Hello world!
 *
 */
public class TrelloAPI 
{
	private static String trelloKey= "a04256995af78e5ea7bff424d82cf477";
    private static String trelloAccessToken="47fd71497e5ffcb377ea49fd0302a42f66ba0a411829da35dac1ade25025e501";
    private static String boardID = "60eae4ca19ea426cbba3021a";
	
	//public String getTrelloKey(){
	//	return trelloKey;
	//}
	
	//public String getTrelloKey(){
	//	return trelloKey;
	//}
	
	//public String getTrelloKey(){
	//	return trelloKey;
	//}
	
	public static void teste(){
		Trello trelloApi = new TrelloImpl(trelloKey, trelloAccessToken, new ApacheHttpClient());
		Board quadro = trelloApi.getBoard(boardID);
	      //  List<Board> member=trelloApi.getMemberBoards("ricardonunosilvapaulo"); //podem consultar o vosso nome_utilizador na opção "profile and visibility" da vossa conta no trello
	      //  for (Board  quadro: member) {
	    System.out.println(quadro.getName()+ " - id: " +quadro.getId());
	            //board = trelloApi.getBoard(quadro.getId());
	    List<Member> nomes = quadro.fetchMembers();
	    for (Member names : nomes) {
	    	System.out.println(names.getUsername());
	    }
	}
	
    public static void main( String[] args )
    {
    	
        Trello trelloApi = new TrelloImpl(trelloKey, trelloAccessToken, new ApacheHttpClient());
      
        Board board;
        teste();
           // List<TList> lists = quadro.fetchLists();
           // for (TList lista : lists) {
           //     System.out.println(lista.getName()+"- "+ lista.getId()+"-"+lista.getIdBoard());
           // }
        //}
    }
}
