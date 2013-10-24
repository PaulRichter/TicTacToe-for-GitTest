import static observerPattern.Observable.Player.NOBODY;
import static observerPattern.Observable.Player.X;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.*;
import observerPattern.Observable.Player;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import ticTacToe.TicTacToeModel;

//hier ist ein Kommentar für Testzwecke
//ein weiterer Kommentar ... 
public class TestForModel {

	private static TicTacToeModel model;

	private static int rows;

	private static int columns;

	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		model = new TicTacToeModel();
		rows = model.getROWS();
		columns = model.getCOLS(); 
	}

	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	
	@Before
	public void setUp() throws Exception {
		model.reset();
	}

	
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void reset() throws Exception {
		//Felder richtig initalisiert?
		Player[][] expected = { 
				{ NOBODY, NOBODY, NOBODY },
				{ NOBODY, NOBODY, NOBODY }, 
				{ NOBODY, NOBODY, NOBODY } };

		checkStateOfModel(expected);

		assertTrue(model.getCurrentPlayer() == Player.X);
	}

	
	@Test
	public void nextPlayer() throws Exception {
		//Nach reset ist Spieler X dran, dann kommt Spieler O
		model.nextPlayer();
		assertTrue(model.getCurrentPlayer() == Player.O);
		model.nextPlayer();
		assertTrue(model.getCurrentPlayer() == Player.X);
	}

	
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void makeMove() throws Exception {
		assertTrue(model.makeMove(0, 0));
		assertTrue(model.makeMove(rows - 1, columns - 1));
		assertTrue(model.makeMove(0, columns - 1));
		assertTrue(model.makeMove(rows - 1, 0));

		Player[][] expected = { 
				{ X		, NOBODY, X 	},
				{ NOBODY, NOBODY, NOBODY}, 
				{ X		, NOBODY, X 	} };

		checkStateOfModel(expected);
		

		assertFalse(model.makeMove(0, 0));

		model.makeMove(rows, columns);
	}

	
	@Test
	public void computerMove() throws Exception {
		for (int i = 0; i < rows * columns; i++) {
			assertTrue(model.computerMove());
		}

		assertFalse(model.computerMove());
		
		assertTrue(model.isFull());

		Player[][] expected = { 
				{ X, X, X }, 
				{ X, X, X }, 
				{ X, X, X } };

		checkStateOfModel(expected);
	}

	
	@Test
	public void isFull() throws Exception {
		assertFalse(model.isFull());

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				model.makeMove(i, j);
			}
		}

		assertTrue(model.isFull());
	}

	
	@Ignore
	@Test
	public void isWin() throws Exception {
		assertFalse(model.isWin());

		for (int i = 0; i < rows; i++) {
			model.makeMove(i, i);
		}

		assertTrue(model.isWin());
		model.reset();

		for (int i = 0; i < rows; i++) {
			model.makeMove(i, 0);
		}

		assertTrue(model.isWin());
		model.reset();

		for (int i = 0; i < columns; i++) {
			model.makeMove(0, i);
		}

		assertTrue(model.isWin());
	}

	
	private void checkStateOfModel(Player[][] expected) {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				//assertEquals(expected[i][j], model.getOwnerOfFigureOnPlayingField(i, j));
				assertThat(expected[i][j], is(equalTo(model.getOwnerOfFigureOnPlayingField(i, j))));
			}
		}
	}

}
