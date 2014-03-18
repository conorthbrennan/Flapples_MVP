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
		setGame(game);//super.g = game;
		// set up configuration
	}
	@Override
	public void playCard(Player pl, Board b) {
		//this.location.removeCard(this.ID); 		// how to implement this paradigm?
		this.g.handleGoal(this);
		//this.location = this.g.getBoard().getGoals;
	}
	
	/**
	 * Returns whether or not the player with this deck has won
	 * @param holdingPen
	 * @return Won?
	 */
	public boolean hasWon(Deck holdingPen){
		//stuff
		
		return false;
	}

}
