package tp.examples.swing.mvc.slidepuzzle.views.window;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import tp.examples.swing.mvc.slidepuzzle.control.Controller;
import tp.examples.swing.mvc.slidepuzzle.logic.Board;
import tp.examples.swing.mvc.slidepuzzle.logic.Observable;
import tp.examples.swing.mvc.slidepuzzle.logic.SlidePuzzleObserver;

@SuppressWarnings("serial")
public class MainWindow3 extends JFrame implements SlidePuzzleObserver {

	private Observable<SlidePuzzleObserver> game;
	private Controller ctrl;

	public MainWindow3(Controller ctrl, Observable<SlidePuzzleObserver> game) {
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
		JPanel ctrlPanel  = new CtrlPanel(ctrl,game);
		JPanel statusBarPanel   = new StatusBarPanel(ctrl,game);

		mainPanel.add(boardPanel, BorderLayout.CENTER);
		mainPanel.add(ctrlPanel, BorderLayout.LINE_END);
		mainPanel.add(statusBarPanel, BorderLayout.PAGE_END);
	

		this.addWindowListener(new WindowListener() {

			@Override
			public void windowClosing(WindowEvent e) {
				quit();
			}

			@Override
			public void windowOpened(WindowEvent e) {
			}

			@Override
			public void windowIconified(WindowEvent e) {
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
			}

			@Override
			public void windowClosed(WindowEvent e) {
			}

			@Override
			public void windowActivated(WindowEvent e) {
			}
		});

		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.pack();
		this.setVisible(true);

	}

	private void quit() {
		int n = JOptionPane.showOptionDialog(new JFrame(),
				"Are sure you want to quit?", "Quit",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				null, null);

		if (n == 0) {
			ctrl.requestExit();
			System.exit(0);
		}
	}


	private void reportError(String title, String msg) {
		JOptionPane.showMessageDialog(new JFrame(), msg, title,
				JOptionPane.ERROR_MESSAGE);

	}

	@Override
	public void onGameStart(Board board) {
	}

	@Override
	public void onMove(Board board, int srcRow, int srcCol, int trgtRow, int trgtCol) {
	}

	@Override
	public void onError(String msg) {
		reportError("Error", msg);
	}

	@Override
	public void onGameOver(Board board) {
	}

}
