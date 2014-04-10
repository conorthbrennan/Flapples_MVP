import java.util.ArrayList;


public class ArtificialIntelligence extends Player{
	int difficulty;//accepts an Integer from 0 - 4 with 0 being super easy and 4 being mega hard
	Game game;

	public ArtificialIntelligence(String n, int diff, Game g){
		super(n);
		difficulty = diff;//the difficulty of the AI
		game = g;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	
	public Card PickCardSwitch(){
		Card picked = null;//the card that is picked
		
		//chooses which pick card method to use based on AI difficulty
		switch(difficulty){
		
		case 0: picked = PickCardVeryEasy();//
				break;
				
		case 1: picked = PickCardEasy();
				break;
				
		case 2: picked = PickCardNormal();
				break;
				
		case 3: picked = PickCardHard();
				break;
				
		case 4: picked = PickCardVeryHard();
				break;
		}
		
		return picked;
	}
	
	/**
	 * on the easiest
	 * @return picked - returns what ever card the AI will play
	 */
	public Card PickCardVeryEasy(){
		Card picked;//the card to be played
		int index = (int) (Math.random() * hand.count());//generates a random number
		picked = hand.deck.get(index);
		return picked;
	}
	
	public Card PickCardEasy(){
		Card picked = null;
		Deck look = null;//a deck that contains all the cards this AI needs to consider
		
		for(Card cd : hand.deck)//adds the hand to the look deck
			look.addCard(cd);
		for(Card cd : holdingPen.deck)//adds the holding pen to the look deck
			look.addCard(cd);
		
		for (Card cd : hand.deck) {
			if (cd.getClass().equals((new Goal()).getClass())) {
				ArrayList<Possession> cardsNeeded = new ArrayList<Possession>();
				for (Card pos : look.deck){
					if (cardsNeeded.contains(pos)) {
						picked = cd;
					}//end if
				}//end for
			}//end if
		}//end for
		
		for (Card cd : hand.deck){
			Goal gl = (Goal) game.gameboard.goals.deck.get(0);//gets the goal currently in play
			if(gl.necCardsToWin.contains(cd))
				picked = cd;
		}
		
		
		
		return picked;
	}
	
	public Card PickCardNormal(){
		return null;
	}
	
	public Card PickCardHard(){
		return null;
	}
	
	public Card PickCardVeryHard(){
		return null;
	}

}
