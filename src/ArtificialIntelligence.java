
public class ArtificialIntelligence extends Player{
	int difficulty;//accepts an Integer from 0 - 4 with 0 being super easy and 4 being mega hard

	public ArtificialIntelligence(String n, int diff){
		super(n);
		difficulty = diff;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public Card PickCardSwitch(){
		Card picked = null;
		
		switch(difficulty){
		
		case 0: 
		}
		
		return picked;
	}
	
	public Card PickCardVeryEasy(){
		return null;
	}

}
