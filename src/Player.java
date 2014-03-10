/**
 * 
 * @author Rebecca Thomas
 *
 */
public class Player {
	
	private String name;
	private Deck hand, holdingPen;
	
	private Deck getHand(){
		return hand;
	}
	
	private Deck getHoldingPen(){
		return holdingPen;
	}
	
	private void setHand(Deck h){
		hand = h;
	}
	
	private void setHoldingPen(Deck hp){
		holdingPen = hp;
	}
	
	private String getName(){
		return name;
	}
	
	private void setName(String n){
		name = n;
	}
}
