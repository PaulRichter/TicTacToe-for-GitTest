package observerPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * Defines the interface of an object that can be observed. Methods for
 * handling observers are implemented.
 */

public abstract class Observable {

	/**
	 * All Observers are stored in a list. Note that the Observer-interface
	 * is used here so that the observable object does not need to know the
	 * observers intimately.
	 */
	private List<Observer> observers = new ArrayList<Observer>();

	/**
	 * The Player, whose turn it is.
	 */
	protected Player currentPlayer;

	/**
	 * A player in tic-tac-toe can either be represented by an X or an O,
	 * and no player by NOBODY.
	 */
	public enum Player {
		NOBODY, X, O
	};

	/**
	 * Possible events during the game. Used to improve the readability of
	 * the notifyEvent() method.
	 */
	public enum Event {
		WIN, RESET, TIE
	};

	public void addObserver(Observer o) {
		observers.add(o);
	}

	public void removeObserver(Observer o) {
		observers.remove(o);
	}

	/**
	 * Informs observers of the change to field [row,col]
	 * 
	 * @param row
	 *            Row of the changed field.
	 * @param col
	 *            Column of the changed field.
	 */
	public void notifyObservers(int row, int col) {
		for (Observer o : observers) {
			o.changed(row, col);
		}
	}

	/**
	 * Informs observers of a certain event.
	 * 
	 * @param e
	 *            The event - can be a win, tie or a reset.
	 */
	public void notifyEvent(Event e) {
		switch (e) {
		case WIN:
			for (Observer o : observers) {
				o.won(currentPlayer);
			}
			break;
		case RESET:
			for (Observer o : observers) {
				o.reset();
			}
			break;
		case TIE:
			for (Observer o : observers) {
				o.tie();
			}
			break;
		}
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}
}
