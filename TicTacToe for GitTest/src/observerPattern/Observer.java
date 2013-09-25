package observerPattern;
import observerPattern.Observable.Player;

/** 
 * Interface for defining the events an Observer could be interested in.
 */
public interface Observer {
	public void changed(int row, int col);

	public void won(Player player);

	public void tie();

	public void reset();
}
