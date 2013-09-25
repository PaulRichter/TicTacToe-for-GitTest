package ticTacToe;

import observerPattern.Observable.Player;

/**
 * Represents a playing piece in a game of tictactoe. Every piece has an
 * owner - a player.
 */
public class TicTacToeFigure {
	private Player owner;

	public TicTacToeFigure(Player p) {
		owner = p;
	}

	public void setOwner(Player p) {
		owner = p;
	}

	public Player getOwner() {
		return owner;
	}
}
