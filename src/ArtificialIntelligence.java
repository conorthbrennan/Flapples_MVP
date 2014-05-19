import java.util.ArrayList;


public class ArtificialIntelligence extends Player{
	int difficulty;//accepts an Integer from 0 - 2 with 0 being easy and 2 being hard
	Game game;//the game that this is in

	/**
	 * the actual constructor
	 * @param n - the name
	 * @param diff - the difficulty of the AI
	 * @param g - the game
	 */
	public ArtificialIntelligence(String n, int diff, Game g){
		super(n);//calls the player constructor
		difficulty = diff;//sets the difficulty of the AI
		game = g;//sets the game object 
	}

	/**
	 * the basic constructor
	 */
	public ArtificialIntelligence(){
		super("Nobody");//makes a player
	}
	
	/**
	 * this uses the AI's difficulty to pick a caard to be played
	 * @return the card to be played
	 */
	public Card PickCardSwitch(){
		Card picked = null;//the card that is picked
		
		//chooses which pick card method to use based on AI difficulty
		switch(difficulty){
		
		case 0: picked = PickCardEasy();//difficulty 0 = easy
				break;
				
		case 1: picked = PickCardNormal();//difficulty 1 = normal
				break;
				
		case 2: picked = PickCardHard();//difficulty 2 = hard
				break;
		}//end switch
		
		return picked;//return the card it picked
	}// end PickCardSwitch()
	
	/**
	 * the picking method for easy Ai
	 * @return the card to be played
	 */
	public Card PickCardEasy(){
		if(hand.count()!= 0)//checks if the players hand is empty
		{//if not empty then pick a random card
			int index = (int) (Math.random() * hand.count());//takes a random number
			Card picked = hand.getDeck().get(index);//take a card from your hand corresponding to that number
			System.out.println(picked.getTitle() + " was played");//say it was played
			return picked;//return the card it picked
		}//end if
		System.out.println("The AI has no hand.");
		return null;//play nothing
	}//end PickCardEasy()
	
	public Card PickCardNormal(){
		Deck goals = game.gameboard.goals;
		//Let's see if any of the cards in your hand fit the goals played
		Card theCard = null;
		boolean fit = false;
		for(int i =0 ; i < goals.count(); i++)
		{
			for(int j=0; j < hand.count();j++)
			{
				Card hdcd = hand.deck.get(j);
				Goal gl = (Goal) goals.deck.get(i);
				//is this card one of the necessary keepers for the goal?
				fit = gl.doesItFit(hdcd);
				if(fit)
					theCard = hdcd;
			}
		}
		
		if(fit){
			System.out.println(theCard.getTitle() + " was played");
			return theCard;
		}
		else
			return PickCardEasy();
	}//end PickCardNormal()
	
	public Card PickCardHard(){
		//for every Goal card in your hand, see if playing it would make you win.
		//if so, play that.
		Card theCard = null;
		boolean fit = false;
		Deck goals = goalsHeld();
		for (int i = 0; i < goals.count(); i++) {
			for (int j = 0; j < holdingPen.count(); j++) {
				Card penCard = holdingPen.deck.get(j);
				Goal gl = (Goal) goals.deck.get(i);
				//is the card in the pen a requirement for this goal?
				fit = gl.doesItFit(penCard);
				if(fit)
					theCard = hand.search(gl.getID());
			}
		}
		
		if(fit){
			System.out.println(theCard.getTitle() + " was played");
			return theCard;
		}
		else
			return PickCardNormal();
	}
	
	/**
	 * Returns a copy of all the goals in your hand.
	 * @return the deck of goals from your hand
	 */
	public Deck goalsHeld(){
		Deck goals = new Deck();
		for (int i = 0; i < hand.count(); i++) {
			Card hdcd = hand.deck.get(i);
			boolean isGoal = false;
			isGoal = hdcd.getClass().equals(new Goal().getClass());
			if(isGoal){
				Goal goal = (Goal)hdcd;
				Goal clone = goal.clone();
				goals.addCard(clone);
			}
		}
		return goals;
	}
	
	/**
	 * this method chooses which cards to discard from the board when necessary
	 * @param max - the current limit on possessions currently in play
	 */
	public void discardHoldingPen(int max){
		if(holdingPen.count() > max){
			Deck goals = new Deck();//creates a new deck to hold all relevant goal cards
			goals.deck.addAll(goalsHeld().deck);//adds the goals in hand to the deck
			goals.deck.addAll(game.gameboard.goals.deck);//adds the goals on the board to the deck
			for(int i = 0; i < goals.count(); i++){//this goes through all the goals in the AI's hand and in play
				for (int j = 0; j < holdingPen.count(); j++) {//this for loop goes through all the cards in the AI's holding pen
					Goal goal = (Goal) goals.deck.get(i);//the goal card being examined
					Card card = holdingPen.deck.get(j);//the card in the holding Pen being examined
					if(!goal.doesItFit(card)){//sees if the card fits the goal
						holdingPen.removeCard(card);//if not the card is discarded
						game.gameboard.discard.addCard(card);//adds the card to the discard pile
						System.out.println(name + " discarded from its holding pen " + card.getTitle());
					}//end if
				}//end for
			}//end for
			while(holdingPen.count() > max){//if there are no cards left that aren't necessary discard cards randomly
				int index = (int) (Math.random() * holdingPen.count());//gets a random number
				Card card = holdingPen.getDeck().get(index);//finds the card that corresponds to the number
				holdingPen.removeCard(card);//the card is discarded
				game.gameboard.discard.addCard(card);//adds the card to the discard pile
				System.out.println(name + " discarded from its holding pen " + card.getTitle());
			}//end while
		}//end if
	}//end discard
	
	/**
	 * this method chooses which cards to discard from hand when necessary
	 * @param max - the current limit on possessions currently in play
	 */
	public void discardHand(int max){
		if(hand.count() > max){
			Deck goals = new Deck();//creates a new deck to hold all relevant goal cards
			goals.deck.addAll(goalsHeld().deck);//adds the goals in hand to the deck
			goals.deck.addAll(game.gameboard.goals.deck);//adds the goals on the board to the deck
			for(int i = 0; i < goals.count(); i++){//this goes through all the goals in the AI's hand and in play
				for (int j = 0; j < hand.count(); j++) {//this for loop goes through all the cards in the AI's hand
					Goal goal = (Goal) goals.deck.get(i);//the goal card being examined
					Card card = hand.deck.get(j);//the card in the hand being examined
					if(!goal.doesItFit(card) && hand.count() > max){//sees if the card fits the goal
						hand.removeCard(card);//if not the card is discarded
						game.gameboard.discard.addCard(card);//adds the card to the discard pile
						System.out.println(name + " discarded from its hand " + card.getTitle());
					}//end if
				}//end for
			}//end for
			while(hand.count() > max){//if there are no cards left that aren't necessary discard cards randomly
				int index = (int) (Math.random() * hand.count());//gets a random number
				Card card = hand.getDeck().get(index);//finds the card that corresponds to the number
				System.out.println(name + " discarded from its hand " + card.getTitle());
				hand.removeCard(card);//the card is discarded
				game.gameboard.discard.addCard(card);//adds the card to the discard pile
			}//end while
		}//end if
	}//end discard

}//end Artificial Intelligence
