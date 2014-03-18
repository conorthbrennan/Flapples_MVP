import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author Billy Leete
 *
 */
public class Game implements GameObject
{	// The Game should be the centralized controller.
	// it DOES things, it receives messages, has many roles.
	
	
	// fields that a Game has (and .get methods)
	private Board gameboard;
	public Board getBoard() { return gameboard;	}
	
	
	public Game getGame() {  return this; }
	
	// Game methods
	
	public Game() {
		int plrs = Integer.parseInt(prompt("how many players?"));
		gameboard = new Board(this, plrs);
	}
	
	/** ***GOALIE***
	 * Handles goals
	 * @param arr the goal in question
	 */
	public void handleGoal(Goal arr) {
		// look up rules
		Deck rulebook = gameboard.getRules();
		// deal with stuff now...
		// ... so, dispense with old goal.
		//gameboard.goals.addCard(arr); // but it's private. We need more methods, here..
		// remove old goals, add new card, and also direct other traffic.
		
	}
	
	/**
	 * This checks whether a player has satisfied the goal
	 */
	public void evaluateGoalMatching() {
		// ??? a method used in gameplay
		//go through each player and check their holding pens against the win conditions of the current goals
		Deck gls = gameboard.getGoals();
		ArrayList<Player> plrs= gameboard.getPlayers();
		for(Player pl : plrs){
			//for(Goal gl:gls.getDeck()){
				Deck hp = pl.getHoldingPen();
				//stuff
			//}
			
		}
	
	}
	
	// utility methods (to be changed according to circumstance
	public String prompt(String msg) {
		System.out.print(msg +"  _");
		Scanner user_input = new Scanner( System.in );
		String ans = user_input.next( );
		return ans;
	}
	
}
