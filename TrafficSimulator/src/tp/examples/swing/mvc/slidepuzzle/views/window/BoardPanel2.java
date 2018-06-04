package tp.examples.swing.mvc.slidepuzzle.views.window;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import tp.examples.swing.mvc.slidepuzzle.control.Controller;
import tp.examples.swing.mvc.slidepuzzle.logic.Board;
import tp.examples.swing.mvc.slidepuzzle.logic.Observable;
import tp.examples.swing.mvc.slidepuzzle.logic.SlidePuzzleObserver;

@SuppressWarnings("serial")
public class BoardPanel2 extends JPanel implements SlidePuzzleObserver {

	private JButton[][] buttons;
	private GridBagConstraints c;
	private boolean active;
	private Controller ctrl;

	public BoardPanel2(Controller ctrl, Observable<SlidePuzzleObserver> game) {
		this.ctrl = ctrl;
		initGUI();
		game.addObserver(this);
	}

	private void initGUI() {
		this.setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		this.setPreferredSize(new Dimension(400, 200));
	}

	@Override
	public void onGameStart(Board board) {
		int rows = board.getWidth();
		int cols = board.getHeight();
		buttons = new JButton[rows][cols];
		this.removeAll();

		for (int i = 0; i < rows; i++)
			for (int j = 0; j < cols; j++) {
				int v = board.getPosition(i + 1, j + 1);
				buttons[i][j] = createButton(i, j, v);
				if ( v == 0 ) buttons[i][j].setEnabled(false);
				c.gridy = i;
				c.gridx = j;
				this.add(buttons[i][j], c);
			}
		this.revalidate();
		active = true;
	}

	private JButton createButton(final int i, final int j, int v) {
		JButton x = new JButton(v == 0 ? "" : v + "");
		x.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (active)
					ctrl.move(i + 1, j + 1);
			}
		});
		return x;
	}

	@Override
	public void onMove(Board board, int srcRow, int srcCol,
			int trgtRow, int trgtCol) {
		String x1 = buttons[srcRow - 1][srcCol - 1].getText();
		String x2 = buttons[trgtRow - 1][trgtCol - 1].getText();
		buttons[srcRow - 1][srcCol - 1].setText(x2);
		buttons[trgtRow - 1][trgtCol - 1].setText(x1);
		buttons[srcRow - 1][srcCol - 1].setEnabled(false);
		buttons[trgtRow - 1][trgtCol - 1].setEnabled(true);

	}

	@Override
	public void onError(String msg) {
	}

	@Override
	public void onGameOver(Board board) {
		active = false;
	}

}
