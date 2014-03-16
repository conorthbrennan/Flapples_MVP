import java.util.ArrayList;

/**
 * 
 * @author Conor Brennan, Becky Thomas, Billy Leete
 *
 */
public class Deck implements GameObject {
	GameObject owner;	// because Deck's are used in many circumstances.
	private ArrayList<Card> deck = new ArrayList<Card>();	// 'Deck' auto creates an empty list
	
	/**
	 * Specify whose Deck!
	 * @param creator
	 */
	public Deck(GameObject creator) {
		owner = creator;
	}
	public Game getGame() {
		if (owner instanceof Game)
			return (Game)owner;
		else
			return owner.getGame();
	}
	
	public ArrayList<Card> getDeck(){	// intended to be used by sub-classes?
		return deck;
	}
	
	/**
	 * shuffles the deck
	 */
	public void shuffle(){
		
		ArrayList<Integer> drawn = new ArrayList<Integer>();//Array list to keep track of which cards have been shuffled
		ArrayList<Card> shuffledDeck = new ArrayList<Card>();//Array list to receive shuffled cards
		boolean check;//boolean used to determine if a card has already been shuffled
		
		for (int i = 0; i < deck.size(); i++) {//iterates through all of the deck and selects a random card to add to the shuffled deck
			int drawing = -1;
			
			check = true;//Initializes check to run the loop
			while(check){//runs until drawing refers to a card that hasn't been drawn yet
				drawing = (int)(Math.random() * deck.size());//sets drawing to an index between 0 and the size of the deck
				if(!drawn.contains(drawing))//exits the loop if the card has not already been drawn
					check = false;
			}//end while
			drawn.add(drawing);//notes that drawing has been drawn for the next iteration
			
			Card card = deck.get(drawing);//the random card to be put in the shuffled deck
			shuffledDeck.add(card);//adds the card to the new deck
		}//end for
		
		deck = shuffledDeck;//sets the class's deck
	}//class shuffle
	
	/**
	 * prints out the titles of all the cards in the deck
	 */
	public void lookAt(){
		for (int i = 0; i < deck.size(); i++) {//iterates through every card in the deck in order
			Card card = deck.get(i);//looks at the next card
			System.out.println(card.getTitle());//gets and prints the card's title
		}//end for
	}//class lookAt
	
	/**
	 * Takes a card from a deck and places it in another
	 */
	public void drawCard(Deck receiver, int num)	// [wgl] Hey! we don't need iteration here, do we?
	{		// [wgl] can you withdraw a *specific* card? Like, by its 'place' or 'ID'?
		ArrayList<Card> receiverDeck = receiver.getDeck();//gets the deck list from the receiving deck
		for (int i = 0; i < num; i++) {
			Card drawn = deck.remove(0);//takes a card from the deck
			receiverDeck.add(drawn);//puts card in receiving deck
		}//end for
	}//class drawCard
	
	/**
	 * adds card to a deck
	 * @param card - card to be added
	 */
	public void addCard(Card card){
		deck.add(card);
	}
	
}

