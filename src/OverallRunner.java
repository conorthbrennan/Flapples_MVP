import java.awt.Container;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;

public class OverallRunner
{

	public static void main(String [] args)
	{
		Game g = new Game();
		//StandardDeck sd = new StandardDeck();
		Deck sd = new Deck(g);
		sd = setUpStandardDeck(sd);//CONOR WRITE THIS CODE FOR ME!
		//THERE SHOULD BE A NEW CLASS, STANDARD DECK, SO THAT I CAN BE LIKE sd.getCard("Banana") and get that card
		
		//all sorts of stuff here
		//each player should get 3 cards to start off with
		
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("exampleCardIcon.png"));
			
		} catch (IOException e1) {
			System.out.println("WHERE IS/ARE YOUR FILE(S)?");			
		}
		
		Board exampleBoard = new Board(g,1);
		Player examplePlayer = new Player("Bob");
		
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
		Goal ootootoot = new Goal(g,"ootootoot", reqdCards);
		goals.addCard(ootootoot);
		
		//Let's actually set everything
		exampleBoard.goals = goals;
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
		JPanel rulesRow = setUpRulesRow();//This will hold the rules in the third row.
		JPanel discardRow = setUpDiscardPileRow();//This will hold the discard pile in the fourth row.
		JPanel holdingPenRow = setUpHoldingPenRow();//This will be the player's holding pen in the fifth row.
		JPanel handRow = setUpHandRow();//This will hold the player's hand.
		
		//Add all the items to the pane
		uberpane.add(playerInfoRow);
		uberpane.add(goalsRow);
		//uberpane.add(rulesRow);
		//uberpane.add(discardRow);
		//uberpane.add(holdingPenRow);
		//uberpane.add(handRow);
		
		testframe.pack();
		testframe.setVisible(true);
		testframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
/*		JButton top = new JButton("Top");
		JButton bot = new JButton("Bottom");

		ActionListener alist = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("You clicked the " + e.getActionCommand());

			}
		};

		top.addActionListener(alist);
		bot.addActionListener(alist);

		MouseListener mlist1 = new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e) {

				System.out.println("YOU went over TOP" );

			}			
		};

		MouseListener mlist2 = new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e) {

				System.out.println("YOU went over BOTTOM");

			}			
		};

		top.setToolTipText("THIS IS THE TOP");
		bot.setToolTipText("THIS IS THE BOTTOM");

		top.addMouseListener(mlist1);
		bot.addMouseListener(mlist2);
*/
		
	}
	private static JPanel setUpHandRow() {
		// TODO Auto-generated method stub
		return null;
	}
	private static JPanel setUpHoldingPenRow() {
		// TODO Auto-generated method stub
		return null;
	}
	private static JPanel setUpDiscardPileRow() {
		// TODO Auto-generated method stub
		return null;
	}
	private static JPanel setUpRulesRow() {
		// TODO Auto-generated method stub
		return null;
	}
	private static JPanel setUpGoalsRow(Board b) {
		JPanel goalsRow = new JPanel();
		
		JTextArea blob = new JTextArea();
		String str= "";
		
		Deck goals = b.getGoals();
		ArrayList<Card> cards = goals.deck;
		for(Card gl: cards)
		{
			str += gl.getTitle() + '\n';
		}
		
		blob.setText(str);
		blob.setPreferredSize(new Dimension(str.length() * 10,str.length() * 2));
		goalsRow.add(blob);
		
		return goalsRow;
	}
	private static JPanel setUpPlayerInfoRow(Player p) {
		JPanel plInfo = new JPanel();
	
		JTextArea blob = new JTextArea();
		String str= "Player: " + p.getName();
		blob.setText(str);
		blob.setPreferredSize(new Dimension(str.length() * 10,str.length() * 2));
		
		plInfo.add(blob);
		
		return plInfo;
	}

}