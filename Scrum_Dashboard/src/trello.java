import com.julienvey.trello.Trello;
import com.julienvey.trello.impl.TrelloImpl;
import com.julienvey.trello.impl.http.ApacheHttpClient;

private String trelloKey
private String trelloToken

public class trello {	
	
	public static Trello conexaoTrello(String trelloKey, String trelloToken) {
		Trello trelloApi = new TrelloImpl(trelloKey,trelloToken, new ApacheHttpClient()); 
		return trelloApi;
	}
	/*
	public static GitHub conexaoGitHub(String link) {
		GitHub gitHubApi; //adicionar conexao ao GitHub 
		return gitHubApi;
	}
	*/
}