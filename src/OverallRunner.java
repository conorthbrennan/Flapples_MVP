import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;


public class OverallRunner
{
	private static JFrame overallFrame;//The JFrame that the whole GUI is based off of
	private static int plIndex;//the index in the list of players of the current player
	private static Game g;//the game this is based off of
	private static boolean discarding;//whether or not the current player is discarding
	private static String message;//what message will show up on the pop-ups and the game feed
	private static ArrayList<String> feed = new ArrayList<String>();//The list of strings the game feed has
	private static Color goalColor = Color.orange;//the color of the goal cards
	private static Color ruleColor = Color.blue;//the color of the rule cards
	private static Color possColor = Color.green;//the color of the possession cards
	private static int waitingTime = 1000;//the waiting time between players

	public static void main(String [] args)
	{
		g = new Game(overallFrame);//Make a new game
		setWaitingTime();//asks the players how long they wish to wait for hotseating
		Board exampleBoard = g.gameboard;//make the board
		//When making the board, all the decks get initialized (including the shuffled drawpile and players' hands and hps)

		ArrayList<Player> plrs = exampleBoard.players;

		//Give each player three cards.
		for(int i = 0; i < 3; i++)//three times
			for(Player pl : plrs)//for every player
			{
				exampleBoard.drawPile.drawCard(pl.getHand(), 1);//move a card from the drawpile to the player's hand
			}

		//Make sure to initialize the original frame
		overallFrame = new JFrame("Flapples");
		plIndex =0;//the number of the person who is currently going is set to 0

		//Make sure the first player draws the right number of cards:
		handleTurnChange(plrs.get(0));
		drawEverything(plrs.get(0),exampleBoard);//draws the initial screen
		//As the end turn button gets pressed, the turns commence.

	}

/*	*//**
	 * Will this game allowing cheating?
	 * @return whether or not you entered the secret code
	 *//*
	private static boolean willCheat() {

		Object[] possibilities = null;//{"ham", "spam", "yam"};
		String s = (String)JOptionPane.showInputDialog(
				overallFrame,
				"Cheating." ,
				"Can you cheat?",
				JOptionPane.QUESTION_MESSAGE,
				null,
				possibilities,
				"Cheatcodes");
		if(s.equals("w"))
			return true;
		else
			return false;
	}*/
	
	/**
	 * How long do you want to wait between turns?
	 * @return how long to wait
	 */
	public static void setWaitingTime(){
		boolean numYet = false;
		int num = -1;
		while(!numYet)//while you haven't gotten proper input yet
		{
			String s = (String)JOptionPane.showInputDialog(
					overallFrame,
					"How long do you wish to wait between different people's turns (in seconds)?" ,
					"Hot seating",
					JOptionPane.QUESTION_MESSAGE,
					null,
					null,
					"1");//ask the user for hotseating
			if(s == null)//if you press x or cancel, then the whole thing will stop
				System.exit(0);
			
			try{
				num = Integer.parseInt(s);
				numYet = true;//stop going through the while loop
				waitingTime = num* 1000;//set the waiting time to the input
			}
			catch(NumberFormatException e)
			{
				//the user did not enter a number when he/she should have
				JOptionPane.showMessageDialog(overallFrame,
					    "You need to enter in a number.",
					    "Wrong input!",
					    JOptionPane.PLAIN_MESSAGE);
			}
		}

	}


	/**
	 * This does the graphical interface for a particular situation.
	 * @param p the Player who the screen is being drawn for
	 * @param b the Board at that time
	 */
	private static void drawEverything(Player p, Board b) {

		//set the look and feel
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e)
		{
			System.out.println("I laugh at your misery!");
		}

		Container uberpane = overallFrame.getContentPane();
		uberpane.removeAll();

		if(p == null)
		{//if the player is null, then
			//let's draw a black screen.
			JPanel jp = new JPanel();
			jp.setBackground(Color.black);
			uberpane.add(jp);
		}
		else
		{
			//the player is not null
			if(!g.evaluateGoalMatching())
			{//Has the game been won yet?
				if(!isAI(p))
				{//The player isn't an ai
					//Set the layout to be a boxlayout that goes from top to bottom		
					BoxLayout box = new BoxLayout(uberpane,BoxLayout.PAGE_AXIS);//top to bottom	
					uberpane.setLayout(box);
					
					JPanel playerInfoRow = setUpPlayerInfoRow(p);//This will hold the player's name at the top of the screen.
					//The Scroll Panes are for displays that involve many cards that would have to be scrolled through.
					JScrollPane goalsRow = setUpGoalsRow();//This will hold the goals in the second row.
					JScrollPane rulesRow = setUpRulesRow();//This will hold the rules in the third row.
					JScrollPane discardRow = setUpDiscardPileRow();//This will hold the discard pile in the fourth row.
					JScrollPane holdingPenRow = setUpHoldingPenRow(p);//This will be the player's holding pen in the fifth row.
					JPanel handRow = setUpHandRow(p);//This will hold the player's hand.
					JPanel otherHPsRow = setUpOtherHPsRow(p);//This will show the other players' holding pens.
					JPanel gameFeed = setUpGameFeed();//This will show output from game events.
					
					//Make the tabbed pane for the goals, rules, discard pile, game feed, and other players' holding pens.
					JTabbedPane tabbedPane = new JTabbedPane();
					
					//add holdingPenRow to the tabbed pane
					tabbedPane.addTab("Your Holding Pen", null, holdingPenRow,
							"Look at your holding pen.");
					
					String s= "";
					String s2 = "";
					if(g != null)// && g.gameboard != null && g.gameboard.goals != null && g.gameboard.goals.deck != null && g.gameboard.goals.deck.get(0) != null)
						if(g.gameboard != null)
							if(g.gameboard.goals != null)
								if(g.gameboard.goals.deck.isEmpty() == false)
								{
									//if there is a goal in the deck,
									//then put the title of it in s 
									//and the description of it in s2
									s = ": " + g.gameboard.goals.deck.get(0).getTitle();
									s2 = g.gameboard.goals.deck.get(0).getDescription();
								}
					
					//The Goal tab will have the title including the title of the current goal.
					//If you hover over it, the text will say the description of the goal.
					tabbedPane.addTab("The Goal" + s, null, goalsRow,
							s2);

					//add the rest of these rows to the tabs in the tabbed pane
					tabbedPane.addTab("The Rules", null, rulesRow,
							"Look at the current rules.");
					
					tabbedPane.addTab("The Others' Holding Pens", null, otherHPsRow,
							"Look at other people's holding pens.");

					tabbedPane.addTab("The Discard Pile", null, discardRow,
							"Look at the current discard pile.");
					
					tabbedPane.addTab("GAME FEED", null, gameFeed,
							"See what's just happened in the game.");

					//Add all the items to the  overall pane
					uberpane.add(playerInfoRow);
					uberpane.add(tabbedPane);
					uberpane.add(handRow);

					if(message != null)//if the message isn't nothing, display it
						JOptionPane.showMessageDialog(overallFrame, message);

					overallFrame.pack();
					overallFrame.setVisible(true);
					overallFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
				else
				{//This player is an ai
					//Show a display where it says the ai's information and a textbox saying it's the ai.
					JPanel playerInfoRow = setUpPlayerInfoRow(p);//This will hold the player's name at the top of the screen.
					JTextArea blob = new JTextArea("THIS IS THE AI.");
					uberpane.add(playerInfoRow);
					uberpane.add(blob);

					if(message != null)//if the message isn't nothing, display it
						JOptionPane.showMessageDialog(overallFrame, message);

					overallFrame.pack();
					overallFrame.setVisible(true);
					overallFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}

			}
			else
			{//the game has been won
				//show that information
				message = "The game has been won by " + p.getName() + "!!!!";
				feed.add(message);
				JOptionPane.showMessageDialog(overallFrame, message);
				System.exit(0);
			}
		}//end else on whether player is null		

	}
	
	/**This sets up the JPanel dealing with the game feed.
	 * It consists of a textArea.
	 * @return a panel containing the feed
	 */
	private static JPanel setUpGameFeed() {
		JPanel gFeed = new JPanel();
		JTextArea textArea = new JTextArea(6, 40);
		JScrollPane scrollPane = new JScrollPane(textArea);//makes a scroll pane out of the text area 
		textArea.setEditable(false);
		for (int i=0;i<feed.size();i++)
			textArea.append("\n"+feed.get(i));//put the message info in the text area
		gFeed.add(scrollPane);
		return gFeed;
	}
	
	/**This sets up the JPanel dealing with the other player's holding pens. 
	 * There will be a list of buttons with each button having each other player's name on it.
	 * If you click that button, then their holding pen will pop up in a different window.
	 * @param p the Player
	 * 
	 * @return the JPanel of other peoples' holding pens.
	 */
	private static JPanel setUpOtherHPsRow(Player p) {
		JPanel HPsRow = new JPanel();
		//Set the layout of the otherHPsRow to be a grid with 1 row with x cards
		GridLayout grid = new GridLayout(1,g.numPlrs);
		HPsRow.setLayout(grid);

		//Make a tabbed pane where each tab is a different player
		JTabbedPane tabbedPane = new JTabbedPane();
		ArrayList<Player> plrs = g.gameboard.players;
		for(Player plr: plrs)//for each player
		{
			if(!plr.equals(p))//if you aren't the current player,
			{
				JScrollPane plrshp = new JScrollPane();
				Deck hp = plr.getHoldingPen();//Get the holding pen of the other player
				plrshp = listButtons(hp,null);//make the scroll pane the result of listButtons
				//add the tab where the title includes the player's name and number of cards in hand
				//the hover text will include the names of every card in that holding pen
				tabbedPane.addTab(plr.getName() + " - " + hp.count(), null, plrshp, listTitlesString(hp,""," "));
			}
		}	

		HPsRow.add(tabbedPane);
		return HPsRow;
	}

	//This is NO longer necessary
	/**
	 * This adds an action listener to the JButton that will pop up another window
	 * That window will have a list of the cards of the that person's holding pen.
	 * @param b
	 * @return the JButton that will pop up with the holding pen info.
	 *//*
	private static JButton addPopUpHPsListeners(JButton b, final Player owner) {
		ActionListener alist = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//title of the popup box = player's name with the num of cards in parens
				//words = all the cards' titles

				String str = "";
				str = listTitlesString(owner.holdingPen,str,"\n");
				if(str.equals(""))
					str = "nothing";

				JOptionPane.showMessageDialog(overallFrame,
						str,
						owner.name + "'s Holding Pen" + " (" + owner.holdingPen.count() + ")",
						JOptionPane.PLAIN_MESSAGE);

			}
		};

		b.addActionListener(alist);
		return b;
	}*/

	
	/**
	 * Of a particular deck, list all the cards' titles in a string
	 * @param d the Deck
	 * @param str the String you want all the cards' titles added to into
	 * @param spacing whether you want a space or a new line in between titles
	 * @return the String of all the cards' titles (plus what was originally in the string)
	 */
	private static String listTitlesString(Deck d, String str, String spacing){
		if(d != null && d.deck != null)
		{//if there are cards in the deck
			ArrayList<Card> cards = d.deck;//Get list of cards from the deck given
			for(Card cd: cards)//for each card
			{
				str += cd.getTitle() + spacing;//add its title to the string str
			}
		}
		else
			str+= "<null>";


		return str;
	}

	/**
	 * This sets up the JPanel dealing with the Player's hand. Each card is a JButton that can be played.
	 * @param p The Player
	 * @return The JPanel of JButtons describing the PLayer's hand.
	 */
	private static JPanel setUpHandRow(Player p) {
		JPanel handRow = new JPanel();
		
		//The hand row's layout is a x-by-4 grid.
		GridLayout grid = new GridLayout(0,4);//any number of rows with 4 cards
		handRow.setLayout(grid);

		Deck hand = p.getHand();
		if(hand!= null)
		{
			ArrayList<Card> cards = hand.deck;//Get list of cards from the deck given
			for(Card cd: cards)//for each card
			{
				//Get the button associated with this card. The color will be determined based on the type of card it is.
				//Let's leave the text as black, so you can read it in the hand. Elsewhere, it will have the background's color.
				JButton b = genericButton(cd,Color.black, colorCard(cd));
				b = addCardListeners(b,cd,p);//add the special hand action listeners to the buttons
				handRow.add(b);
			}
		}
		else{
			//if the hand has no cards
			JTextArea blob = new JTextArea();
			blob.setText("The hand is null");
			handRow.add(blob);
		}

		return handRow;
	}

	/**
	 * Returns the color of the card according to its type
	 * @param cd the Card
	 * @return the color of that card
	 */
	public static Color colorCard(Card cd){
		if(cd.getClass().equals(new RuleCard().getClass()))
			return ruleColor;//rules are blue
		else if(cd.getClass().equals(new Possession().getClass()))
			return possColor;//possessions are green
		else if(cd.getClass().equals(new Goal().getClass()))
			return goalColor;//Goals are orange
		else
			return Color.black;//There should not be any other card type.
	}



	/**
	 * This adds the proper action listener to each JButton representing a card in the handRow.
	 * @param b The JButton that represents this card.
	 * @param cd The actual card
	 * @param p The player playing the card
	 * @param brd The board at that moment
	 * @return Returns the JButton that represents the card.
	 */
	private static JButton addCardListeners(JButton b,final Card cd, final Player p) {
		//The ActionListener for all the JButtons representing cards in the hand:
		//To be used to make sure the buttons could work:
		ActionListener alist = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				cardChosen(cd,p);//this card has been chosen
			}
		};

		b.addActionListener(alist);
		//The buttons of this card in your hand will have hover text of its description.
		b.setToolTipText(cd.getDescription());
		return b;
	}

	/**
	 * Determining what to do with a card once it has been chosen.
	 * It could be played or discarded.
	 * @param cd the Card
	 * @param p the Player
	 */
	private static void cardChosen(Card cd, Player p) {
		if(!discarding)
		{//if the player isn't discarding
			if(canPlay(p))
			{//if it isn't against the rules to play this card
				if(!isAI(p) && verifyCard(cd, p))
				{//if you aren't an ai and the user has verified that he/she wants to play this card
								
					cd.playCard(p, g.gameboard);//play the card

					p.numPlaysSoFar +=1;//increment the player's counter for number of plays
					//add this event to the feed:
					feed.add(p.name+" played '"+ cd.title +"'. (play #"+p.numPlaysSoFar+")");
					
					//if this is a rule, then change the rules.
					if(cd.getClass().equals((new RuleCard()).getClass()))
					{
						replaceNumber((RuleCard) cd);
					}

					//REDRAW EVERYTHING!
					drawEverything(p, g.gameboard);

					if(canEnd(p,false))//whether or not the player's turn can end (no redrawing so no message)
						innardsEnd(p);//if able to, end the player's turn
				}
				else if(isAI(p))
				{//if the player is an ai
					if(cd != null)
					{
						cd.playCard(p, g.gameboard);//play the card

						p.numPlaysSoFar +=1;//increment its number of plays so far

						//if this is a rule, then change the rules.
						if(cd.getClass().equals((new RuleCard()).getClass()))
						{
							replaceNumber((RuleCard) cd);
						}

						//REDRAW EVERYTHING!
						drawEverything(p, g.gameboard);
					}
					else
						System.out.println("LOL. This should never occur.");
					

				}
			}
			else
			{//you can't play this card
				message = "You can't play that card, because you have surpassed the number of plays per turn allowed. Press 'End Turn'.";
				feed.add("@"+p.name+": "+ message);
				drawEverything(p,g.gameboard);
				innardsEnd(p);//ends the turn
			}
		}
		else//you are discarding
		{//this only ever occurs with a player, not ais (it is handled in doAIStuff)
			if (wantDiscard(cd))
			{//does the user truly want to discard this card?
				cd.location.removeCard(cd);//remove the card from its original location					
				g.gameboard.addCard(cd, g.gameboard.discard);//add the card to the discard pile
				cd.location = g.gameboard.discard;//set the card's location to the discard pile
				message = "You discarded " + cd.getTitle() + "! Click 'Discard' again to discard a different card.";
				feed.add(p.name +" discarded "+ cd.getTitle());
				discarding = false;
				drawEverything(p,g.gameboard);

				if(canEnd(p,false))//check if the turn should end (no redrawing so no message)
					innardsEnd(p);//end the turn
			}
			else//if the user didn't want to discard something then don't
				discarding = false;
			
		}

	}

	/**
	 * Checks to see whether or not you actually want to play that card
	 * @param cd the card you're about to play
	 * @return whether you want to play it
	 */
	private static boolean verifyCard(Card cd, Player p) {
		if (!p.AlertsEnabled[0])	// Alerts - 0
			return true;
		else {
			String s = "You are about to play your card: '" + cd.getTitle() + "'.";
			if(!cd.getTitle().equals(cd.getDescription()))
				s += "\n Description: " + cd.getDescription();
			
			s += "\n Click 'yes' to confirm.";
			//have a checkbox where you can check it if you don't want these "are you sure?" messages
			JCheckBox msgCancel = new JCheckBox("Do not show again");
			Object[] cnt = {s, msgCancel};
			//ask if they are sure they want to play this card
			int n = JOptionPane.showConfirmDialog(overallFrame,
					cnt,
					"are you sure?",
					JOptionPane.YES_NO_OPTION);
			if(msgCancel.isSelected())
				p.AlertsEnabled[0] = false;//set the alert 0 enabled to false if you checked the no-show box 
			if(n == JOptionPane.YES_OPTION)
				return true;
			else
				return false;
		}
	}

	/**
	 * This checks to see whether or not you actually want to discard that card
	 * @param cd The card you're about to discard
	 * @return whether you want to discard it
	 */
	private static boolean wantDiscard(Card cd){
		int n = JOptionPane.showConfirmDialog(overallFrame,
				"Do you really want to discard " + cd.getTitle() + "?",
				"are you sure?",
				JOptionPane.YES_NO_OPTION);
		if(n == JOptionPane.YES_OPTION)
			return true;
		else
			return false;
	}

	/**
	 * This sets up the JPanel about the Player's Holding Pen. Each card is represented by its title.
	 * @param p The Player
	 * @return the JPanel with the JTextArea of the cards' titles.
	 */
	private static JScrollPane setUpHoldingPenRow(Player p) {
		JScrollPane hpRow = new JScrollPane();
		Deck hp = p.getHoldingPen();//Get the deck of the holding pen from the player.

		if(!discarding)
		{
			hpRow = listButtons(hp,null);
			//the holding pen row is the list of buttons (colored by type) of the holding pen
		}
		else
		{

			if(hp!= null)
			{
				JPanel pane = new JPanel();
				pane.setLayout(new BoxLayout(pane, BoxLayout.LINE_AXIS));
				for(Card cd : hp.deck){
					//for every card in the holding pen
					//make a button which has red text and respective color background
					JButton cdButt = genericButton(cd,Color.red,colorCard(cd));
					cdButt = addCardListeners(cdButt,cd,p);
					pane.add(cdButt);
				}
				hpRow = new JScrollPane(pane);
				hpRow.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

			}
		}

		return hpRow;
	}

	/**
	 * This makes the generic JButton (no listener) from the card and the two colors.
	 * @param cd the Card
	 * @param fore the text color
	 * @param back the background color
	 * @return the simple JButton with no listener
	 */
	private static JButton genericButton(Card cd, Color fore, Color back){
		JButton cdButt = new JButton(cd.getTitle());
		//the button's text is its title
		//if the background color was null, then choose the respective color
		if(back == null)
			back = colorCard(cd);
		//if the text color was null, make it the background color but darker
		if(fore == null)
			fore = back.darker();
		cdButt.setForeground(fore);
		cdButt.setBackground(back);
		cdButt.setOpaque(true);
		//add a 75 by 75 image of the card onto the button
		Image newimg = cd.getPicture().getScaledInstance(75, 75,  java.awt.Image.SCALE_SMOOTH ) ; 
		cdButt.setIcon(new ImageIcon(newimg));
		//Move the text to the bottom center
		cdButt.setHorizontalTextPosition(JButton.CENTER);
		cdButt.setVerticalTextPosition(JButton.BOTTOM);
		//the card's tool tip is its description
		cdButt.setToolTipText(cd.getDescription());
		return cdButt;
	}

	/**
	 * This sets up the JPanel about the Discard Pile. Each card is represented by its title.
	 * @param b The Board
	 * @return The JPanel with the JTextArea of cards' titles
	 */
	private static JScrollPane setUpDiscardPileRow() {
		JScrollPane discardRow = new JScrollPane();
		Deck discards = g.gameboard.getDiscards();//Get the deck of all the discards from the board.
		discardRow = listButtons(discards,null);
		return discardRow;
	}

	/**
	 * This sets up the JPanel about the Rules in play. Each card is represented by its title.
	 * @param b The Board
	 * @return The JPanel with the JTextArea of cards' titles.
	 */
	private static JScrollPane setUpRulesRow() {
		JScrollPane rulesRow = new JScrollPane();
		//Get the deck of all the rules from the board.
		rulesRow = listButtons(g.gameboard.getRules(),null);
		return rulesRow;
	}

	/**
	 * This sets up the JPanel about the Goals in play. 
	 * Each goal is represented by its title on a JButton.
	 * The JButton will open up a popup window which tells you its description.
	 * @param b The Board
	 * @return The JPanel with JTextArea which has the titles of all its cards
	 */
	private static JScrollPane setUpGoalsRow() {
		JScrollPane goalsRow = new JScrollPane();
		Deck goals = g.gameboard.getGoals();//Get the deck of all the goals from the board.
		goalsRow = listButtons(goals,null);
		return goalsRow;
	}

	/**
	 * This adds the listener that will bring up a pop up containing the description of the card.
	 * @param descrip the JButton for the Goal you want to have described.
	 * @param cd the card associated
	 * @return the JButton with listener
	 */
	private static JButton addDescriptionListener(JButton descrip,final Card cd){
		ActionListener alist = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//Makes a popup with the title of the popup as the card's title
				//and the description as the main body of text
				JOptionPane.showMessageDialog(overallFrame,
						cd.getDescription(),
						cd.getTitle(),
						JOptionPane.PLAIN_MESSAGE);

			}
		};

		descrip.addActionListener(alist);
		return descrip;
	}

	/**
	 * This sets up the JPanel that has the current Player's name and the JButton to end your turn.
	 * @param p The Player
	 * @param brd The Board
	 * @return The JPanel which has the JTextField of the Player's name and the end turn JButton
	 */
	private static JPanel setUpPlayerInfoRow(Player p) {
		JPanel plInfo = new JPanel();

		//List the player's name at the top left
		JTextField name = new JTextField();
		String str= "Player: " + p.getName();
		name.setText(str);
		name.setEditable(false);
		
		//Make JButtons that will only show up if the player isn't ai 
		//the endturn button is now unneccessary since turns now go automatically
		//but in the case that something goes wrong, you can still use it
		JButton endTurn = new JButton("Nope");
		JButton discard = new JButton("Nope");
		JButton help = new JButton("nope");
		if(!isAI(p))
		{//if the player is human, fill in the buttons with the proper listeners and the like
			discard = discardButton(p);
			endTurn = endTurnButton(p);
			help = helpButton();
		}

		//Make a box on the right most have the current message
		JTextArea messages = new JTextArea();
		messages.setText(message);
		
		//first add the player's name
		plInfo.add(name);
		if(!isAI(p))
		{//if you aren't an ai, add an end turn button, discard button, and help button
			plInfo.add(endTurn);
			plInfo.add(discard);
			plInfo.add(help);
		}
		//lastly, add the messages box
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
				//player will discard the next thing it touches
				discarding = true;
				message = "You will be discarding the next card you click! Beware!";
				feed.add("@"+p.name+": "+ "discard mode activated!");
				drawEverything(p, g.gameboard);
			}
		};
		discard.addActionListener(alist);

		return discard;
	}

	/**
	 * Make the JButton that ends your turn. 
	 * Somewhat defunct now that turns automatically end when they can't go on.
	 * @param nextPlayer The next person to play.
	 * @param brd The board as it is.
	 * @return The JButton, which will end your turn if you click it.
	 */
	private static JButton endTurnButton(final Player currentPlayer) {
		JButton endTurn = new JButton("End Your Turn.");

		ActionListener alist = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {

				innardsEnd(currentPlayer);//end the turn
			}
		};

		endTurn.addActionListener(alist);

		return endTurn;
	}
	
	/**
	 * This button will show the help box if you click it.
	 * @return the help button
	 */
	private static JButton helpButton() {
		JButton help = new JButton("help");
		final BufferedImage helpImg;
		try {
			helpImg = ImageIO.read(new File("tut_01.png"));
			
			//if you click the button, the help image will pop up.
			ActionListener alist = new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(overallFrame,
							null, 
							null,
							JOptionPane.PLAIN_MESSAGE, 
							new ImageIcon(helpImg));
				}
			};

			help.addActionListener(alist);
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return help;
	}

	/**
	 * This ends the turn.
	 * This method is called from clicking the end turn button and cardChosen (if the turn can end, it will).
	 * @param currentPlayer the current player
	 */
	private static void innardsEnd(Player currentPlayer) {

		if(canEnd(currentPlayer,true))
		{//This determines whether or not the turn can end, and it will pop up with new messages
			
			Player nextPlayer = getNextPlayer(currentPlayer);
			if(!isAI(nextPlayer) && !isAI(currentPlayer))
			{//if neither the previous and the next player are ai,
				//then have hotseating
				message = currentPlayer.getName() + " should walk away so that " + nextPlayer.getName() + " can come on.";
				feed.add(message);
				drawEverything(currentPlayer,g.gameboard);
				hotSeatingTime();
			}

			message = "Now it is " + nextPlayer.name + "'s turn.";
			feed.add(message);
			handleTurnChange(nextPlayer);//handles drawing cards
			if(!isAI(nextPlayer))
				drawEverything(nextPlayer,g.gameboard);
			else
			{
				ArtificialIntelligence AI = (ArtificialIntelligence) nextPlayer;
				doAIStuff(AI);
			}

		}	
	}//end innardsEnd

	/**
	 * Wait some time (determined earlier by the user) until the next person should arrive.
	 */
	private static void hotSeatingTime() {
		try {
			Thread.sleep(waitingTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	/**
	 * This returns whether or not a player is an AI
	 * @param p the Player
	 * @return whether the player is an ai
	 */
	private static boolean isAI(Player p){
		return p.getName().contains("AI");
	}

	/**
	 * Make sure that the AI does the things it needs to.
	 * @param AI the AI player
	 */
	private static void doAIStuff(ArtificialIntelligence AI) {

		for(int i = 0; i < determineNumber(1); i++)
		{//while you need to keep playing cards, choose them
			if(AI.hand.count() > 0){
				//(choose them) as long as you actually have cards to play
				Card cd = AI.PickCardSwitch();
				discarding = false;
				cardChosen(cd,AI);//Play the card the ai picked
				message = AI.getName() + " played " + cd.getTitle();
				feed.add(message);
				drawEverything(AI,g.gameboard);
			}
		}

		//ensuring possession limit
		AI.discardHoldingPen(determineNumber(3));
		//ensuring hand limit
		AI.discardHand(determineNumber(4));

		innardsEnd(AI);//end turn
	}

	/**
	 * Takes the current Player and returns the next Player
	 * @param currentPlayer the current Player
	 * @return the next Player
	 */
	private static Player getNextPlayer(Player currentPlayer) {
		ArrayList<Player> plrs = g.gameboard.players;

		int nextPlIndex = (plIndex + 1) % plrs.size();
		//plIndex = nextPlIndex;

		return plrs.get(nextPlIndex);
	}

//This method is no longer necessary:
	/**
	 * Gets all the titles from a Deck and makes a JScrollPane out of it. 
	 * @param d the Deck you want the titles of
	 * @param str the starting string for the text area
	 * @return the scroll pane with all the titles
	 */
	private static JScrollPane listTitles(Deck d, String str){
		JTextArea blob = new JTextArea();
		str = listTitlesString(d,str,"\n");
		blob.setEditable(false);
		blob.setText(str);//set the text of the textArea to str
		JScrollPane scrollPane = new JScrollPane(blob,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setPreferredSize(new Dimension(200,60));
		return scrollPane;
	}

	/**
	 * Gets all the titles from a Deck and makes a JScrollPane out of the JButtons
	 * @param d the Deck you want the titles of
	 * @param color the color of the button's background (null if you want it according to card type)
	 * @return the JScrollPane with all the buttons
	 */
	private static JScrollPane listButtons(Deck d, Color color){
		JPanel pane = new JPanel();
		pane.setLayout(new BoxLayout(pane, BoxLayout.LINE_AXIS));
		for(Card cd : d.deck){
			JButton cdButt = genericButton(cd,null,color);
			cdButt = addDescriptionListener(cdButt,cd);//This will only let you look at the cards, not play/discard them.
			pane.add(cdButt);
		}
		JScrollPane scroll = new JScrollPane(pane);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		return scroll;
	}


	/**
	 * This determines what needs to be done before the next Player is allowed to play any cards.
	 * This includes drawing cards before a turn and reseting numPlaysSoFar.
	 * @param nextPlayer
	 */
	private static void handleTurnChange(Player nextPlayer) {

		nextPlayer.numPlaysSoFar = 0;

		//Drawing cards
		//This should give the player the number of cards as specified by the "Draw X" card in play.
		//If there isn't one in play, it defaults to Draw 1.
		int drawAmt = determineNumber(2);
		checkDrawPile(drawAmt);
		g.gameboard.drawPile.drawCard(nextPlayer.hand, drawAmt);
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
			//There aren't enough cards in the draw pile and the discard pile combined to draw enough cards.
			System.out.println("There aren't enough cards to draw from the combined discard and draw pile.");
			feed.add("There aren't enough cards to draw from the combined discard and draw pile.");
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
		{//for each rule:
			RuleCard rl = (RuleCard) cd;
			//if the rule's type is the same as the one in question, then return the associated limit/number
			if(rl.type == numType)
				return rl.theNumber;
		}

		//The Defaults if there are no rules pertaining to that type of rule yet.
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
	 * @param loud Whether or not you want drawEverything to be called
	 * @return whether you can end your turn
	 */
	private static boolean canEnd(Player p, boolean loud) {
		int numPlaysNeeded = determineNumber(1);
		int maxPoss = determineNumber(3);
		int maxHand = determineNumber(4);

		if(p.numPlaysSoFar < numPlaysNeeded)
		{//if you haven't played enough cards
			if(p.hand.count() != 0)
			{//if you have a hand
				message = "You haven't played enough cards.";
				feed.add("@"+p.name+": "+message);
				if(loud)
					drawEverything(p,g.gameboard);
				return false;
			}
			else
			{
				//you have no hand
				//you can't play anything
				message = "End turn, because you have no hand.";
				feed.add("@"+p.name+": "+message);
				if(loud)
					drawEverything(p,g.gameboard);
				return true;
			}

		}
		else
		{//you have played enough cards
			if(p.hand.count() > maxHand)
			{//if you have too many cards in hand
				message = "You have too many cards in your hand.";
				feed.add("@"+p.name+": "+message);
				if(loud)
					drawEverything(p,g.gameboard);
				return false;
			}
			else
			{//the number of cards you have in your hand is under the limit
				if(p.holdingPen.count()>maxPoss)
				{//if you have too many cards in your holding pen
					message = "You have too many cards in your holding pen.";
					feed.add("@"+p.name+": "+message);
					if(loud)
						drawEverything(p,g.gameboard);
					return false;
				}
				else
				{//you have nothing stopping you from ending this turn!
					return true;
				}
			}
		}
	}//end canEnd()


	/**
	 * This resolves old and new rule conflicts. (If there are any conflicts)
	 * This method is only ever called in cardChosen after the new rule has been played.
	 * @param newRule the new rule to replace the old
	 */
	private static void replaceNumber(RuleCard newRule) {

		if(g!= null && g.gameboard != null && g.gameboard.rules != null)
		{
			Deck dRules = g.gameboard.rules;
			if(dRules.count() != 0)
			{//if there are rules,
				ArrayList<Card> rules = dRules.deck;
				for(int i = 0; i < dRules.count(); i++)
				{
					//Look through every rule card
					RuleCard rl = (RuleCard) rules.get(i);

					if(newRule != rl)
					{
						//see if the distinct cards are of the same type:
						if(rl.type == newRule.type)
						{
							//remove the old rule from the rules
							dRules.removeCard(rl);
							g.gameboard.discard.addCard(rl);
							rl.location = g.gameboard.discard;
							//Because this method is only called after the rule has been played, 
							//we do not need to put in the new rule
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
