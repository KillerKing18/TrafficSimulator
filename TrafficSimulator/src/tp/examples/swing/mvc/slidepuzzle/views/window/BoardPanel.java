package tp.examples.swing.mvc.slidepuzzle.views.window;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import tp.examples.swing.mvc.slidepuzzle.control.Controller;
import tp.examples.swing.mvc.slidepuzzle.logic.Board;
import tp.examples.swing.mvc.slidepuzzle.logic.Observable;
import tp.examples.swing.mvc.slidepuzzle.logic.SlidePuzzleObserver;

@SuppressWarnings("serial")
public class BoardPanel extends JPanel implements SlidePuzzleObserver {

	private JTextArea txtArea;
	private JTextField rowTxt;
	private JTextField colTxt;
	private JButton moveButton;
	private Controller ctrl;

	public BoardPanel(Controller ctrl, Observable<SlidePuzzleObserver> game) {
		this.ctrl = ctrl;
		initGUI();
		game.addObserver(this);
	}

	private void initGUI() {
		this.setLayout( new BorderLayout() );
		txtArea = new JTextArea(10,20);
		txtArea.setEditable(false);
		txtArea.setFont(new Font("Courier", Font.PLAIN, 16));
		JScrollPane area = new JScrollPane(txtArea,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.add(area, BorderLayout.CENTER);
		
		JPanel playPanel = new JPanel();
		
		rowTxt = new JTextField(3);
		colTxt = new JTextField(4);
		moveButton = new JButton("Move");
		moveButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int row = Integer.parseInt(rowTxt.getText());
					int col = Integer.parseInt(colTxt.getText());
					ctrl.move(row, col);
				} catch ( NumberFormatException _e ) {	
				}
			}
		});
		
		playPanel.add( new JLabel( "row") );
		playPanel.add( rowTxt );
		playPanel.add( new JLabel( "col") );
		playPanel.add( colTxt );
		playPanel.add( moveButton);
		this.add(playPanel, BorderLayout.PAGE_END);

		this.setPreferredSize( new Dimension(400,200));
	}

	@Override
	public void onGameStart(Board board) {
		txtArea.setText(board.toString());
		moveButton.setEnabled(true);
		rowTxt.setEnabled(true);
		colTxt.setEnabled(true);
	}

	@Override
	public void onMove(Board board, int srcRow, int srcCol, int trgtRow, int trgtCol) {
		txtArea.setText(board.toString());
		rowTxt.setText("");
		colTxt.setText("");

	}

	@Override
	public void onError(String msg) {		
	}

	@Override
	public void onGameOver(Board board) {
		moveButton.setEnabled(false);
		rowTxt.setEnabled(false);
		colTxt.setEnabled(false);
	}

}
