/**
 * Requires access to the Game it belongs to.
 * @author William Leete
 *
 */
// kind of needed this hehe - it connects you to the Game!
public interface GameObject {
	public Game getGame();	
} // basically it ensures that it belongs to a specifiable Game
