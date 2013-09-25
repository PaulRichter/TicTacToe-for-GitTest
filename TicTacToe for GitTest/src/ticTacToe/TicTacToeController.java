package ticTacToe;
 
import observerPattern.Observable.Event;

/**
 * The controller manipulates the model according to the specified rules of
 * the game. It also responds to interaction from the user via the view.
 */
public class TicTacToeController {
	// Class members
	protected TicTacToeModel model;
	// Constructor
	public TicTacToeController(TicTacToeModel model) {
		this.model = model;
	}

	// public interface

	/**
	 * Checks if the move is possible - if it is, it checks if the game
	 * isn't over yet - if it isn't, it executes the move and lets the
	 * computer play, switching players accordingly.
	 * 
	 * @param row
	 *            Row of the field one wants to move to.
	 * @param col
	 *            Column of the field one wants to move to.
	 */
	public void playerMove(int row, int col) {
		if (model.makeMove(row, col)) {
			checkState();
			model.nextPlayer();
			model.computerMove();
			checkState();
			model.nextPlayer();
		}
	}

	// private methods
	/**
	 * Auxilliary method used to check if the game should be terminated or
	 * not. In case of a win or a tie, the model is reset and the notify
	 * method is called to inform observers.
	 */
	protected void checkState() {
		if (model.isWin()) {
			model.notifyEvent(Event.WIN);
			model.reset();
			model.notifyEvent(Event.RESET);
		} else if (model.isFull()) {
			model.notifyEvent(Event.TIE);
			model.reset();
			model.notifyEvent(Event.RESET);
		}
	}

}
