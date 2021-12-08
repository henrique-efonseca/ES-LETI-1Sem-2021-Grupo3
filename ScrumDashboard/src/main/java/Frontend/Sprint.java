package Frontend;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.julienvey.trello.domain.Action;
import com.julienvey.trello.domain.Card;
import com.julienvey.trello.domain.Member;

/**
 *Trello API 
 *@author Ricardo Paulo
 *@version 1.0
 */

public class Sprint {

	private String name;
	private String text;
	private String date;
	private List<Card> cards = new ArrayList<>();
	private HashMap<String, Double> realizados = new HashMap<String, Double>();
	private HashMap<String, Double> previstos = new HashMap<String, Double>();
	/**
	 * Constructor of a Sprint
	 * @param n
	 * name of sprint
	 */
	public Sprint(String n) {
		// TODO Auto-generated constructor stub
		this.name=n;
		this.text = this.name.toUpperCase()+"\n";
	}
/**
 * returns the name of a sprint
 * @return
 */
	public String getName() {
		return name;
	}
/**
 * returns the text of a sprint
 * @return
 */
	public String getText() {
		return text;
	}
/**
 * add text to a sprint
 * @param text
 */
	public void addText(String text) {
		this.text += text + "\n";
	}
	
	/**
	 * return the start & end date of a sprint
	 * @return
	 */
	public String getDate() {
		return date.toString();
	}
    /**
     * add the start & end date to a sprint
     * @param start
     */
	public void setDate(String start) {
		this.date = start;
	}
    /**
     * returns the cards of a sprint 
     * @return
     */
	public List<Card> getCards() {
		return cards;
	}
    /**
     * add a card to a sprint
     * @param c
     * card
     */
	public void addCard(Card c) {
		cards.add(c);
	}
	
	/**
	 * Reset lists
	 * @param nomes
	 */
	public void resetLists(List<Member> nomes){
    	for(Member m: nomes){
    		realizados.put(m.getUsername(),0.0);
    		previstos.put(m.getUsername(),0.0);
    	}
	}
	/**
	 * add spent and estimate time on a Sprint by a member
	 * @param nomes
	 */
	
		public void addTimes(List<Member> nomes){
		resetLists(nomes);
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
	/**
	 * returns time spent by a member
	 * @param m
	 * member
	 * @return
	 */
	public double getActualTime(Member m){
		return realizados.get(m.getUsername());
	}
	
	/**
	 * returns estimated time of a member
	 * @param m
	 * member
	 * @return
	 */
	public double getEstimatedTime(Member m){
		return previstos.get(m.getUsername());
	}

}
