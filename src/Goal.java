/**
 * 
 * @author Rebecca Thomas
 *
 */
public class Goal extends Card{
	// has to reference specific Possessions objects
	// has to have a method that interfaces with Game's evaluateGoalMatching method
	
	public Goal(Game game, Object win_conditions) {
		super.g = game;
		// set up configuration
	}
	@Override
	public void playCard(Player pl, Board b) {
		//this.location.removeCard(this.ID); 		// how to implement this paradigm?
		this.g.handleGoal(this);
		//this.location = this.g.getBoard().goals;	// does not work; Board.goals is private
	}

}
