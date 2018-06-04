package tp.examples.swing.mvc.slidepuzzle.views.window;

import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tp.examples.swing.mvc.slidepuzzle.control.Controller;
import tp.examples.swing.mvc.slidepuzzle.logic.Board;
import tp.examples.swing.mvc.slidepuzzle.logic.Observable;
import tp.examples.swing.mvc.slidepuzzle.logic.SlidePuzzleObserver;

@SuppressWarnings("serial")
public class StatusBarPanel extends JPanel implements SlidePuzzleObserver {
	
	private JLabel txt;
	
	public StatusBarPanel(Controller ctrl, Observable<SlidePuzzleObserver> game) {
		initGUI();
		game.addObserver(this);
	}

	private void initGUI() {
		this.setLayout( new FlowLayout(FlowLayout.LEFT) );
		txt = new JLabel();
		this.add( txt );
		this.setBorder( BorderFactory.createBevelBorder( 1 ));
	}

	private void setMsg(String msg) {
		txt.setText(msg);
	}

	@Override
	public void onGameStart(Board board) {
		setMsg("Game Started!");
	}


	@Override
	public void onMove(Board board, int srcRow, int srcCol, int trgtRow, int trgtCol) {
		setMsg("Last move: slided ("+srcRow+","+srcCol+") to ("+trgtRow+","+trgtCol+")");		
	}

	@Override
	public void onError(String msg) {
	}

	@Override
	public void onGameOver(Board board) {
		setMsg("Game Over!");
	}

}
