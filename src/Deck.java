import java.util.ArrayList;

/**
 * A list of cards that could be the unread cards set, the hands,the holding pens, the goal set, the rule set, and the discard pile. 
 * @author Conor Brennan, Becky Thomas, Billy Leete
 *
 */
public class Deck implements GameObject {
	GameObject owner;	// because Deck's are used in many circumstances.
	public ArrayList<Card> deck = new ArrayList<Card>();	// 'Deck' auto creates an empty list
	
	/**
	 * Specify whose Deck
	 * @param creator
	 */
	public Deck(GameObject creator) {
		owner = creator;
	}
	
	/**
	 * blank constructor for Deck
	 */
	public Deck(){
		
	}
	
	/**
	 * returns the game the deck is in
	 */
	public Game getGame() {
		if (owner instanceof Game)
			return (Game)owner;
		else
			return owner.getGame();
	}
	

	/**
	 * returns the deck
	 * @return
	 */
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
	 * @param reciever - the deck receiving the card
	 * @param num - the number of cards to be drawn
	 */
	public void drawCard(Deck receiver, int num){
		for (int i = 0; i < num; i++) {
			Card drawn = deck.remove(0);//takes a card from the deck
			receiver.addCard(drawn);//puts card in receiving deck
			drawn.location = receiver;
		}//end for
	}//class drawCard
	
	/**
	 * adds card to the deck
	 * @param card - card to be added
	 */
	public void addCard(Card card){
		deck.add(card);//adds the card to the deck
	}//class addCard
	
	/**
	 * adds an array of cards to the deck
	 * @param cards - cards to be added
	 */
	public void addCards(Card[] cards){
		for (int i = 0; i < cards.length; i++) {//iterates through the list of cards
			addCard(cards[i]);//adds each card to the deck
		}//end for
	}//class addCards
	
	/**
	 * removes a card from the deck
	 * @param card - card to be removed
	 */
	public void removeCard(Card card){
		if(deck.indexOf(card) != -1)//ensures the card is in the deck
			deck.remove(card);//removes the card from the deck
	}//class removeCard
	
	/**
	 * removes an array of cards to the deck
	 * @param cards - cards to be removed
	 */
	public void removeCards(Card[] cards){
		for (int i = 0; i < cards.length; i++) {//iterates through the list of cards
			removeCard(cards[i]);//removes each card from the deck
		}//end for
	}//class addCards
	
	/**
	 * searches the deck for a specific card
	 * @param ID - the ID of the desired card
	 * @return card - the card with the desired ID if it is in the deck
	 * 				  if it's not in the deck the function returns null
	 */
	public Card search(int ID){
		Card card = null;// Initializes the card to be returned as null
		for (int i = 0; i < deck.size(); i++) {//iterates through the whole deck until the card is found
			Card drawn = deck.get(i);//gets the card from the deck
			if(drawn.getID() == ID){//checks the ID of the drawn card against the ID of the desired card
				card = drawn;//sets the drawn card as the card to be returned
				break;//exits the for loop
			}//end if
		}//end for
		return card;//returns the card
	}//class search
	
	/**
	 * counts the number of cards in the deck
	 * @return the number of cards in the deck
	 */
	public int count(){
		return deck.size();//returns the number of cards in the deck
	}
	
}

