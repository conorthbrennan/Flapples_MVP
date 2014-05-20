import java.awt.image.BufferedImage;

/**
 * The basic abstract class for Card.
 * @author Rebecca Thomas, Conor Brennan, Billy Leete
 *
 */
public abstract class Card implements GameObject {
	Game g;
	String title;//the cards title
	BufferedImage picture;//the picture of the card
	String description;//a short description of the card
	int ID;//a unique Id for the card
	Deck location;//a designation of it's place on the board
	
	
	/**
	 * gets the game the card is in
	 */
	public Game getGame() { return g; }
	
	/**
	 * gets the card's title
	 * @return the cards title
	 */
	public String getTitle() {
		return title;
	}//end getTitle()
	
	/**
	 * sets the cards title
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}//end setTitle()
	
	/**
	 * gets the card's picture
	 * @return the picture attached to the card
	 */
	public BufferedImage getPicture() {
		return picture;
	}//end getPicture()
	
	/**
	 * sets the picture
	 * @param picture
	 */
	public void setPicture(BufferedImage picture) {
		this.picture = picture;
	}//end setPicture
	
	/**
	 * get the description
	 * @return
	 */
	public String getDescription() {
		return description;
	}//end getDescription
	
	/**
	 * sets the description
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}//end setDescription
	
	/**
	 * gets the ID
	 * @return
	 */
	public int getID() {
		return ID;
	}//end getID
	
	/**
	 * sets the ID
	 * @param iD
	 */
	public void setID(int iD) {
		ID = iD;
	}//end setID
	
	/**
	 * gets the Location
	 * @return
	 */
	public Deck getLocation(){
		return location;
	}//end getLocation
	
	/**
	 * sets the location
	 * @param locate
	 */
	public void setLocation(Deck locate){
		location = locate;
	}//end setLocation
	
	/**
	 * plays the card
	 * @param pl - the player
	 * @param b - the board it is being played on
	 */
	public abstract void playCard(Player pl, Board b);
	
	/**
	 * the primary constructor for the Cards
	 * @param titl
	 * @param pic
	 * @param descrip
	 * @param id
	 * @param locate
	 */
	public Card(String titl, BufferedImage pic, String descrip, int id, Deck locate)
	{
		title = titl;//sets the title
		picture = pic;//the picture
		description = descrip;//the description
		location = locate;//the location
		ID = id;//and the ID
		
	}//end constructor

	/**
	 * creates a non-card(never used)
	 */
	public Card() {
		//blankety blank card
		title="Errorzzz";//gives it a title
		description = "The blank card";//and a descrption
	}//end constructor
}//end class
