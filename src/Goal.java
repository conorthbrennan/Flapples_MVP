import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * A card that involves a goal.
 * @author Rebecca Thomas, Billy Leete,Conor Brennan
 *
 */
public class Goal extends Card{

	// has to reference specific Possessions objects
	// has to have a method that interfaces with Game's evaluateGoalMatching method
	
	public ArrayList<Possession> necCardsToWin;
	
	/**
	 * constructor for a null goal card
	 */
	public Goal(){
		//super(null, null, null, (Integer) null, null);
		necCardsToWin=null;
		super.g = null;
	}//end goal
	
	/**
	 * constructor for goal cards
	 * @param game
	 * @param titl
	 * @param pic
	 * @param descrip
	 * @param id
	 * @param locate
	 * @param winCards
	 */
	public Goal(Game game, String titl, BufferedImage pic, String descrip, int id, Deck locate, ArrayList<Possession> winCards) {
		super(titl, pic, descrip, id, locate);
	
		necCardsToWin=winCards;
		
		super.g = game;
		
		// set up configuration
	}//end constructor
	
	/**
	 * gets the cards needed to win
	 * @return
	 */
	public ArrayList<Possession> getWinCards(){
		return necCardsToWin;
	}//end getWinCards()
	
	@Override
	/**
	 * plays the goal card from the hand
	 * @param pl - the player playing the card
	 * @param b - the board the card is being played on
	 */
	public void playCard(Player pl, Board b) {
		pl.hand.removeCard(this);
		
		//Dispense with old goal:
		if(b.goals.count() >= 1)//if there is a goal:
				b.goals.drawCard(b.discard, 1);//move the old goal to the discard pile
		b.goals.addCard(this);//add new goal
		
		//change the location of this goal:
		this.location = this.g.getBoard().getGoals();
		
		//handle the goal
		g.evaluateGoalMatching();
	}//end playCard()
	
	/**
	 * Returns whether or not the player with this deck has won for this goal card
	 * @param holdingPen
	 * @return Won?
	 */
	public boolean hasWon(Deck holdingPen){
		for(Card n : necCardsToWin)
		{
			Card copyCard = holdingPen.search(n.ID);//searches the players holding pen for the necessary cards
			if (copyCard == null)//if you don't have this necessary card
				return false;
		}//end for
		
		return true;
	}//end hasWon()

	/**
	 * Returns whether or not this card is within one of the Goal cards
	 * @param the Card
	 * @return is this card in one of the goal cards
	 */
	public boolean doesItFit(Card cd){
		for(Card n : necCardsToWin)//look at every card required for this goal card 
			if(cd.equals(n))//if a card is the same as the card being looked at
				return true;
		
		return false;
	}//end doesItFit()
	
	/**
	 * creates a clone of the goal card
	 */
	public Goal clone(){
		Goal clone = new Goal(g, title, picture, description, ID, location, necCardsToWin);// makes a new card with all the same variables as the original
		return clone;
	}//end clone()
}
