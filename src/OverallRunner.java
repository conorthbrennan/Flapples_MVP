import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;


public class OverallRunner
{
	private static JFrame overallFrame;
	private static int plIndex;//the index in the list of players of the current player
	private static Game g;
	private static boolean discarding;
	private static String message;
	private static Color goalColor = Color.orange;
	private static Color ruleColor = Color.blue;
	private static Color possColor = Color.green;
	private static int waitingTime = 1000;
	
	public static void main(String [] args)
	{
		g = new Game(overallFrame);
		g.cheatable = willCheat();
		
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
	 * Will this game allowing cheating?
	 * @return whether or not you entered the secret code
	 */
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
	}

	
	/**
	 * This does the graphical interface.
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
		{
			//let's draw a black screen.
			JPanel jp = new JPanel();
			jp.setBackground(Color.black);
			uberpane.add(jp);
		}
		else
		{
			if(!g.evaluateGoalMatching())
			{
				if(!p.getName().contains("AI"))
				{
					//System.out.println("NO COMPY HERE");
					//FlowLayout flow = new FlowLayout(FlowLayout.RIGHT,200,20);//alignment,hgap,vgap		
					BoxLayout box = new BoxLayout(uberpane,BoxLayout.PAGE_AXIS);//top to bottom	
					uberpane.setLayout(box);

					JPanel playerInfoRow = setUpPlayerInfoRow(p);//This will hold the player's name at the top of the screen.
					JPanel goalsRow = setUpGoalsRow();//This will hold the goals in the second row.
					JPanel rulesRow = setUpRulesRow();//This will hold the rules in the third row.
					JPanel discardRow = setUpDiscardPileRow();//This will hold the discard pile in the fourth row.
					JPanel holdingPenRow = setUpHoldingPenRow(p);//This will be the player's holding pen in the fifth row.
					JPanel handRow = setUpHandRow(p);//This will hold the player's hand.
					JPanel otherHPsRow = setUpOtherHPsRow(p);//This will show the other players' holding pens.
					
					JTabbedPane tabbedPane = new JTabbedPane();
					
					tabbedPane.addTab("Your Holding Pen", null, holdingPenRow,
			                  "Look at your holding pen.");
					
					tabbedPane.addTab("The Rules", null, rulesRow,
			                  "Look at the current rules.");
					
					String s= "";
					String s2 = "";
					if(g != null)// && g.gameboard != null && g.gameboard.goals != null && g.gameboard.goals.deck != null && g.gameboard.goals.deck.get(0) != null)
						if(g.gameboard != null)
							if(g.gameboard.goals != null)
									if(g.gameboard.goals.deck.isEmpty() == false)
									{
										s = ": " + g.gameboard.goals.deck.get(0).getTitle();
										s2 = g.gameboard.goals.deck.get(0).getDescription();
									}
						
					tabbedPane.addTab("The Goal" + s, null, goalsRow,
			                  s2);
					
					tabbedPane.addTab("The Others' Holding Pens", null, otherHPsRow,
			                  "Look at other people's holding pens.");
					
					tabbedPane.addTab("The Discard Pile", null, discardRow,
			                  "Look at the current discard pile.");
					
					//Add all the items to the pane
					uberpane.add(playerInfoRow);
					uberpane.add(tabbedPane);
					uberpane.add(handRow);
					
					if(message != null)
						JOptionPane.showMessageDialog(overallFrame, message);
					
					overallFrame.pack();
					overallFrame.setVisible(true);
					overallFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
				else
				{
					JPanel playerInfoRow = setUpPlayerInfoRow(p);//This will hold the player's name at the top of the screen.
					JTextArea blob = new JTextArea("THIS IS THE AI.");
					uberpane.add(playerInfoRow);
					uberpane.add(blob);
					
					if(message != null)
						JOptionPane.showMessageDialog(overallFrame, message);
					
					overallFrame.pack();
					overallFrame.setVisible(true);
					overallFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
				
			}
			else
			{
				message = "The game has been won by " + p.getName() + "!!!!";
				JOptionPane.showMessageDialog(overallFrame, message);
				System.exit(0);
			}
		}//end else on whether player is null		
		
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
		
		GridLayout grid = new GridLayout(1,g.numPlrs);//1 row with x cards
		HPsRow.setLayout(grid);
		
		JTabbedPane tabbedPane = new JTabbedPane();
		ArrayList<Player> plrs = g.gameboard.players;
		for(Player plr: plrs)//for each player
		{
			if(!plr.equals(p))
			{
				JPanel plrshp = new JPanel();
				Deck hp = plr.getHoldingPen();//Get the deck of all the discards from the board.
				plrshp = listButtons(hp,null);
				tabbedPane.addTab(plr.getName() + " - " + hp.count(), null, plrshp, listTitlesString(hp,""," "));
			}
		}	
		
		HPsRow.add(tabbedPane);
		return HPsRow;
	}

	
	/**
	 * This adds an action listener to the JButton that will pop up another window
	 * That window will have a list of the cards of the that person's holding pen.
	 * @param b
	 * @return the JButton that will pop up with the holding pen info.
	 */
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
	}

	
	/**
	 * Of a particular deck, list all the cards' titles in a string
	 * @param d the Deck
	 * @param str the String you want it to be placed into
	 * @param spacing whether you want a " " or a "\n"
	 * @return the String of all the cards' titles
	 */
	private static String listTitlesString(Deck d, String str, String spacing){
		if(d != null && d.deck != null)
		{
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
		//THIS SHALL BE A GRID OF BUTTONS
		
		GridLayout grid = new GridLayout(0,4);//any number of rows with 3 cards
		handRow.setLayout(grid);
		
		Deck hand = p.getHand();
		if(hand!= null)
		{
			ArrayList<Card> cards = hand.deck;//Get list of cards from the deck given
			for(Card cd: cards)//for each card
			{
				JButton b = new JButton(cd.getTitle());
				b.setText(cd.getTitle());//no description because ToolTips
				Image newimg = cd.getPicture().getScaledInstance( 75, 75,  java.awt.Image.SCALE_SMOOTH ) ; 
				b.setIcon(new ImageIcon(newimg));
				b.setHorizontalTextPosition(JButton.CENTER);
				b.setVerticalTextPosition(JButton.BOTTOM);
				b = addCardListeners(b,cd,p);
				b.setBackground(colorCard(cd));
				//b.setForeground(colorCard(cd)); //Let's leave the text as black, so you can read it here. Elsewhere, it will have the background's color.
				b.setOpaque(true);
				handRow.add(b);
				//handRow.add(b);
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
	 * Returns the color of the card according to its type
	 * @param cd the Card
	 * @return the color of that card
	 */
	public static Color colorCard(Card cd){
		if(cd.getClass().equals(new RuleCard().getClass()))
			return ruleColor;
		else if(cd.getClass().equals(new Possession().getClass()))
			return possColor;
		else if(cd.getClass().equals(new Goal().getClass()))
			return goalColor;//Goals are orange
		else
			return Color.black;//Other are black
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
				cardChosen(cd,p);
			}
		};
		
		b.addActionListener(alist);
		b.setToolTipText(cd.getDescription());
		return b;
	}

	/**
	 * The innards of the actionListener in addCardListeners()
	 * @param cd the Card
	 * @param p the Player
	 */
	private static void cardChosen(Card cd, Player p) {
		if(!discarding)
		{
			if(canPlay(p))
			{
				if(!isAI(p) && verifyCard(cd))
				{
					/*if(!p.getName().contains("AI"))
						message = "You played the " + cd.getTitle() + " card!";
					//if it is an AI, this fixes itself later...... I think		
					 * This is no longer necessary since verifyCard happens		*/					
					cd.playCard(p, g.gameboard);
					
					p.numPlaysSoFar +=1;
					
					//if this is a rule, then change the rules.
					if(cd.getClass().equals((new RuleCard()).getClass()))
					{
						replaceNumber((RuleCard) cd);
					}
					
					//REDRAW EVERYTHING!
					drawEverything(p, g.gameboard);
					
					if(canEnd(p,false))
						innardsEnd(p);
				}
				else if(isAI(p))
				{
					cd.playCard(p, g.gameboard);
					
					p.numPlaysSoFar +=1;
					
					//if this is a rule, then change the rules.
					if(cd.getClass().equals((new RuleCard()).getClass()))
					{
						replaceNumber((RuleCard) cd);
					}
					
					//REDRAW EVERYTHING!
					drawEverything(p, g.gameboard);
					
					//if(canEnd(p,false))
						//innardsEnd(p);
				}
			}
			else
			{
				message = "You can't play that card, because you have surpassed the number of plays per turn allowed. Press 'End Turn'.";
				drawEverything(p,g.gameboard);
				innardsEnd(p);
			}
		}
		else//you are discarding
		{
			if (wantDiscard(cd))
			{
				//System.out.println(cd.location);
				cd.location.removeCard(cd);					
				g.gameboard.addCard(cd, g.gameboard.discard);
				cd.location = g.gameboard.discard;
				message = "You discarded " + cd.getTitle() + "! Click 'Discard' again to discard a different card.";
				discarding = false;
				drawEverything(p,g.gameboard);
				
				if(canEnd(p,false))
					innardsEnd(p);
			}
			else
			{
				discarding = false;
			}
		}
		
	}

	/**
	 * Checks to see whether or not you actually want to play that card
	 * @param cd the card you're about to play
	 * @return whether you want to play it
	 */
	private static boolean verifyCard(Card cd) {
		String s = "Do you really want to play " + cd.getTitle() + "?";
		if(!cd.getTitle().equals(cd.getDescription()))
		{
			s += "\n Description: " + cd.getDescription();
		}
		int n = JOptionPane.showConfirmDialog(overallFrame,
						s,
						"are you sure?",
						JOptionPane.YES_NO_OPTION);
		if(n == JOptionPane.YES_OPTION)
			return true;
		else
			return false;
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
	private static JPanel setUpHoldingPenRow(Player p) {
		JPanel hpRow = new JPanel();
		Deck hp = p.getHoldingPen();//Get the deck of the holding pen from the player.
		
		GridLayout grid = new GridLayout(0,4);//any number of rows with 3 cards
		hpRow.setLayout(grid);
		if(!discarding)
		{
			hpRow = listButtons(hp,null);
		}
		else
		{
			
			if(hp!= null)
			{
				//hpRow = listButtons(hp,Color.PINK);WRONG CUZ WRONG LISTENERS HERE
				ArrayList<Card> cards = hp.deck;
				for(Card cd: cards)
				{
					JButton b = new JButton(cd.getTitle());
					b.setText(cd.getTitle());
					Image newimg = cd.getPicture().getScaledInstance( 75, 75,  java.awt.Image.SCALE_SMOOTH ) ; 
					b.setIcon(new ImageIcon(newimg));
					b.setForeground(Color.red);
					b = addCardListeners(b,cd,p);
					hpRow.add(b);
				}
				
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
		Deck discards = g.gameboard.getDiscards();//Get the deck of all the discards from the board.
		discardRow = listButtons(discards,null);
		return discardRow;
	}
	
	/**
	 * This sets up the JPanel about the Rules in play. Each card is represented by its title.
	 * @param b The Board
	 * @return The JPanel with the JTextArea of cards' titles.
	 */
	private static JPanel setUpRulesRow() {
		JPanel rulesRow = new JPanel();
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
	private static JPanel setUpGoalsRow() {
		JPanel goalsRow = new JPanel();
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
				//Get the goal's description
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
	
		JTextField blob = new JTextField();
		String str= "Player: " + p.getName();
		//I might add more player info later!
		blob.setText(str);
		blob.setEditable(false);
		
		JButton endTurn = new JButton("Nope");
		JButton discard = new JButton("Nope");
		if(!isAI(p))
		{
			discard = discardButton(p);
			endTurn = endTurnButton(p);
		}
			
		
		JTextArea messages = new JTextArea();
		messages.setText(message);
		
		plInfo.add(blob);
		if(!isAI(p))
		{
			plInfo.add(endTurn);
			plInfo.add(discard);
		}
			
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
				message = "You will be discarding the next card you click! Beware!";
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
				
				innardsEnd(currentPlayer);
			}
		};
		
		endTurn.addActionListener(alist);
		
		return endTurn;
	}

	/**
	 * What the endTurn Button does when it has been clicked.
	 * @param currentPlayer
	 */
	private static void innardsEnd(Player currentPlayer) {
		
		if(canEnd(currentPlayer,true))
		{
			//Redraw everything:
			Player nextPlayer = getNextPlayer(currentPlayer);
			if(!nextPlayer.getName().contains("AI") && !currentPlayer.getName().contains("AI"))
			{
				message = currentPlayer.getName() + " should walk away so that " + nextPlayer.getName() + " can come on.";
				drawEverything(currentPlayer,g.gameboard);
				hotSeatingTime();
			}
			
			message = "Now it is " + nextPlayer.name + "'s turn.";
			handleTurnChange(nextPlayer);
			if(nextPlayer.getClass() != (new ArtificialIntelligence()).getClass())
			{
				drawEverything(nextPlayer,g.gameboard);
			}
			else
			{
				ArtificialIntelligence AI = (ArtificialIntelligence) nextPlayer;
				doAIStuff(AI);
			}
			
		}	
	}

	/**
	 * Wait some time until the next person should arrive.
	 */
	private static void hotSeatingTime() {
		try {
			Thread.sleep(waitingTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * This returns whether or not a player is an AI
	 * @param p
	 * @return
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
		{
			//while you need to keep playing, choose cards
			Card cd = AI.PickCardSwitch();
			discarding = false;
			cardChosen(cd,AI);
			message = AI.getName() + " played " + cd.getTitle();
			drawEverything(AI,g.gameboard);
		}
		
		//possession limit
		AI.discardHoldingPen(determineNumber(3));
		//hand limit
		AI.discardHand(determineNumber(4));
		
		innardsEnd(AI);
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
	 * @param pink 
	 * @return the JPanel with all the buttons
	 */
	private static JPanel listButtons(Deck d, Color color){
		JPanel pane = new JPanel();
		
		for(Card cd : d.deck){
			JButton cdButt = new JButton(cd.getTitle());
			if(color == null)
				color = colorCard(cd);
			cdButt.setForeground(color.darker());
			cdButt.setBackground(color);
			cdButt.setOpaque(true);
			Image newimg = cd.getPicture().getScaledInstance(75, 75,  java.awt.Image.SCALE_SMOOTH ) ; 
			cdButt.setIcon(new ImageIcon(newimg));
			cdButt.setHorizontalTextPosition(JButton.CENTER);
			cdButt.setVerticalTextPosition(JButton.BOTTOM);
			cdButt.setToolTipText(cd.getDescription());
			cdButt = addDescriptionListener(cdButt,cd);
			pane.add(cdButt);
		}
		
		return pane;
	}
	
	
	/**
	 * This determines what needs to be done before the next Player is allowed to play any cards.
	 * Right now that is limited to drawing cards beforehand. And also, reseting numPlaysSoFar.
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
			//LOL. There aren't enough cards in the draw pile and the discard pile combined to draw enough cards.
			//Have fun with errors!!
			System.out.println("There aren't enough cards to draw from the combined discard and draw pile.");
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
	 * @param loud Whether or not you want drawEverything to be called
	 * @return whether you can end your turn
	 */
	private static boolean canEnd(Player p, boolean loud) {
		int numPlaysNeeded = determineNumber(1);
		int maxPoss = determineNumber(3);
		int maxHand = determineNumber(4);
		
//		if(p.getName().contains("AI"))
//			return true;
		
		if(p.numPlaysSoFar < numPlaysNeeded)
		{
			if(p.hand.count() != 0)
			{
				//System.out.println("You haven't played enough cards.");
				message = "You haven't played enough cards.";
				if(loud)
					drawEverything(p,g.gameboard);
				return false;
			}
			else
			{
				if(p.hand.count() > maxHand)
				{
					//System.out.println("You have too many cards in your hand.");
					message = "You have too many cards in your hand.";
					if(loud)
						drawEverything(p,g.gameboard);
					return false;
				}
				else
				{
					//you can't play anything
					message = "End turn, because you have no hand.";
					if(loud)
						drawEverything(p,g.gameboard);
					return true;
				}
				
			}
			
		}
		else
		{
			if(p.hand.count() > maxHand)
			{
				//System.out.println("You have too many cards in your hand.");
				message = "You have too many cards in your hand.";
				if(loud)
					drawEverything(p,g.gameboard);
				return false;
			}
			else
			{
				if(p.holdingPen.count()>maxPoss)
				{
					//System.out.println("You have too many cards in your holding pen.");
					message = "You have too many cards in your holding pen.";
					if(loud)
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
