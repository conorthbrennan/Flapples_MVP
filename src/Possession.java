import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Possessions: A type of card that you can play into your holding pen.
 * @author Rebecca Thomas, Billy Leete, Conor Brennan
 *
 */
public class Possession extends Card {
	// --- ALSO MAYBE SOME KIND OF EXTRA IDENTIFYING DATA-CONTENT.
	ArrayList<String> categories = new ArrayList<String>();

	@Override
	public void playCard(Player pl, Board b) {
		Deck hand = pl.getHand();
		Deck holdingPen = pl.getHoldingPen();
		//Move the card from the hand to the holding pen.
		hand.removeCard(this);
		holdingPen.addCard(this);
		this.location = pl.holdingPen;
	}
	
	/**
	 * 
	 * @param titl the title
	 * @param pic the icon of the possession
	 * @param descrip the description of the possession
	 * @param id the id number of the card
	 * @param locate the deck location of the card
	 */
	public Possession(String titl, BufferedImage pic, String descrip, int id, Deck locate, ArrayList<String> cats){
		super(titl, pic, descrip, id, locate);
		categories = cats;
	}

}
