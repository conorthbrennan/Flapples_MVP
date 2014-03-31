import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;


public class StandardDeck extends Deck{

	public StandardDeck(GameObject creator) {
		super(creator);
		this.deck = putInCards(creator);
	}

	private ArrayList<Card> putInCards(GameObject creator) {
		
		try {
			BufferedImage img = ImageIO.read(new File("exampleCardIcon.png"));
			
			//Possessions!
			//Possession apple = new Possession("Apple",ImageIO.read(new File("apple.png")),"It's an apple. Pretty self-explanatory.",1,this);
			Possession apple = new Possession("Apple",img,"It's an apple. Pretty self-explanatory.",1,this);
			deck.add(apple);
			//Possession banana = new Possession("Banana",ImageIO.read(new File("banana.png")),"bananananana",2,this);
			Possession banana = new Possession("Banana",img,"bananananana",2,this);
			deck.add(banana);
			
			//Goals!
			ArrayList<Possession> reqdCards = new ArrayList<Possession>();
			reqdCards.add(apple);
			reqdCards.add(banana);
			//Goal ootootoot = new Goal(creator.getGame(),"ootootoot",ImageIO.read(new File("ootootoot.png")),"Have: Apple and Banana",3,this,reqdCards);
			Goal ootootoot = new Goal(creator.getGame(),"ootootoot",img,"Have: Apple and Banana",3,this,reqdCards);
			deck.add(ootootoot);
			
			//Rules
			//RuleCard draw2 = new RuleCard("Draw 2",ImageIO.read(new File("draw2.png")),"Draw 2 Cards",4,this);
			RuleCard draw2 = new RuleCard("Draw 2",img,"Draw 2 Cards",4,this,2,2);
			deck.add(draw2);
			//RuleCard play2 = new RuleCard("Play 2",ImageIO.read(new File("play2.png")),"Play 2 Cards",5,this);
			RuleCard play2 = new RuleCard("Play 2",img,"Play 2 Cards",5,this,1,2);
			deck.add(play2);
			
			RuleCard handLimit4 = new RuleCard("Hand Limit 4",img,"4 cards at most in your hand.",6,this,4,4);
			deck.add(handLimit4);
			
			RuleCard possLimit4 = new RuleCard("Holding Pen Limit 4",img,"4 cards at most in your holding pen.",7,this,3,4);
			deck.add(possLimit4);
			
			RuleCard possLimit2 = new RuleCard("Holding Pen Limit 2",img,"2 cards at most in your holding pen.",8,this,3,2);
			deck.add(possLimit2);
			
			//temporary extra cards so I can give each player 3 cards for testing:
			deck.add(apple);
			deck.add(apple);
			deck.add(apple);
			deck.add(apple);
			deck.add(apple);
			deck.add(apple);
			deck.add(apple);
			deck.add(apple);
			deck.add(apple);
			
		} catch (IOException e1) {
			System.out.println("WHERE IS/ARE YOUR FILE(S)?");			
		}
		
		
		return deck;
	}
	
}