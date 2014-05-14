import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * The board.
 * @author Rebecca Thomas,Billy Leete,Conor Brennan
 *
 */
public class Board {
	public ArrayList<Player> players;
	public Deck goals, rules, discard, drawPile;
	public Game g;
	
	public Board(Game game, int num,JFrame f){//game needed a constructor like this but I don't know what to fill it with
		g = game;
		players = new ArrayList<Player>();
		for (int i=0;i<num;i++) {
			//players.add(new Player(g.prompt("name of player "+ (i+1) )) );
			
			String n = askPlayerName(i+1,f);
			if(!n.contains("AI"))				
				players.add(new Player(n));
			else
				if(n.contains("1"))
					players.add(new ArtificialIntelligence(n,1,game));
				else if(n.contains("2"))
					players.add(new ArtificialIntelligence(n,2,game));
				else if(n.contains("3"))
					players.add(new ArtificialIntelligence(n,3,game));
				else if(n.contains("0"))
					players.add(new ArtificialIntelligence(n,0,game));
				else
					players.add(new ArtificialIntelligence(n,0,game));
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
	
	public static String askPlayerName(int n, JFrame overallFrame){

		Object[] possibilities = null;//{"ham", "spam", "yam"};
		String d = "What is player " + n + " 's name?\nFor an AI, type AI and a hardness level from 0-2.\nAI level defaults to the lowest if you don't give a number in its name.\nPlease list a human player before an AI.\nEx: AI1, blaAIbla2,0AI";
		String s = (String)JOptionPane.showInputDialog(
				overallFrame,
				d,
				"WHAT'S YO NAME?",
				JOptionPane.QUESTION_MESSAGE,
				null,
				possibilities,
				"NameNameName");
		return s;
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
