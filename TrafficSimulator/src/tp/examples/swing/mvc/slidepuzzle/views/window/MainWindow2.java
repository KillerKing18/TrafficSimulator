package tp.examples.swing.mvc.slidepuzzle.views.window;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import tp.examples.swing.mvc.slidepuzzle.control.Controller;
import tp.examples.swing.mvc.slidepuzzle.logic.Board;
import tp.examples.swing.mvc.slidepuzzle.logic.Observable;
import tp.examples.swing.mvc.slidepuzzle.logic.SlidePuzzleObserver;

@SuppressWarnings("serial")
public class MainWindow2 extends JFrame implements SlidePuzzleObserver {

	private Observable<SlidePuzzleObserver> game;
	private Controller ctrl;

	public MainWindow2(Controller ctrl, Observable<SlidePuzzleObserver> game) {
		super("Slide Puzzle!");
		this.game = game;
		this.ctrl = ctrl;
		initGUI();
		game.addObserver(this);
	}
	
	private void initGUI() {

		JPanel mainPanel = new JPanel( new BorderLayout() );
		this.setContentPane(mainPanel);

		JPanel boardPanel = new BoardPanel(ctrl,game);

		mainPanel.add(boardPanel, BorderLayout.CENTER);
	
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.pack();
		this.setVisible(true);

	}

	@Override
	public void onGameStart(Board board) {
	}

	@Override
	public void onMove(Board board, int srcRow, int srcCol, int trgtRow, int trgtCol) {
	}

	@Override
	public void onError(String msg) {
		JOptionPane.showMessageDialog(new JFrame(), msg, "ERROR",
				JOptionPane.ERROR_MESSAGE);
	}

	@Override
	public void onGameOver(Board board) {
	}

}
