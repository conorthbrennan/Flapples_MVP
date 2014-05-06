import java.util.ArrayList;


public class ArtificialIntelligence extends Player{
	int difficulty;//accepts an Integer from 0 - 3 with 0 being super easy and 3 being hard
	Game game;
	Deck noPlay;

	public ArtificialIntelligence(String n, int diff, Game g){
		super(n);
		difficulty = diff;
		game = g;
	}

	public ArtificialIntelligence(){
		super("Nobody");
	}
	
	public Card PickCardSwitch(){
		Card picked = null;//the card that is picked
		
		//chooses which pick card method to use based on AI difficulty
		switch(difficulty){
		
		case 0: picked = PickCardVeryEasy();
				break;
				
		case 1: picked = PickCardEasy();
				break;
				
		case 2: picked = PickCardNormal();
				break;
				
		case 3: picked = PickCardHard();
				break;
		}
		
		if(noPlay!= null)
			hand.addCards((Card[]) noPlay.deck.toArray());
		
		/*for(Card cd : noPlay.deck){
			hand.addCard(cd);
			//noPlay.removeCard(cd);
		}*/
		numPlaysSoFar += 1;
		
		return picked;
	}
	
	public Card PickCardVeryEasy(){
		int index = (int) (Math.random() * hand.count());
		Card picked = hand.getDeck().get(index);
		System.out.println("played at random");
		return picked;
	}
	
	public Card PickCardEasy(){
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
			System.out.println("played because this card best fits the goal");
			return theCard;
		}
		else
			return PickCardVeryEasy();
	}
	
	public Card PickCardNormal(){
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
			System.out.println("Played because this card fits the cards in my pen");
			return theCard;
		}
		else
			return PickCardEasy();
	}
	
	public Card PickCardHard(){
		ArrayList<Player> players = game.gameboard.players;
		//removes this AI from the list of players so it will allow itself to win
		Deck goals = goalsHeld();
		Card theCard = null;
		Player AI = null;
		for (int p = 0; p < players.size(); p++) {
			if(players.get(p).getName().equals(name)){
				AI = players.get(p);
				players.remove(p);
			}
		}
		for (int i = 0; i < players.size(); i++) {
			for (int j = 0; j < players.get(i).holdingPen.count(); j++) {
				for (int k = 0; k < goals.count(); k++) {
					boolean fit = false;
					Card penCard =players.get(i).holdingPen.deck.get(j);
					Goal goal = (Goal) goals.deck.get(k);
					if(goal.doesItFit(penCard)){
						theCard = hand.search(goal.getID());
						noPlay.addCard(theCard);
						hand.removeCard(theCard);
					}
				}
			}	
		}
		
		players.add(AI);
		
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
				}//end if
			}//end for
		}//end for
		while(holdingPen.count() > max){//if there are no cards left that aren't necessary discard cards randomly
			int index = (int) (Math.random() * holdingPen.count());//gets a random number
			Card card = holdingPen.getDeck().get(index);//finds the card that corresponds to the number
			holdingPen.removeCard(card);//the card is discarded
			game.gameboard.discard.addCard(card);//adds the card to the discard pile
		}//end while
	}//end discard
	
	/**
	 * this method chooses which cards to discard from hand when necessary
	 * @param max - the current limit on possessions currently in play
	 */
	public void discardHand(int max){
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
				}//end if
			}//end for
		}//end for
		while(hand.count() > max){//if there are no cards left that aren't necessary discard cards randomly
			int index = (int) (Math.random() * hand.count());//gets a random number
			Card card = hand.getDeck().get(index);//finds the card that corresponds to the number
			hand.removeCard(card);//the card is discarded
			game.gameboard.discard.addCard(card);//adds the card to the discard pile
		}//end while
	}//end discard

}//end Artificial Intelligence
