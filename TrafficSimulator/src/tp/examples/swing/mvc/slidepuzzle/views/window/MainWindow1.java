package tp.examples.swing.mvc.slidepuzzle.views.window;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import tp.examples.swing.mvc.slidepuzzle.logic.Board;
import tp.examples.swing.mvc.slidepuzzle.logic.Observable;
import tp.examples.swing.mvc.slidepuzzle.logic.SlidePuzzleObserver;

@SuppressWarnings("serial")
public class MainWindow1 extends JFrame implements SlidePuzzleObserver {

	private JTextArea txtArea;

	public MainWindow1(Observable<SlidePuzzleObserver> game) {
		super("Slide Puzzle!");
		initGUI();
		game.addObserver(this);
	}
	
	private void initGUI() {

		txtArea = new JTextArea(10,10);
		this.setContentPane(txtArea);
		txtArea.setEditable(false);
		txtArea.setFont(new Font("Courier", Font.PLAIN, 16));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);

	}

	@Override
	public void onGameStart(Board board) {
		txtArea.setText(board.toString());
	}

	@Override
	public void onMove(Board board, int srcRow, int srcCol, int trgtRow, int trgtCol) {
		txtArea.setText(board.toString());
	}

	@Override
	public void onError(String msg) {
	}

	@Override
	public void onGameOver(Board board) {
	}

}
