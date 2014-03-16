import java.util.ArrayList;

/**
 * 
 * @author Rebecca Thomas
 *
 */
// 3-16 - Board is not abstract. it should have a constructor.
public class Board {
	private Game g;
	private ArrayList<Player> players;
	private Deck goals, rules, discard, deckdeck;
	
	public Board(Game g0, int numPlayers) {
		g = g0;
		players = new ArrayList<Player>();
	}
	
	public Game getGame() { return g; }
	
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
