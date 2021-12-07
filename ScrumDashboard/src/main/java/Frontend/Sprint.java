package Frontend;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.julienvey.trello.domain.Action;
import com.julienvey.trello.domain.Card;
import com.julienvey.trello.domain.Member;

public class Sprint {

	private String name;
	private String text;
	private String date;
	private List<Card> cards = new ArrayList<>();
	private HashMap<String, Double> realizados = new HashMap<String, Double>();
	private HashMap<String, Double> previstos = new HashMap<String, Double>();
	
	public Sprint(String n) {
		// TODO Auto-generated constructor stub
		this.name=n;
		this.text = this.name.toUpperCase()+"\n";
	}

	public String getName() {
		return name;
	}

	public String getText() {
		return text;
	}

	public void addText(String text) {
		this.text += text + "\n";
	}
	
	public String getDate() {
		return date.toString();
	}

	public void setDate(String start) {
		this.date = start;
	}

	public List<Card> getCards() {
		return cards;
	}

	public void addCard(Card c) {
		cards.add(c);
	}
	
	public void reserLists(List<Member> nomes){
    	for(Member m: nomes){
    		realizados.put(m.getUsername(),0.0);
    		previstos.put(m.getUsername(),0.0);
    	}
	}
	
	public void addTimes(List<Member> nomes){
		reserLists(nomes);
		for(Card c : cards){
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
							realizados.put(m.getUsername(),realizados.get(m.getUsername())+Double.parseDouble(previstas_realizadas[0]));
							previstos.put(m.getUsername(),previstos.get(m.getUsername())+Double.parseDouble(previstas_realizadas[1].replaceAll("\\s+","")));
					}
				}
			}
		}
	return;
	}
	
	public double getActualTime(Member m){
		return realizados.get(m.getUsername());
	}
	
	public double getEstimatedTime(Member m){
		return previstos.get(m.getUsername());
	}

}
