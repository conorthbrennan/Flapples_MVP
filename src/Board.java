import java.util.ArrayList;

/**
 * The board.
 * @author Rebecca Thomas,Billy Leete,Conor Brennan
 *
 */
public class Board {
	public ArrayList<Player> players;
	public Deck goals, rules, discard, deckdeck;
	public Game g;
	
	public Board(Game game, int num){//game needed a constructor like this but I don't know what to fill it with
		g = game;
		players = new ArrayList<Player>();
		for (int i=0;i<num;i++) {
			players.add(new Player(g.prompt("name of player "+ (i+1) )) );
		}
	}
	
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
