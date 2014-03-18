import java.util.ArrayList;

/**
 * A card that involves a goal.
 * @author Rebecca Thomas, Billy Leete,Conor Brennan
 *
 */
public class Goal extends Card{
	// has to reference specific Possessions objects
	// has to have a method that interfaces with Game's evaluateGoalMatching method
	
	private ArrayList<Possessions> necCardsToWin;
	
	public Goal(Game game, ArrayList<Possessions> winCards) {
		super();//sets up blank card
		necCardsToWin=winCards;
		
		super.g = game;
		
		// set up configuration
	}
	@Override
	public void playCard(Player pl, Board b) {
		this.location.removeCard(this);; 		// how to implement this paradigm?
		this.g.handleGoal(this);
		this.location = this.g.getBoard().getGoals();
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
