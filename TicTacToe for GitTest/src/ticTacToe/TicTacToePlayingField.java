package ticTacToe;

import observerPattern.Observable.Player;

/**
 * Represents a square on the board. Each square has a state which - in the
 * case of tictactoe - is simply one figure.
 */
public class TicTacToePlayingField {
	TicTacToeFigure state;

	/**
	 * A playing field is initially free- i.e. has a figure that belongs to
	 * nobody.
	 */
	public TicTacToePlayingField() {
		state = new TicTacToeFigure(Player.NOBODY);
	}

	public boolean isFree() {
		return state.getOwner() == Player.NOBODY;
	}

	public boolean isPlayer(Player p) {
		return state.getOwner() == p;
	}

	public void setFigure(TicTacToeFigure p) {
		state = p;
	}
}