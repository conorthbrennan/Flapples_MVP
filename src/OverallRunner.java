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
import javax.swing.UIManager;

public class OverallRunner
{

	public static void main(String [] args)
	{
		
		
		
		//I shall use JPanes and JFrames to display everything. 
		drawEverything();



	}

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
		Container pane = testframe.getContentPane();

		//FlowLayout flow = new FlowLayout(FlowLayout.RIGHT,200,20);//alignment,hgap,vgap		
		BoxLayout box = new BoxLayout(pane,BoxLayout.LINE_AXIS);
		pane.setLayout(box);

		JButton top = new JButton("Top");
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

		//Add all the items to the pane
		pane.add(top);
		pane.add(bot);

		testframe.pack();
		testframe.setVisible(true);
		testframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}