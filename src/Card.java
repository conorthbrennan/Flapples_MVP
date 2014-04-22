import java.awt.image.BufferedImage;

/**
 * The basic abstract class for Card.
 * @author Rebecca Thomas, Conor Brennan, Billy Leete
 *
 */
public abstract class Card implements GameObject {
	Game g;
	private String title;//the cards title
	private BufferedImage picture;//the picture of the card
	String description;//a short description of the card
	int ID;//a unique Id for the card
	Deck location;//a designation of it's place on the board
	
	
	public Game getGame() { return g; }
		/**
		 * 
		 * @return the cards title
		 */
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public BufferedImage getPicture() {
		return picture;
	}
	
	public void setPicture(BufferedImage picture) {
		this.picture = picture;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getID() {
		return ID;
	}
	
	public void setID(int iD) {
		ID = iD;
	}
	
	public Deck getLocation(){
		return location;
	}
	
	public void setLocation(Deck locate){
		location = locate;
	}
	
	public abstract void playCard(Player pl, Board b);
	
	public Card(String titl, BufferedImage pic, String descrip, int id, Deck locate)
	{
		title = titl;
		picture = pic;
		description =descrip;
		location = locate;
		ID =id;
		
	}

	public Card() {
		//blankety blank card
		title="Errorzzz";
		description = "The blank card";
		
	}
}
