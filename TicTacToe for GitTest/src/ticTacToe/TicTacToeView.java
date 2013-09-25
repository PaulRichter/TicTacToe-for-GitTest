package ticTacToe;

import javax.swing.*;

import observerPattern.Observer;
import observerPattern.Observable.Player;

import java.awt.*;
import java.awt.event.*;

/**
 * The view - the GUI via which we interact with the model.
 */
@SuppressWarnings( { "serial" })
public class TicTacToeView extends JFrame implements Observer {
	// Class members
	protected TicTacToeModel model;
	private TicTacToeController controller;

	/**
	 * The board is represented as an array of buttons to ease receiving
	 * moves.
	 */
	protected JButton[][] mBtns;
	
	public TicTacToeView(TicTacToeModel model,
			TicTacToeController controller) {
		this.model = model;
		this.controller = controller;
		viewInit();
		callbackInit();
		setSize(300, 300);
		setTitle("TicTacToe");
		model.addObserver(this);
	}

	// public interface
	public void won(Player i) {
		JOptionPane.showMessageDialog(this, "Player " + i.ordinal()
				+ " won!",

		"Game Over", JOptionPane.INFORMATION_MESSAGE);

	}

	public void tie() {
		JOptionPane.showMessageDialog(this, "Tie - Nobody wins",

		"Game Over", JOptionPane.INFORMATION_MESSAGE);

	}

	public void changed(int row, int col) {
		mBtns[row][col].setText(model.getOwnerOfFigureOnPlayingField(row,
				col).name());
	}

	public void reset() {
		int iR, iC;
		for (iR = 0; iR < 3; iR++)
			for (iC = 0; iC < 3; iC++)
				mBtns[iR][iC].setText("");
	}

	public JButton getBtn(int row, int column) {
		return mBtns[row][column];
	}

	// private methods
	/**
	 * Attach elements to the window.
	 */
	private void viewInit() {
		int iRow; // button row
		int iCol; // button column
		Container pane; // content pane

		// get content pane, set to grid layout

		pane = getContentPane();
		pane.setLayout(new GridLayout(3, 3));

		// create and attach buttons

		mBtns = new JButton[3][3];
		for (iRow = 0; iRow < 3; iRow++) {
			for (iCol = 0; iCol < 3; iCol++) {
				mBtns[iRow][iCol] = new JButton();
				pane.add(mBtns[iRow][iCol]);
			}
		}
	}

	/**
	 * Setup callback methods.
	 */
	private void callbackInit() {
		int iR; // row and column loop indices
		int iC;

		for (iR = 0; iR < 3; iR++) {
			for (iC = 0; iC < 3; iC++) {
				final int row = iR;
				final int col = iC;
				mBtns[iR][iC].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						controller.playerMove(row, col);
					}
				});
			}
		}
	}

	// Overridden so we can exit when window is closed
	protected void processWindowEvent(WindowEvent e) {
		super.processWindowEvent(e);
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			System.exit(0);
		}
	}

}