import java.util.ArrayList;

/**
 * 
 * @author Rebecca Thomas
 *
 */
public class Board {
	private ArrayList<Player> players;
	private Deck goals, rules, discard, deckdeck;
	
	public Deck getDeckDeck(){
		return deckdeck;
	}
	
	public ArrayList<Player> getPlayers(){
		return players;
	}
	
	public Deck getGoals(){
		return goals;
	}
	
	public Deck getRules(){
		return rules;
	}
	
	public Deck getDiscards(){
		return discard;
	}
	
	public void addCard(Card c, Deck receiver){
		receiver.addCard(c);
	}
}
