/**
 * Assets: A type of card.
 * @author Rebecca Thomas
 *
 */
public class Possessions extends Card {
	// --- ALSO MAYBE SOME KIND OF EXTRA IDENTIFYING DATA-CONTENT.
	//todo- add constructor.

	@Override
	public void playCard(Player pl, Board b) {
		Deck hand = pl.getHand();
		Deck holdingPen = pl.getHoldingPen();
		//Move the card from the hand to the holding pen.
		
	}

}
