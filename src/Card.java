import java.awt.image.BufferedImage;

/**
 * 
 * @author Rebecca Thomas, Conor Brennan, Billy Leete
 *
 */
public abstract class Card {
	String title;//the cards title
	BufferedImage picture;//the picture of the card
	String description;//a short description of the card
	int place, ID;//a unique Id for the card and a designation of it's place on the board
	
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
	
	/**
	 * 
	 * @param pl - the player 
	 * @param b
	 */
	public abstract void playCard(Player pl, Board b);
	
}
