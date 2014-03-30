import java.awt.image.BufferedImage;


/**
 * A card that changes the rules of the game.
 * @author Becky Thomas,Conor Brennan, Billy Leete
 *
 */
public class RuleCard extends Card{

	/**
	 * Type indicates whether it is a "Play X" card (value 1) or a "Draw X" card (value 2) or
	 *  a "Possession Limit" card (value 3) or a "Hand Limit" card (value 4).
	 * 
	 */
	public int type;
	public int theNumber;
	
	/**
	 * 
	 * @param titl The title
	 * @param pic The picture
	 * @param descrip The description
	 * @param id The id
	 * @param locate The location
	 * @param typ The type of card: 1= play,2=draw,3= poss limit, 4= hand limit
	 * @param metaNum The number associated. The "X" in Play X or Draw X. The number in the limits.
	 */
	public RuleCard(String titl, BufferedImage pic, String descrip, int id, Deck locate, int typ, int metaNum) {
		super(titl, pic, descrip, id, locate);
		type = typ;
		theNumber = metaNum;
		
	}

	@Override
	public void playCard(Player pl, Board b) {
		pl.getHand().removeCard(this);
		b.getRules().addCard(this);
		this.location = b.rules;
		
		//STILL NEEDS TO DETERMINE WHETHER THE INCOMING RULE KILLS AN OLD RULE
	}

}
