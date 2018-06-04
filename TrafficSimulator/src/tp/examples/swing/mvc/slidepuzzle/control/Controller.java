package tp.examples.swing.mvc.slidepuzzle.control;

import tp.examples.swing.mvc.slidepuzzle.logic.GameError;
import tp.examples.swing.mvc.slidepuzzle.logic.SlidePuzzle;

abstract public class Controller {
	boolean exit;
	SlidePuzzle game;

	public Controller(SlidePuzzle game) {
		this.game = game;
		exit = false;
	}

	abstract public void run(); 

	public void move(int row, int col) {
		try {
			game.move(row, col);
		} catch ( GameError e) {
			
		}
	}

	public void requestExit() {
		exit = true;
	}

	public void reset(int rows, int cols) {
		game.reset(rows, cols);
	}
}
