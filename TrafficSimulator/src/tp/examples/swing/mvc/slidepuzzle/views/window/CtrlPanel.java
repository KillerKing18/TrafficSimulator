package tp.examples.swing.mvc.slidepuzzle.views.window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import tp.examples.swing.mvc.slidepuzzle.control.Controller;
import tp.examples.swing.mvc.slidepuzzle.logic.Board;
import tp.examples.swing.mvc.slidepuzzle.logic.Observable;
import tp.examples.swing.mvc.slidepuzzle.logic.SlidePuzzleObserver;

@SuppressWarnings("serial")
public class CtrlPanel extends JPanel implements SlidePuzzleObserver {

	private Controller ctrl;
	private JTextField rowsTxt;
	private JTextField colsTxt;
	private JButton resetButton;
	private JButton quitButton;

	public CtrlPanel(Controller ctrl, Observable<SlidePuzzleObserver> game) {
		this.ctrl = ctrl;
		initGUI();
		game.addObserver(this);
	}

	private void initGUI() {
		this.setLayout( new BoxLayout(this, BoxLayout.Y_AXIS));
		

		JPanel resetPanel = new JPanel();

		rowsTxt = new JTextField(3);
		colsTxt = new JTextField(4);
		resetButton = new JButton("Reset");
		resetButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int rows = Integer.parseInt(rowsTxt.getText());
					int cols = Integer.parseInt(colsTxt.getText());
					ctrl.reset(rows, cols);
				} catch ( NumberFormatException e1 ) {
					
				}
			}
		});

		
		resetPanel.add( new JLabel( "rows") );
		resetPanel.add( rowsTxt );
		resetPanel.add( new JLabel( "cols") );
		resetPanel.add( colsTxt );
		resetPanel.add( resetButton);

		quitButton = new JButton("Quit");
		quitButton.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				quit();
			}
		});

		this.add( new JSeparator(JSeparator.HORIZONTAL));
		this.add( resetPanel );
		this.add( new JSeparator(JSeparator.HORIZONTAL));
		this.add( quitButton );

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

	@Override
	public void onGameStart(Board board) {
	}

	@Override
	public void onMove(Board board, int srcRow, int srcCol, int trgtRow, int trgtCol) {
	}

	@Override
	public void onError(String msg) {
	}

	@Override
	public void onGameOver(Board board) {
	}

}
