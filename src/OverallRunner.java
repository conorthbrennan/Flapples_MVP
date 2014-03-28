import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class OverallRunner
{

	public static void main(String [] args)
	{
		Game g = new Game();
		//StandardDeck sd = new StandardDeck();
		Deck sd = new Deck(g);
		sd = setUpStandardDeck(sd);//CONOR WRITE THIS CODE FOR ME!
		
		//all sorts of stuff here
		//each player should get 3 cards to start off with
		
		
		//I shall use JPanes and JFrames to display everything. 
		//This will need to take in which Player is going in order to work properly.
		drawEverything();



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
	private static void drawEverything() {
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

		JPanel playerInfoRow = setUpPlayerInfoRow();//This will hold the player's name at the top of the screen.
		JPanel goalsRow = setUpGoalsRow();//This will hold the goals in the second row.
		JPanel rulesRow = setUpRulesRow();//This will hold the rules in the third row.
		JPanel discardRow = setUpDiscardPileRow();//This will hold the discard pile in the fourth row.
		JPanel holdingPenRow = setUpHoldingPenRow();//This will be the player's holding pen in the fifth row.
		JPanel handRow = setUpHandRow();//This will hold the player's hand.
		
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
	private static JPanel setUpGoalsRow() {
		// TODO Auto-generated method stub
		return null;
	}
	private static JPanel setUpPlayerInfoRow() {
		// TODO Auto-generated method stub
		return null;
	}

}