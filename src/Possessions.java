import java.awt.image.BufferedImage;

/**
 * Assets: A type of card.
 * @author Rebecca Thomas, Billy Leete, Conor Brennan
 *
 */
public class Possessions extends Card {
	// --- ALSO MAYBE SOME KIND OF EXTRA IDENTIFYING DATA-CONTENT.

	@Override
	public void playCard(Player pl, Board b) {
		Deck hand = pl.getHand();
		Deck holdingPen = pl.getHoldingPen();
		//Move the card from the hand to the holding pen.
		hand.removeCard(this);
		holdingPen.addCard(this);
	}
	
	public Possessions(String titl, BufferedImage pic, String descrip, int id){
		super(titl, pic, descrip, id);
		
	}

}
