import java.util.ArrayList;

/**
 * The board.
 * @author Rebecca Thomas,Billy Leete,Conor Brennan
 *
 */
public class Board {
	public ArrayList<Player> players;
	public Deck goals, rules, discard, drawPile;
	public Game g;
	
	public Board(Game game, int num){//game needed a constructor like this but I don't know what to fill it with
		g = game;
		players = new ArrayList<Player>();
		for (int i=0;i<num;i++) {
			players.add(new Player(g.prompt("name of player "+ (i+1) )) );
			//initialize hand and holding pen
			players.get(i).hand = new Deck(g);
			players.get(i).holdingPen = new Deck(g);
		}
		//initialize some of the decks:
		goals = new Deck(g);
		rules = new Deck(g);
		discard = new Deck(g);
		
		//Shuffle the drawPile
		drawPile = new MainDeck(g);
		drawPile.shuffle();
	}
	
	public Deck getDrawPile(){
		return drawPile;
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
