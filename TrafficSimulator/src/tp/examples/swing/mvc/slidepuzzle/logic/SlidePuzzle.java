package tp.examples.swing.mvc.slidepuzzle.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SlidePuzzle implements Observable<SlidePuzzleObserver> {

	private Board board;
	private Board roBoard;

	private boolean finished;
	private int rows;
	private int cols;
	private List<SlidePuzzleObserver> obs;

	public SlidePuzzle() {
		this(4, 4);
	}

	public SlidePuzzle(int rows, int cols) {
		this.obs = new ArrayList<SlidePuzzleObserver>();
		reset(rows, cols);
	}

	public void move(int row, int col) {

		// if the game is finished, we cannot make a move
		if (finished) {
			notifyError("Game is finshed already!");
		}

		int i = -1;
		int j = -1;

		// compute destination and empty cell
		if (board.getPosition(row - 1, col) == 0) {
			i = row - 1;
			j = col;
		} else if (board.getPosition(row + 1, col) == 0) {
			i = row + 1;
			j = col;
		} else if (board.getPosition(row, col - 1) == 0) {
			i = row;
			j = col - 1;
		} else if (board.getPosition(row, col + 1) == 0) {
			i = row;
			j = col + 1;
		}
		// if i=-1 and j=-1, then there is no empty cell adjacent to (row,col)
		if (i == -1 || j == -1) {
			notifyError("Invalid move " + "(" + row + "," + col + ")");
		}

		// swap (row,col) with the empty cell (i,j)
		int v = board.getPosition(row, col);
		board.setPosition(row, col, board.getPosition(i, j));
		board.setPosition(i, j, v);

		// notify that the move has been performed
		notifyMove(row, col, i, j);

		// check if the game is over, and notify if so
		if (checkIfFinished()) {
			finished = true;
			notifyGameOver();
		}
	}

	private boolean checkIfFinished() {
		int maxNum = rows * cols;
		int currNum = 1;

		for (int i = 1; i <= rows; i++) {
			for (int j = 1; j <= cols; j++) {
				if (board.getPosition(i, j) != currNum) {
					return false;
				}
				currNum = (currNum + 1) % maxNum;
			}
		}
		return true;
	}

	public boolean isFinished() {
		return finished;
	}

	public int getRows() {
		return rows;
	}

	public int getColss() {
		return cols;
	}

	public void reset(int rows, int cols) {
		rows = Math.max(rows, 2);
		cols = Math.max(cols, 2);
		this.rows = rows;
		this.cols = cols;
		int maxNum = rows * cols;

		board = new RectBoard(rows, cols);
		roBoard = new ReadOnlyBoard(board);
		finished = false;

		Random r = new Random();
		boolean[] usedNums = new boolean[maxNum];
		for (int i = 0; i < rows; i++)
			for (int j = 0; j < cols; j++) {
				int k = r.nextInt(maxNum);
				while (usedNums[k])
					k = (k + 1) % maxNum;
				usedNums[k] = true;
				board.setPosition(i + 1, j + 1, k);
			}

		notifyReset();
	}

	private void notifyReset() {
		for (SlidePuzzleObserver o : obs) {
			o.onGameStart(roBoard);
		}
	}

	private void notifyGameOver() {
		for (SlidePuzzleObserver o : obs) {
			o.onGameOver(roBoard);
		}
	}

	private void notifyMove(int row, int col, int i, int j) {
		for (SlidePuzzleObserver o : obs) {
			o.onMove(roBoard, row, col, i, j);
		}
	}

	private void notifyError(String msg) {
		for (SlidePuzzleObserver o : obs) {
			o.onError(msg);
		}
		throw new GameError(msg);
	}

	@Override
	public void addObserver(SlidePuzzleObserver o) {
		obs.add(o);
		o.onGameStart(roBoard);
	}

	@Override
	public void removeObserver(SlidePuzzleObserver o) {
		obs.remove(o);
	}

	@Override
	public String toString() {
		return board.toString();
	}

}
