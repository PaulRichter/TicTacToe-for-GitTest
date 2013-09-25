import org.junit.BeforeClass;
import org.junit.Test;

import ticTacToe.TicTacToeController;
import ticTacToe.TicTacToeModel;
import ticTacToe.TicTacToeView;
import static observerPattern.Observable.Player.*;
import static org.junit.Assert.*;


public class TestForView {

	private static TicTacToeModel model;

	private static TicTacToeView view;

	private static TicTacToeController controller;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		model = new TicTacToeModel();
		controller = new TicTacToeController(model);
		view = new TicTacToeView(model, controller);
	}


	@Test
	public void simulateClick() throws Exception {
		int testRow = 0, testColumn = 0;

		view.getBtn(testRow, testColumn).getActionListeners()[0]
				.actionPerformed(null);

		assertTrue(model.getPlayingField(testRow,
				testColumn).isPlayer(X));
				//is(True);

		view.getBtn(testRow, testColumn).getActionListeners()[0]
				.actionPerformed(null);

		assertTrue(model.getPlayingField(testRow,
				testColumn).isPlayer(X));

		view.getBtn(testRow, testColumn + 1).getActionListeners()[0]
				.actionPerformed(null);

		assertTrue(model.getPlayingField(0, 1)
				.isPlayer(O));
	}
}
