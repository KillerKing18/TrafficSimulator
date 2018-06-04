package tp.examples.swing.mvc.slidepuzzle.views.console;

import tp.examples.swing.mvc.slidepuzzle.logic.Board;
import tp.examples.swing.mvc.slidepuzzle.logic.Observable;
import tp.examples.swing.mvc.slidepuzzle.logic.SlidePuzzleObserver;

public class ConsoleView implements SlidePuzzleObserver {

	public ConsoleView(Observable<SlidePuzzleObserver> game) {
		game.addObserver(this);
	}

	@Override
	public void onGameStart(Board board) {
		System.out.println(board);
	}

	@Override
	public void onMove(Board board, int srcRow, int srcCol, int trgtRow, int trgtCol) {
		System.out.println(board);
	}

	@Override
	public void onError(String msg) {
		System.err.println(msg);
		System.err.flush();
	}

	@Override
	public void onGameOver(Board board) {
		System.out.println("Game over!");
	}

}
