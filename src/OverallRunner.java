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
	public static void main(String [] args)
	{
		Game g = new Game();
		//StandardDeck sd = new StandardDeck();
		Deck sd = new Deck(g);
		sd = setUpStandardDeck(sd);//CONOR WRITE THIS CODE FOR ME!
		//THERE SHOULD BE A NEW CLASS, STANDARD DECK, SO THAT I CAN BE LIKE sd.getCard("Banana") and get that card
		
		//all sorts of stuff here
		//each player should get 3 cards to start off with
		
		try {
			img = ImageIO.read(new File("exampleCardIcon.png"));
			
		} catch (IOException e1) {
			System.out.println("WHERE IS/ARE YOUR FILE(S)?");			
		}
		
		Board exampleBoard = g.gameboard;
		Player examplePlayer = g.gameboard.players.get(0);
		
		//Let's make the player's hand the apple and the banana
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
		examplePlayer.setHand(hand);
		
		drawEverything(examplePlayer,exampleBoard);
		
		//ArrayList<Player> plrs = g.gameboard.getPlayers();
	
		/*boolean won = false;//THIS SHOULD BE CHANGED
		while(!won)
		{
			for(Player pl : plrs){
				drawEverything(pl);
				//game play here
			}
		}*/

	}
	
	/**
	 * This makes the incoming deck the original set of cards.
	 * @param sd
	 * @return the initial deck of cards.
	 */
	private static Deck setUpStandardDeck(Deck sd) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * This draws everything.
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

		JFrame testframe = new JFrame("Flapples");
		Container uberpane = testframe.getContentPane();

		//FlowLayout flow = new FlowLayout(FlowLayout.RIGHT,200,20);//alignment,hgap,vgap		
		BoxLayout box = new BoxLayout(uberpane,BoxLayout.PAGE_AXIS);//top to bottom
		uberpane.setLayout(box);

		JPanel playerInfoRow = setUpPlayerInfoRow(p);//This will hold the player's name at the top of the screen.
		JPanel goalsRow = setUpGoalsRow(b);//This will hold the goals in the second row.
		JPanel rulesRow = setUpRulesRow(b);//This will hold the rules in the third row.
		JPanel discardRow = setUpDiscardPileRow(b);//This will hold the discard pile in the fourth row.
		JPanel holdingPenRow = setUpHoldingPenRow(p);//This will be the player's holding pen in the fifth row.
		JPanel handRow = setUpHandRow(p);//This will hold the player's hand.
		
		//Add all the items to the pane
		uberpane.add(playerInfoRow);
		uberpane.add(goalsRow);
		uberpane.add(rulesRow);
		uberpane.add(discardRow);
		uberpane.add(holdingPenRow);
		uberpane.add(handRow);
		
		testframe.pack();
		testframe.setVisible(true);
		testframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	private static JPanel setUpHandRow(Player p) {
		JPanel handRow = new JPanel();
		//THIS SHALL BE A ROW OF BUTTONS
		
		Deck hand = p.getHand();
		ArrayList<Card> cards = hand.deck;//Get list of cards from the deck given
		for(Card cd: cards)//for each card
		{
			JButton b = new JButton(cd.getTitle());
			
			b.setIcon(new ImageIcon(img));
			b = addListeners(b);
			handRow.add(b);
		}
	
		return handRow;
	}
	private static JButton addListeners(JButton b) {
		//The ActionListener for all the buttons:
		//To be used to make sure the buttons could work:
		ActionListener alist = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(e.getActionCommand());

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

		ArrayList<Card> cards = d.deck;//Get list of cards from the deck given
		for(Card cd: cards)//for each card
		{
			str += cd.getTitle() + '\n';//add its title to the string str
		}
		
		blob.setText(str);//set the text of the textArea to str
		blob.setPreferredSize(new Dimension(str.length() * 10,str.length() * 2));//make the TextArea big enough to read
		
		return blob;
	}

}