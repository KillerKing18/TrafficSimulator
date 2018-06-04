package tp.examples.swing.mvc.slidepuzzle.views.log;

import java.io.FileWriter;
import java.io.IOException;

import tp.examples.swing.mvc.slidepuzzle.logic.Board;
import tp.examples.swing.mvc.slidepuzzle.logic.Observable;
import tp.examples.swing.mvc.slidepuzzle.logic.SlidePuzzleObserver;

public class LogView implements SlidePuzzleObserver {

	private String fname;

	public LogView(Observable<SlidePuzzleObserver> game, String fname) {
		this.fname = fname;
		game.addObserver(this);
	}

	private void log(String s) {
		try {
			FileWriter fw = new FileWriter(fname, true);
			fw.write(s);
			fw.write("\n");
			fw.close();
		} catch (IOException e) {
		}
	}

	@Override
	public void onGameStart(Board board) {
		log(board.toString());
	}

	@Override
	public void onMove(Board board, int srcRow, int srcCol,
			int trgtRow, int trgtCol) {
		log(board.toString());
		log("Moved (" + srcRow + "," + srcCol + ") to (" + trgtRow
				+ "," + trgtCol + ")");
	}

	@Override
	public void onError(String msg) {
	}

	@Override
	public void onGameOver(Board board) {
		log("Game over!");
	}
}
