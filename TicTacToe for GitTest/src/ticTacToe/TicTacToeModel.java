package ticTacToe;

import observerPattern.Observable;

/**
 * The Model consists of a board, playing fields and figures. Logic for
 * moving, changing the current player and letting the computer make a move
 * is implemented as well.
 */
public class TicTacToeModel extends Observable {
	// Class members
	/**
	 * The gameboard is an array of squares - of playing fields.
	 */
	private TicTacToePlayingField[][] gameBoard;

	private final static int ROWS = 3;
	private final static int COLS = 3;
	
	private int freePlayingFields;

	// Constructor
	public TicTacToeModel() {
		gameBoard = new TicTacToePlayingField[ROWS][COLS]; // instantiate
														// array
		reset();
	}

	// Public interface
	public boolean isFull() {
		return freePlayingFields == 0;
	}

	/**
	 * Initialises the board - making it "empty", setting by convention the
	 * first player to X, and setting the number of free fields.
	 */
	public void reset() {
		int iRow; // row loop counter
		int iCol; // column loop counter
		for (iRow = 0; iRow < ROWS; iRow++) {
			for (iCol = 0; iCol < COLS; iCol++) {
				gameBoard[iRow][iCol] = new TicTacToePlayingField(); 
			}
		}
		currentPlayer = Player.X;
		freePlayingFields = ROWS*COLS;

	}

	public void nextPlayer() {
		switch (currentPlayer) {
		case X:
			currentPlayer = Player.O;
			break;
		default:
			currentPlayer = Player.X;
		}
	}

	public TicTacToePlayingField getPlayingField(int iRow, int iCol) {
		return gameBoard[iRow][iCol];
	}

	public Player getOwnerOfFigureOnPlayingField(int row, int col) {
		return getPlayingField(row, col).state.getOwner();
	}

	public boolean makeMove(int iRow, int iCol) {

		if (gameBoard[iRow][iCol].isFree()) {
			gameBoard[iRow][iCol].setFigure(new TicTacToeFigure(
					currentPlayer));
			freePlayingFields--;
			notifyObservers(iRow, iCol);
			return true;
		}
		return false;

	}

	/**
	 * have the program move the next piece (currently find the next open
	 * spot)
	 * 
	 * @return false if no moves are possible, true otherwise
	 */
	public boolean computerMove() {
		int iRow; // row loop index
		int iCol; // column loop index

		// loop to find an open position

		for (iRow = 0; iRow < ROWS; iRow++) {
			for (iCol = 0; iCol < COLS; iCol++) {
				if (gameBoard[iRow][iCol].isFree()) {
					makeMove(iRow, iCol);
					return true;
				}
			}
		}
		// no moves are left, the board is full

		return false;
	}

	/**
	 * is there a win yet?
	 * 
	 * @return true if there is a win
	 */
	public boolean isWin() {
		int j; // loop counter

		for (Player i : Player.values()) {
			// check for three across
			if (i != Player.NOBODY) {
				for (j = 0; j <= 2; j++) {
					if (gameBoard[j][0].isPlayer(i)
							&& gameBoard[j][1].isPlayer(i)
							&& gameBoard[j][2].isPlayer(i)) {
						return true;
					}
				}

				// check for three down

				for (j = 0; j <= 2; j++) {
					if (gameBoard[0][j].isPlayer(i)
							&& gameBoard[1][j].isPlayer(i)
							&& gameBoard[2][j].isPlayer(i)) {
						return true;
					}
				}

				// check diagonal \

				if (gameBoard[0][0].isPlayer(i)
						&& gameBoard[1][1].isPlayer(i)
						&& gameBoard[2][2].isPlayer(i)) {
					return true;
				}

				// check diagonal /

				if (gameBoard[2][0].isPlayer(i)
						&& gameBoard[1][1].isPlayer(i)
						&& gameBoard[0][2].isPlayer(i)) {
					return true;
				}

			}

		}
		// if we get here, neither side has won
		return false;
	}

	public int getCOLS() {
		return COLS;
	}

	public int getROWS() {
		return ROWS;
	}
}