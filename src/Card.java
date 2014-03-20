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
	int place, ID;//a unique Id for the card and a designation of it's place on the board
	Deck location;
	//yes
	
	public Game getGame() { return g; }
		
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
	public int getPlace() {
		return place;
	}
	public void setPlace(int place) {
		this.place = place;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	
	public abstract void playCard(Player pl, Board b);
	
	public Card(String titl, BufferedImage pic, String descrip, int id)
	{
		title = titl;
		picture = pic;
		description =descrip;
		place = 0;//aka the main deck
		ID =id;
		
	}

	public Card() {
		//blankety blank card
		title="Errorzzz";
		description = "The blank card";
		place = 0;
		
	}
}
