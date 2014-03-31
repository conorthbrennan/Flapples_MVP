import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;


public class OverallRunner
{
	private static JFrame overallFrame;
	private static int plIndex;
	private static Game g;
	private static boolean discarding;
	private static String message;
	
	public static void main(String [] args)
	{
		g = new Game();
		
		Board exampleBoard = g.gameboard;
		//When making the board, all the decks get initialized (including the shuffled drawpile and players' hands and hps)
		
		ArrayList<Player> plrs = exampleBoard.players;
		
		//Give each player three cards.
		for(int i = 0; i < 3; i++)
			for(Player pl : plrs)
			{
				exampleBoard.drawPile.drawCard(pl.getHand(), 1);
			}
		
		//Make sure to initialize the original frame
		overallFrame = new JFrame("Flapples");
		plIndex =0;
		
		//Make sure the first player draws the right number of cards:
		handleTurnChange(plrs.get(0));
		drawEverything(plrs.get(0),exampleBoard);
		//As the end turn button gets pressed, the turns commence.

	}

	/**
	 * This does the graphical interface.
	 */
	private static void drawEverything(Player p, Board b) {
		
		//set the look and feel
		try{
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		}
		catch(Exception e)
		{
			System.out.println("I laugh at your misery!");
		}

		Container uberpane = overallFrame.getContentPane();
		uberpane.removeAll();
		
		if(!g.evaluateGoalMatching())
		{
			//FlowLayout flow = new FlowLayout(FlowLayout.RIGHT,200,20);//alignment,hgap,vgap		
			BoxLayout box = new BoxLayout(uberpane,BoxLayout.PAGE_AXIS);//top to bottom
			uberpane.setLayout(box);

			JPanel playerInfoRow = setUpPlayerInfoRow(p);//This will hold the player's name at the top of the screen.
			JPanel goalsRow = setUpGoalsRow();//This will hold the goals in the second row.
			JPanel rulesRow = setUpRulesRow();//This will hold the rules in the third row.
			JPanel discardRow = setUpDiscardPileRow();//This will hold the discard pile in the fourth row.
			JPanel holdingPenRow = setUpHoldingPenRow(p);//This will be the player's holding pen in the fifth row.
			JPanel handRow = setUpHandRow(p);//This will hold the player's hand.
			
			//Add all the items to the pane
			uberpane.add(playerInfoRow);
			uberpane.add(goalsRow);
			uberpane.add(rulesRow);
			uberpane.add(discardRow);
			uberpane.add(holdingPenRow);
			uberpane.add(handRow);
			
			overallFrame.pack();
			overallFrame.setVisible(true);
			overallFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		else
		{
			JTextArea youWon = new JTextArea();
			youWon.setText("THE GAME HAS BEEN WON!!!!!");
			uberpane.add(youWon);
		}
		
	}
	
	/**
	 * This sets up the JPanel dealing with the Player's hand. Each card is a JButton that can be played.
	 * @param p The Player
	 * @param brd The Board
	 * @return The JPanel of JButtons describing the PLayer's hand.
	 */
	private static JPanel setUpHandRow(Player p) {
		JPanel handRow = new JPanel();
		//THIS SHALL BE A GRID OF BUTTONS
		
		GridLayout grid = new GridLayout(0,3);//any number of rows with 3 cards
		handRow.setLayout(grid);
		
		Deck hand = p.getHand();
		if(hand!= null)
		{
			ArrayList<Card> cards = hand.deck;//Get list of cards from the deck given
			for(Card cd: cards)//for each card
			{
				JButton b = new JButton(cd.getTitle());
				b.setText(cd.getTitle() + ": " + cd.getDescription());
				b.setIcon(new ImageIcon(cd.getPicture()));
				b = addListeners(b,cd,p);
				handRow.add(b);
			}
		}
		else{
			JTextArea blob = new JTextArea();
			blob.setText("The hand is null");
			handRow.add(blob);
		}
	
		return handRow;
	}
	
	/**
	 * This adds the proper action listener to each JButton representing a card.
	 * @param b The JButton that represents this card.
	 * @param cd The actual card
	 * @param p The player playing the card
	 * @param brd The board at that moment
	 * @return Returns the JButton that represents the card.
	 */
	private static JButton addListeners(JButton b,final Card cd, final Player p) {
		//The ActionListener for all the JButtons representing cards in the hand:
		//To be used to make sure the buttons could work:
		ActionListener alist = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!discarding)
				{
					if(canPlay(p))
					{
						//System.out.println("You played the " + cd.getTitle() + " card!");
						message = "You played the " + cd.getTitle() + " card!";
						cd.playCard(p, g.gameboard);
						p.numPlaysSoFar +=1;
						
						//if this is a rule, then change the rules.
						if(cd.getClass().equals((new RuleCard()).getClass()))
						{
							//System.out.println("It's a rule card!");
							replaceNumber((RuleCard) cd);
						}
						
						//REDRAW EVERYTHING!
						drawEverything(p, g.gameboard);
					}
					else
					{
						//System.out.println("You can't play that card, because you have surpassed the number of plays per turn allowed. Press 'End Turn'.");
						message = "You can't play that card, because you have surpassed the number of plays per turn allowed. Press 'End Turn'.";
						drawEverything(p,g.gameboard);
					}
				}
				else//you are discarding
				{
					cd.location.removeCard(cd);
					g.gameboard.addCard(cd, g.gameboard.discard);
					cd.location = g.gameboard.discard;
					//System.out.println("You discarded " + cd.getTitle() + "! Click 'Discard' again to discard a different card.");
					message = "You discarded " + cd.getTitle() + "! Click 'Discard' again to discard a different card.";
					discarding = false;
					drawEverything(p,g.gameboard);
				}
				
			}
		};
		
		b.addActionListener(alist);
		return b;
	}

	/**
	 * This sets up the JPanel about the Player's Holding Pen. Each card is represented by its title.
	 * @param p The Player
	 * @return the JPanel with the JTextArea of the cards' titles.
	 */
	private static JPanel setUpHoldingPenRow(Player p) {
		JPanel hpRow = new JPanel();
		String str = "Holding Pen: \n";
		Deck hp = p.getHoldingPen();//Get the deck of the holding pen from the player.
		
		if(!discarding)
		{
			JTextArea blob = listTitles(hp,str);
			hpRow.add(blob);//add the text to the panel
		}
		else
		{
			//ROW OF BUTTONS WHEN DISCARDING
			GridLayout grid = new GridLayout(0,3);//any number of rows with 3 cards
			hpRow.setLayout(grid);
			
			if(hp!= null)
			{
				ArrayList<Card> cards = hp.deck;//Get list of cards from the deck given
				for(Card cd: cards)//for each card
				{
					JButton b = new JButton(cd.getTitle());
					b.setText(cd.getTitle() + ": " + cd.getDescription());
					b.setIcon(new ImageIcon(cd.getPicture()));
					b.setBackground(Color.PINK);
					b = addListeners(b,cd,p);
					hpRow.add(b);
				}
			}
			else{
				JTextArea blob = new JTextArea();
				blob.setText("The holding pen is null");
				hpRow.add(blob);
			}
		}
		
		return hpRow;
	}
	
	/**
	 * This sets up the JPanel about the Discard Pile. Each card is represented by its title.
	 * @param b The Board
	 * @return The JPanel with the JTextArea of cards' titles
	 */
	private static JPanel setUpDiscardPileRow() {
		JPanel discardRow = new JPanel();
		String str = "Discard Pile: \n";
		Deck discards = g.gameboard.getDiscards();//Get the deck of all the discards from the board.
		JTextArea blob = listTitles(discards,str);
		discardRow.add(blob);//add the text to the panel
		return discardRow;
	}
	
	/**
	 * This sets up the JPanel about the Rules in play. Each card is represented by its title.
	 * @param b The Board
	 * @return The JPanel with the JTextArea of cards' titles.
	 */
	private static JPanel setUpRulesRow() {
		JPanel rulesRow = new JPanel();
		String str = "Rules: \n";
		Deck rules = g.gameboard.getRules();//Get the deck of all the rules from the board.
		JTextArea blob = listTitles(rules,str);
		rulesRow.add(blob);//add the text to the panel
		return rulesRow;
	}
	
	/**
	 * This sets up the JPanel about the Goals in play. Each card is represented by its title.
	 * @param b The Board
	 * @return The JPanel with JTextArea which has the titles of all its cards
	 */
	private static JPanel setUpGoalsRow() {
		JPanel goalsRow = new JPanel();
		String str= "Goals: \n";
		Deck goals = g.gameboard.getGoals();//Get the deck of all the goals from the board.
		JTextArea blob = listTitles(goals,str);
		goalsRow.add(blob);//add the text to the panel
		return goalsRow;
	}
	
	/**
	 * This sets up the JPanel that has the current Player's name and the JButton to end your turn.
	 * @param p The Player
	 * @param brd The Board
	 * @return The JPanel which has the JTextField of the Player's name and the end turn JButton
	 */
	private static JPanel setUpPlayerInfoRow(Player p) {
		JPanel plInfo = new JPanel();
	
		JTextField blob = new JTextField();
		String str= "Player: " + p.getName();
		//I might add more player info later!
		blob.setText(str);
		blob.setPreferredSize(new Dimension(str.length() * 10,str.length() * 2));
		
		JButton endTurn = endTurnButton(p);
		JButton discard = discardButton(p);
		
		JTextArea messages = new JTextArea();
		messages.setText(message);
		
		plInfo.add(blob);
		plInfo.add(endTurn);
		plInfo.add(discard);
		plInfo.add(messages);
		return plInfo;
	}
	
	/**
	 * Make the JButton that will let you discard other cards
	 * @param p The Player
	 * @return the JButton that lets you discard cards
	 */
	private static JButton discardButton(final Player p) {
		JButton discard = new JButton("Discard a card");
		ActionListener alist = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				discarding = true;
				drawEverything(p, g.gameboard);
			}
		};
		discard.addActionListener(alist);
		
		return discard;
	}
	

	/**
	 * Make the JButton that ends your turn
	 * @param nextPlayer The next person to play.
	 * @param brd The board as it is.
	 * @return The JButton, which will end your turn if you click it.
	 */
	private static JButton endTurnButton(final Player currentPlayer) {
		JButton endTurn = new JButton("End Your Turn.");

		ActionListener alist = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(canEnd(currentPlayer))
				{
					//Redraw everything:
					Player nextPlayer = getNextPlayer(currentPlayer);
					message = "Now it is " + nextPlayer.name + "'s turn.";
					handleTurnChange(nextPlayer);
					drawEverything(nextPlayer,g.gameboard);
				}	
				
			}
		};
		
		endTurn.addActionListener(alist);
		
		return endTurn;
	}

	/**
	 * Takes the current Player and returns the next Player
	 * @param currentPlayer the current Player
	 * @return the next Player
	 */
	private static Player getNextPlayer(Player currentPlayer) {
		ArrayList<Player> plrs = g.gameboard.players;
		
		//I think this gets the next player...
		int nextPlIndex = (plIndex + 1) % plrs.size();
		plIndex = nextPlIndex;
		
		return plrs.get(nextPlIndex);
	}
	

	/**
	 * Gets all the titles from a Deck and makes a JTextArea out of it. 
	 * @param d the Deck you want the titles of
	 * @param str the starting string for the text area
	 * @return the text area with all the titles
	 */
	private static JTextArea listTitles(Deck d, String str){
		JTextArea blob = new JTextArea();

		if(d != null && d.deck != null)
		{
			ArrayList<Card> cards = d.deck;//Get list of cards from the deck given
			for(Card cd: cards)//for each card
			{
				str += cd.getTitle() + '\n';//add its title to the string str
			}
		}
		else
		{
			str+= "<null>";
		}
		
		blob.setText(str);//set the text of the textArea to str
		blob.setPreferredSize(new Dimension(str.length() * 10,str.length() * 3));//make the TextArea big enough to read
		
		return blob;
	}
	
	/**
	 * This determines what needs to be done before the next Player is allowed to play any cards.
	 * Right now that is limited to drawing cards beforehand. And also, reseting numPlaysSoFar.
	 * @param nextPlayer
	 */
	private static void handleTurnChange(Player nextPlayer) {
		
		//Drawing cards
		//This should give the player the number of cards as specified by the "Draw X" card in play.
		//If there isn't one in play, it defaults to Draw 1.
		Deck dRules = g.gameboard.rules;

		nextPlayer.numPlaysSoFar = 0;
		/* If the deck of rules contains the "Draw 2" card, which has id 4, 
		 * then draw 2 cards
		 * */
		if(dRules.search(4)!=null)
		{
			//Add two cards from the draw pile to the next player's hand
			checkDrawPile(2);
			g.gameboard.drawPile.drawCard(nextPlayer.hand, 2);
		}
		else
		{
			checkDrawPile(1);
			g.gameboard.drawPile.drawCard(nextPlayer.hand, 1);
		}
		
	}

	/**
	 * This checks whether or not there are enough cards in the draw pile to be drawn.
	 * If not, then the discard pile will be shuffled and used for the draw pile.
	 * @param drawNum the number of cards to draw
	 */
	private static void checkDrawPile(int drawNum) {
		if(g.gameboard.drawPile.count() < drawNum)
		{
			//Add the shuffled discard pile to the drawpile:
			g.gameboard.discard.shuffle();
			g.gameboard.discard.drawCard(g.gameboard.drawPile, g.gameboard.discard.count());
		}
		
		if(g.gameboard.drawPile.count() < drawNum)
		{
			//LOL. There aren't enough cards in the draw pile and the discard pile combined to draw enough cards.
			//Have fun with errors!!
			System.out.println("The standard deck hasn't been filled with cards yet, so screw you!");
		}
			
	}
	
	/**
	 * This determines whether or not you can play another card
	 * @param p The Player
	 * @return whether you can play another card
	 */
	private static boolean canPlay(Player p) {
		int n = determineNumber(1);
		if(p.numPlaysSoFar < n)
			return true;
		else
			return false;
	}

	/**
	 * This determines what limit (of this type) is currently in play 
	 * @param numType The type of rule you're looking for. play is 1. draw is 2. poss lim is 3. hand lim is 4.
	 * @return the number associated with this type
	 */
	private static int determineNumber(int numType) {
		Deck dRules = g.gameboard.rules;
		ArrayList<Card> rules = dRules.deck;
 		for(Card cd : rules)
 		{
 			RuleCard rl = (RuleCard) cd;
 			if(rl.type == numType)
 				return rl.theNumber;
 		}
		
 		switch(numType){
 			case 1://play
 				return 1;//Default: Play 1
 			case 2://draw
 				return 1; //Default: Draw 1
 			case 3://possession
 				return Integer.MAX_VALUE; //Default: No possession limit
 			case 4://hand
 				return Integer.MAX_VALUE;//Default:No hand limit
 				
 		}
 		
 		System.out.println("This statement should never be seen.");
		return -1;
	}
	

	/**
	 * This determines whether or not you can end your turn
	 * @param p The Player
	 * @return whether you can end your turn
	 */
	private static boolean canEnd(Player p) {
		int numPlaysNeeded = determineNumber(1);
		int maxPoss = determineNumber(3);
		int maxHand = determineNumber(4);
		
		if(p.numPlaysSoFar < numPlaysNeeded)
		{
			//System.out.println("You haven't played enough cards.");
			message = "You haven't played enough cards.";
			drawEverything(p,g.gameboard);
			return false;
		}
		else
		{
			if(p.hand.count() > maxHand)
			{
				//System.out.println("You have too many cards in your hand.");
				message = "You have too many cards in your hand.";
				drawEverything(p,g.gameboard);
				return false;
			}
			else
			{
				if(p.holdingPen.count()>maxPoss)
				{
					//System.out.println("You have too many cards in your holding pen.");
					message = "You have too many cards in your holding pen.";
					drawEverything(p,g.gameboard);
					return false;
				}
				else
				{
					return true;
				}
			}
		}
	}//end canEnd()


	/**
	 * This replaces the current rules with the new rule, if necessary
	 * @param newRule the new rule to replace the old
	 */
	private static void replaceNumber(RuleCard newRule) {

		if(g!= null && g.gameboard != null && g.gameboard.rules != null)
		{
			Deck dRules = g.gameboard.rules;
			if(dRules.count() != 0)
			{
				ArrayList<Card> rules = dRules.deck;
				for(int i = 0; i < dRules.count(); i++)
				{
					RuleCard rl = (RuleCard) rules.get(i);
					
					if(newRule != rl)
					{
						if(rl.type == newRule.type)
						{
							//remove the old rule from the rules
							dRules.removeCard(rl);
							g.gameboard.discard.addCard(rl);
							rl.location = g.gameboard.discard;
						}
					}
				}//end going through all the rules
				
			}
		}
		else
		{
			System.out.println("hmmm. this shouldn't have happened");
		}
	}
}
