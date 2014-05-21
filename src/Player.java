/**
 * A Player.
 * @author Rebecca Thomas, Billy Leete,Conor Brennan
 *
 */
public class Player implements GameObject {
	public Game g;	// all players are held in the Board's list. They are level-1 Game Entities.
	public String name;
	public Deck hand, holdingPen;
	public int numPlaysSoFar = 0;
	public boolean[] AlertsEnabled = {true,true,true,true,true};
				/* to make life easier, alerts may be personally disabled!
				 *	0 - card played
				 *	1 - 
				 *	2 - 
				 *	3 - 
				 *	4 - 
				 */
	
	/**
	 * constructor
	 * @param n - name of player
	 */
	public Player(String n){
		name = n;
	}//end constructor
	
	/**
	 * return the game
	 */
	public Game getGame() { return g; }
	
	/**
	 * return the hand
	 * @return
	 */
	public Deck getHand(){
		return hand;
	}//end getHand()
	
	/**
	 * get the holding pen
	 * @return
	 */
	public Deck getHoldingPen(){
		return holdingPen;
	}//end getHoldingPen()
	
	/**
	 * sets the player's hand
	 * @param h - the new hand
	 */
	public void setHand(Deck h){
		hand = h;
	}//end setHand(Deck h)
	
	/**
	 * sets the player's holding pen
	 * @param hp - the new holding pen
	 */
	public void setHoldingPen(Deck hp){
		holdingPen = hp;
	}//end setHoldingPen()
	
	/**
	 * get the player's name
	 * @return
	 */
	public String getName(){
		return name;
	}//end getName()
	
	/**
	 * set the player's name
	 * @param n - the new name
	 */
	public void setName(String n){
		name = n;
	}//end setName()
	
	/**
	 * This adds the card to the receiving deck of cards. 
	 * This might be the hand or the deck.
	 * @param card
	 * @param receiver
	 */
	public void addCard(Card card, Deck receiver){
		receiver.addCard(card);
	}//end addCard()
	
}//end class
