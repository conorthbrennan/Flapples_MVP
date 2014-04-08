
public class ArtificialIntelligence extends Player{
	int difficulty;//accepts an Integer from 0 - 4 with 0 being super easy and 4 being mega hard
	Game game;

	public ArtificialIntelligence(String n, int diff, Game g){
		super(n);
		difficulty = diff;
		game = g;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

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
				
		case 4: picked = PickCardVeryHard();
				break;
		}
		
		return picked;
	}
	
	public Card PickCardVeryEasy(){
		int index = (int) (Math.random() * hand.count());
		Card picked = hand.getDeck().get(index);
		return picked;
	}
	
	public Card PickCardEasy(){
		return null;
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
