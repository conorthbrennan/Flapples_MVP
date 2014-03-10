/**
 * 
 * @author Rebecca Thomas
 *
 */
public class Player {
	
	private String name;
	private Deck hand, holdingPen;
	
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
	
	/**
	 * This adds the card to the receiving deck of cards. 
	 * This might be the hand or the deck.
	 * @param card
	 * @param receiver
	 */
	public void addCard(Card card, Deck receiver){
		//receiver.addCard(card);
	}
	
}
