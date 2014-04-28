import java.util.ArrayList;


public class ArtificialIntelligence extends Player{
	int difficulty;//accepts an Integer from 0 - 4 with 0 being super easy and 4 being mega hard
	Game game;

	public ArtificialIntelligence(String n, int diff, Game g){
		super(n);
		difficulty = diff;
		game = g;
	}

	public ArtificialIntelligence(){
		super("Nobody");
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
					theCard = gl;
			}
		}
		
		if(fit){
			System.out.println("Played because this card fits the cards in my pen");
			return theCard;
		}
		else
			return PickCardNormal();
	}
	
	public Card PickCardHard(){
		//for every card in your hand, you check to see if playing it would make anyone else win.
		//if so, kill it (unless you have to play it)
		//then take the limited set of cards now and see if playing it would 
		//make you win.
		//if none of the cards make you win, then
		//see if any of the cards is on the current goal card
		//if none of the cards are on the goals,
		//then BLA BLA BLA card/board position
		
		ArrayList<Player> players = game.gameboard.players;
		//removes this AI from the list of players so it will allow itself to win
		for(int p = 0; p < players.size(); p++){
			if(players.get(p).getName().equals(name))
				players.remove(p);
		}
		
		return null;
	}
	
	public Card PickCardVeryHard(){
		return null;
	}
	
	public Deck goalsHeld(){
		Deck goals = new Deck();
		for (int i = 0; i < hand.count(); i++) {
			Card hdcd = hand.deck.get(i);
			boolean isGoal = false;
			isGoal = hdcd.getClass().equals(new Goal().getClass());
			if(isGoal){
				Goal goal = (Goal)hdcd;
				Goal clone = new Goal(g, goal.getTitle(), goal.getPicture(), goal.getDescription(), goal.getID(), goal.getLocation(), goal.getWinCards());
				goals.addCard(clone);
			}
		}
		return goals;
	}

}
