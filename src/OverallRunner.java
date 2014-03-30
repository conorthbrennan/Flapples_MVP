import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;


public class OverallRunner
{

	private static BufferedImage img;
	private static JFrame overallFrame;
	
	public static void main(String [] args)
	{
		Game g = new Game();
		
		StandardDeck sd = new StandardDeck(g);
		//CONOR WRITE THIS CODE FOR ME!
		//STANDARD DECK, I WANT TO BE LIKE sd.getCard("Banana") and get that card
		
		//all sorts of stuff here
		
		Board exampleBoard = g.gameboard;
		//When making the board, all the decks get initialized (including the drawpile and players' hands and hps)
		
		ArrayList<Player> plrs = exampleBoard.players;
		
		//System.out.println("" + drawpile.count());
		
		//Give each player three cards.
		for(int i = 0; i < 3; i++)
			for(Player pl : plrs)
			{
				exampleBoard.drawPile.drawCard(pl.getHand(), 1);
			}
				
		//for the purposes of seeing what's really in peoples' hands
		for(Player pl: plrs)
		{
			System.out.println(pl.name);
			for(Card cd: pl.hand.deck){
				System.out.println(cd.getTitle());
			}
		}
		
		//Make sure to initialize the original frame
		overallFrame = new JFrame("Flapples");
		drawEverything(plrs.get(0),exampleBoard);
		
		//System.out.println("" + drawpile.count());
		//System.out.println("plr0 " + plrs.get(0).hand.count());
		//System.out.println("plr1 " + plrs.get(1).hand.count());
		
		/*boolean won = false;//THIS SHOULD BE CHANGED
		while(!won)
		{
			for(Player pl : plrs){
				drawEverything(pl,g.gameboard);
				//game play here
			}
		}*/
		
		/*//Let's make the player's hand the apple and the banana
		Deck hand = new Deck(g);
		Possession apple = new Possession("Apple",img,"It's an apple. Pretty self-explanatory.",1,hand);
		Possession banana = new Possession("Banana",img,"bananananana",2,hand);
		hand.addCard(apple);
		hand.addCard(banana);
		
		//Let's make the goal(s) the ootootoot card
		Deck goals = new Deck(g);
		ArrayList<Possession> reqdCards = new ArrayList<Possession>();
		reqdCards.add(apple);
		reqdCards.add(banana);
		Goal ootootoot = new Goal(g,"ootootoot",img,"Have: Apple and Banana",3,goals,reqdCards);
		goals.addCard(ootootoot);
		
		goals.addCard(banana);//WRONGWRONGWRWONGOWNGONWONGOWNGOWNGOGWNGWO
		
		//Let's make the rules
		Deck rules = new Deck(g);
		RuleCard draw2 = new RuleCard("Draw 2",img,"Draw 2 Cards",4,rules);
		rules.addCard(draw2);
		
		rules.addCard(banana);//WRONG WRONG WRONG WRONG
		
		//Let's make the discard pile
		Deck discard = new Deck(g);
		discard.addCard(draw2);//WRONGWRONGWRNGWONGWOENGOEWNEOGNOEWGNEWON
		discard.addCard(banana);//WRONWORNWOENROWENROWENR
		
		//Let's make the holding pen
		Deck hp = new Deck(g);
		hp.addCard(banana);//WERONEWORNWENFWEINFIWENFLIWEF
		hp.addCard(apple);//WRONWEORMEWORwWEORNEWONRW
		
		//Let's actually set everything
		exampleBoard.goals = goals;
		exampleBoard.rules = rules;
		exampleBoard.discard = discard;
		examplePlayer.setHoldingPen(hp);
		examplePlayer.setHand(hand);*/

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
				System.out.println(e.getActionCommand());
				//THIS SHOULD PROLLY ACTUALLY PLAY THE CARD INSTEAD......
				cd.playCard(p, brd);
				//REDRAW EVERYTHING!
				drawEverything(p, brd);
			}
		};
		
		b.addActionListener(alist);
		return b;
	}

	private static JPanel setUpHoldingPenRow(Player p) {
		JPanel hpRow = new JPanel();
		String str = "Holding Pen: \n";
		Deck hp = p.getHoldingPen();//Get the deck of the holding pen from the player.
		JTextArea blob = listTitles(hp,str);
		hpRow.add(blob);//add the text to the panel
		return hpRow;
	}
	private static JPanel setUpDiscardPileRow(Board b) {
		JPanel discardRow = new JPanel();
		String str = "Discard Pile: \n";
		Deck discards = b.getDiscards();//Get the deck of all the discards from the board.
		JTextArea blob = listTitles(discards,str);
		discardRow.add(blob);//add the text to the panel
		return discardRow;
	}
	private static JPanel setUpRulesRow(Board b) {
		JPanel rulesRow = new JPanel();
		String str = "Rules: \n";
		Deck rules = b.getRules();//Get the deck of all the rules from the board.
		JTextArea blob = listTitles(rules,str);
		rulesRow.add(blob);//add the text to the panel
		return rulesRow;
	}
	private static JPanel setUpGoalsRow(Board b) {
		JPanel goalsRow = new JPanel();
		String str= "Goals: \n";
		Deck goals = b.getGoals();//Get the deck of all the goals from the board.
		JTextArea blob = listTitles(goals,str);
		goalsRow.add(blob);//add the text to the panel
		return goalsRow;
	}
	private static JPanel setUpPlayerInfoRow(Player p) {
		JPanel plInfo = new JPanel();
	
		JTextArea blob = new JTextArea();
		String str= "Player: " + p.getName();
		//I might add more player info later!
		blob.setText(str);
		blob.setPreferredSize(new Dimension(str.length() * 10,str.length() * 2));
		
		plInfo.add(blob);
		
		return plInfo;
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

}