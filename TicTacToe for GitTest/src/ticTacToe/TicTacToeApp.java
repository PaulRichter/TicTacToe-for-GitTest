package ticTacToe;

/**
 * Klasse , die Modell, Controller und Views erzeugt und Spiel startet. 
 */
public class TicTacToeApp {
	
	public static void main(String[] args) {
		TicTacToeModel model = new TicTacToeModel();
		TicTacToeController controller = new TicTacToeController(model);
		TicTacToeView view = new TicTacToeView(model, controller);
		view.setVisible(true);
	}
}
