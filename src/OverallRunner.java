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
		//The ActionListener for all the buttons:
		//To be used to make sure the buttons could work:
		ActionListener alist = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(canPlay(p))
				{
					System.out.println("You played the " + cd.getTitle() + " card!");
					cd.playCard(p, g.gameboard);
					p.numPlaysSoFar +=1;
					//REDRAW EVERYTHING!
					drawEverything(p, g.gameboard);
				}
				else
				{
					System.out.println("You can't play that card, because you have surpassed the number of plays per turn allowed. Press 'End Turn'.");
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
		JTextArea blob = listTitles(hp,str);
		hpRow.add(blob);//add the text to the panel
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
		
		plInfo.add(blob);
		plInfo.add(endTurn);
		
		return plInfo;
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
					System.out.println("You ended your turn.");
					//Redraw everything:
					Player nextPlayer = getNextPlayer(currentPlayer);
					handleTurnChange(nextPlayer);
					drawEverything(nextPlayer,g.gameboard);
				}
				else
				{
					System.out.println("You haven't played enough cards to end your turn.");
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
		Deck dRules = g.gameboard.rules;
		
		/*
		 * If the deck of rules contains the "Play 2" card, which had id 5,
		 * then play 2 cards.
		 */
		if(dRules.search(5) != null)
		{
			if(p.numPlaysSoFar < 2)
			{
				//you can play another card, if you've only played one or zero cards
				return true;
			}
			else
			{
				//you can't play another card
				return false;
			}
		}
		else
		{
			//default to "Play 1"
			if(p.numPlaysSoFar ==0)
				return true;
			else
				return false;
		}

	}

	/**
	 * This determines whether or not you can end your turn
	 * @param p The Player
	 * @return whether you can end your turn
	 */
	private static boolean canEnd(Player p) {
		Deck dRules = g.gameboard.rules;
		
		//NOT DONE PROPER YET!!!!!DFGJSDKLRFGJSDLKJFSLDKJFKLSDFJLKSDJFLKSDJFLKDSFKLJ
		
		/*
		 * If the deck of rules contains the "Play 2" card, which had id 5,
		 * then you must have played 2 cards.
		 */
		if(dRules.search(5) != null)
		{
			if(p.numPlaysSoFar < 2)
			{
				//you need to play more cards
				return false;
			}
			else
			{
				//you can end your turn
				return true;
			}
		}
		else
		{
			//default to "Play 1"
			if(p.numPlaysSoFar ==0)
				return false;
			else
				return true;
		}

	}
}