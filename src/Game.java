import java.awt.Canvas;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * The game. It controls things.
 * @author Billy Leete, Rebecca Thomas, Conor Brennan
 *
 */
public class Game extends Canvas implements GameObject, Runnable, KeyListener
{	// The Game should be the centralized controller.
	// it DOES things, it receives messages, has many roles.


	// fields that a Game has (and .get methods)
	private Thread thisThread;//bleepbloop

	public Board gameboard;
	public Board getBoard() { return gameboard;	}


	public Game getGame() {  return this; }

	// Game methods

	public Game(JFrame f) {
		super();		
		thisThread=new Thread(this); //create a thread for an object
		thisThread.start(); 
		//int plrs = Integer.parseInt(prompt("how many players?"));
		int plrs = askNumPlayers(f);
		gameboard = new Board(this, plrs,f);
	}
	
	public static int askNumPlayers(JFrame overallFrame){
		Object[] possibilities = null;
		String s = (String)JOptionPane.showInputDialog(
				overallFrame,
				"How many players?",
				"Tell me the number of the players...",
				JOptionPane.QUESTION_MESSAGE,
				null,
				possibilities,
				"0");
		return Integer.parseInt(s);
	}

	/** ***GOALIE***
	 * Handles goals
	 * @param arr the goal in question
	 *//*
	public void handleGoal(Goal arr) {
		// look up rules
		Deck rulebook = gameboard.getRules();
		// deal with stuff now...
<<<<<<< HEAD
		// ... so, dispense with old goal.
		gameboard.getGoals().addCard(arr);
		// remove old goals, add new card, and also direct other traffic.
		
	}
	
=======

		// remove old goals, add new card <- that was done in the goal's playCard method
		evaluateGoalMatching();
		//and also direct other traffic.

	}*/

	/**
	 * This checks whether a player has satisfied the goal
	 */
	public boolean evaluateGoalMatching() {
		//go through each player and check their holding pens against the win conditions of the current goals
		Deck gls = gameboard.getGoals();
		ArrayList<Player> plrs= gameboard.getPlayers();
		boolean hasAnyoneWon = false;
		Player who = null;
		for(Player pl : plrs){
			for(Card gl:gls.deck){
				Goal goal = (Goal) gl;
				Deck hp = pl.getHoldingPen();
				boolean won = goal.hasWon(hp);
				
				if(won){
					//System.out.println(goal.getTitle() +" has won for "+ pl.name);
					//System.out.println("\t with "+ goal.necCardsToWin.size() +" reqs");
					
					hasAnyoneWon = true;	
					who = pl;
				}		
			}
		}

		if(hasAnyoneWon){

			System.out.println("YOU, "+ who.name +", HAVE WON!!!!");
		}

		return hasAnyoneWon;
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
		game.addKeyListener(game);
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


	@Override
	public void keyPressed(KeyEvent arg0) {


	}


	@Override
	public void keyReleased(KeyEvent arg0) {


	}


	@Override
	public void keyTyped(KeyEvent arg0) {


	}

	/**
	 * 
	 * @param g - the graphics
	 */
	public void nextTurn(Graphics g){
		paint(g);
	}

	public void paint(Graphics g){

	}
}
