import java.awt.image.BufferedImage;


/**
 * A card that changes the rules of the game.
 * @author Becky Thomas,Conor Brennan, Billy Leete
 *
 */
public class RuleCard extends Card{

	public RuleCard(String titl, BufferedImage pic, String descrip, int id) {
		super(titl, pic, descrip, id);
	}

	@Override
	public void playCard(Player pl, Board b) {
		pl.getHand().removeCard(this);
		b.getRules().addCard(this);
	}

}
