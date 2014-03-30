import java.awt.Canvas;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The game. It controls things.
 * @author Billy Leete, Rebecca Thomas, Conor Brennan
 *
 */
public class Game extends Canvas implements GameObject, Runnable
{	// The Game should be the centralized controller.
	// it DOES things, it receives messages, has many roles.
	
	
	// fields that a Game has (and .get methods)
	private Thread thisThread;//bleepbloop
	
	public Board gameboard;
	public Board getBoard() { return gameboard;	}
	
	
	public Game getGame() {  return this; }
	
	// Game methods
	
	public Game() {
		super();		
		thisThread=new Thread(this); //create a thread for an object
		thisThread.start(); 
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
		gameboard.goals.drawCard(gameboard.discard, 1);
		gameboard.goals.addCard(arr);
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
		boolean hasAnyoneWon = false;
		for(Player pl : plrs){
			for(Card gl:gls.deck){
				Goal goal = (Goal) gl;
				Deck hp = pl.getHoldingPen();
				boolean won = goal.hasWon(hp);
				if(won)
					hasAnyoneWon = true;	
			}
		}
		
		if(hasAnyoneWon){
			//stuff
		}
	
	}
	
	// utility methods (to be changed according to circumstance
	public String prompt(String msg) {
		System.out.print(msg +"  _");
		Scanner user_input = new Scanner( System.in );
		String ans = user_input.next( );
		return ans;
	}


	@Override
	/**
	 * start the thread
	 */
	public void run() {
		// TODO Auto-generated method stub
		while(Thread.currentThread()== thisThread){ //am I running?
			repaint(); //redraw screen
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * Create Frame and Panel
	 * Call this method to start the game
	 * @param game canvas
	 * @param width width of game frame
	 * @param height height of game frame
	 */
	public static void createGameFrame(Game game, int width, int height){
		Frame myFrame=new Frame(); 
		 
		myFrame.setSize(  width,height); //frame size
		myFrame.setBackground(Color.white);
		 
		myFrame.add(game);
		//game.addKeyListener(game);
		//Make sure program ends when window is closed
		WindowAdapter d=new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
			
		};
		
		myFrame.addWindowListener(d);
		myFrame.setVisible(true); //see frame
		game.requestFocus(); //make sure the game is selected
	
		
	}
	
}
