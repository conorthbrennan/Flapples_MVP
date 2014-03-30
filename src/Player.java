/**
 * A Player.
 * @author Rebecca Thomas, Billy Leete,Conor Brennan
 *
 */
public class Player implements GameObject {
	public Game g;	// all players are held in the Board's list. They are level-1 Game Entities.
	public String name;
	public Deck hand, holdingPen;
	public int numPts = 0;
	
	public Player(String n){
		name = n;
	}
	
	public Game getGame() { return g; }
	
	public Deck getHand(){
		return hand;
	}
	
	public Deck getHoldingPen(){
		return holdingPen;
	}
	
	public void setHand(Deck h){
		hand = h;
	}
	
	public void setHoldingPen(Deck hp){
		holdingPen = hp;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String n){
		name = n;
	}
	
	public void setPts(int n){
		numPts=n;
	}
	
	public int getPts(){
		return numPts;
	}
	
	/**
	 * This adds the card to the receiving deck of cards. 
	 * This might be the hand or the deck.
	 * @param card
	 * @param receiver
	 */
	public void addCard(Card card, Deck receiver){
		receiver.addCard(card);
	}
	
}
