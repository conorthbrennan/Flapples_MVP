import java.awt.Container;
import java.awt.Dimension;
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
		StandardDeck sd = new StandardDeck(g);
		//CONOR WRITE THIS CODE FOR ME!
		//STANDARD DECK, I WANT TO BE LIKE sd.getCard("Banana") and get that card
		
		//all sorts of stuff here
		
		Board exampleBoard = g.gameboard;
		//When making the board, all the decks get initialized (including the shuffled drawpile and players' hands and hps)
		
		ArrayList<Player> plrs = exampleBoard.players;
		
		//System.out.println("" + drawpile.count());
		
		//Give each player three cards.
		for(int i = 0; i < 3; i++)
			for(Player pl : plrs)
			{
				exampleBoard.drawPile.drawCard(pl.getHand(), 1);
			}
		
		//Make sure to initialize the original frame
		overallFrame = new JFrame("Flapples");
		plIndex =0;
		
		drawEverything(plrs.get(0),exampleBoard);
		//As the end turn button gets pressed, the turns commence.
		
		//for the purposes of seeing what's really in peoples' hands
		/*for(Player pl: plrs)
		{
			System.out.println(pl.name);
			for(Card cd: pl.hand.deck){
				System.out.println(cd.getTitle());
			}
		}*/

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

		JPanel playerInfoRow = setUpPlayerInfoRow(p,b);//This will hold the player's name at the top of the screen.
		JPanel goalsRow = setUpGoalsRow(b);//This will hold the goals in the second row.
		JPanel rulesRow = setUpRulesRow(b);//This will hold the rules in the third row.
		JPanel discardRow = setUpDiscardPileRow(b);//This will hold the discard pile in the fourth row.
		JPanel holdingPenRow = setUpHoldingPenRow(p);//This will be the player's holding pen in the fifth row.
		JPanel handRow = setUpHandRow(p,b);//This will hold the player's hand.
		
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
	private static JPanel setUpHandRow(Player p,Board brd) {
		JPanel handRow = new JPanel();
		//THIS SHALL BE A ROW OF BUTTONS
		
		Deck hand = p.getHand();
		if(hand!= null)
		{
			ArrayList<Card> cards = hand.deck;//Get list of cards from the deck given
			for(Card cd: cards)//for each card
			{
				JButton b = new JButton(cd.getTitle());
				b.setText(cd.getTitle() + ": " + cd.getDescription());
				b.setIcon(new ImageIcon(cd.getPicture()));
				b = addListeners(b,cd,p,brd);
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
	private static JButton addListeners(JButton b,final Card cd, final Player p, final Board brd) {
		//The ActionListener for all the buttons:
		//To be used to make sure the buttons could work:
		ActionListener alist = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("You played the " + cd.getTitle() + " card!");
				//THIS SHOULD PROLLY ACTUALLY PLAY THE CARD INSTEAD......
				cd.playCard(p, brd);
				//REDRAW EVERYTHING!
				drawEverything(p, brd);
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
	private static JPanel setUpDiscardPileRow(Board b) {
		JPanel discardRow = new JPanel();
		String str = "Discard Pile: \n";
		Deck discards = b.getDiscards();//Get the deck of all the discards from the board.
		JTextArea blob = listTitles(discards,str);
		discardRow.add(blob);//add the text to the panel
		return discardRow;
	}
	
	/**
	 * This sets up the JPanel about the Rules in play. Each card is represented by its title.
	 * @param b The Board
	 * @return The JPanel with the JTextArea of cards' titles.
	 */
	private static JPanel setUpRulesRow(Board b) {
		JPanel rulesRow = new JPanel();
		String str = "Rules: \n";
		Deck rules = b.getRules();//Get the deck of all the rules from the board.
		JTextArea blob = listTitles(rules,str);
		rulesRow.add(blob);//add the text to the panel
		return rulesRow;
	}
	
	/**
	 * This sets up the JPanel about the Goals in play. Each card is represented by its title.
	 * @param b The Board
	 * @return The JPanel with JTextArea which has the titles of all its cards
	 */
	private static JPanel setUpGoalsRow(Board b) {
		JPanel goalsRow = new JPanel();
		String str= "Goals: \n";
		Deck goals = b.getGoals();//Get the deck of all the goals from the board.
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
	private static JPanel setUpPlayerInfoRow(Player p, Board brd) {
		JPanel plInfo = new JPanel();
	
		JTextField blob = new JTextField();
		String str= "Player: " + p.getName();
		//I might add more player info later!
		blob.setText(str);
		blob.setPreferredSize(new Dimension(str.length() * 10,str.length() * 2));
		
		JButton endTurn = endTurnButton(p,brd);
		
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
	private static JButton endTurnButton(final Player currentPlayer, final Board brd) {
		JButton endTurn = new JButton("End Your Turn.");

		ActionListener alist = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("You ended your turn.");
				//Redraw everything:
				Player nextPlayer = getNextPlayer(currentPlayer);
				handleTurnChange(nextPlayer,brd);
				drawEverything(nextPlayer,brd);
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
	
	private static void handleTurnChange(Player nextPlayer, Board brd) {
		//This should give the player the number of cards as specified by the "Draw X" card in play.
		//If there isn't one in play, it defaults to Draw 1.
		
		
	}

}