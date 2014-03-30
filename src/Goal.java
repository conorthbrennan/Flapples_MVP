import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * A card that involves a goal.
 * @author Rebecca Thomas, Billy Leete,Conor Brennan
 *
 */
public class Goal extends Card{

	// has to reference specific Possessions objects
	// has to have a method that interfaces with Game's evaluateGoalMatching method
	
	public ArrayList<Possession> necCardsToWin;
	
	public Goal(Game game, String titl, BufferedImage pic, String descrip, int id, Deck locate, ArrayList<Possession> winCards) {
		super(titl, pic, descrip, id, locate);
	
		necCardsToWin=winCards;
		
		super.g = game;
		
		// set up configuration
	}
	@Override
	public void playCard(Player pl, Board b) {
		pl.hand.removeCard(this);
		
		//Dispense with old goal:
		if(b.goals.count() >= 1)//if there is a goal:
				b.goals.drawCard(b.discard, 1);//move the old goal to the discard pile
		b.goals.addCard(this);//add new goal
		
		//change the location of this goal:
		this.location = this.g.getBoard().getGoals();
		
		//handle the goal
		this.g.handleGoal(this);
	}
	
	/**
	 * Returns whether or not the player with this deck has won for this goal card
	 * @param holdingPen
	 * @return Won?
	 */
	public boolean hasWon(Deck holdingPen){
		for(Card n : necCardsToWin)
		{
			Card copyCard = holdingPen.search(n.ID);
			if (copyCard == null)//if you don't have this necessary card
				return false;
		}
		
		return true;
	}

}
