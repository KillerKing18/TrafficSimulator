package tp.examples.swing.mvc.slidepuzzle.logic;

public class ReadOnlyBoard implements Board {

	private Board board;

	public ReadOnlyBoard(Board board) {
		this.board = board;
	}

	@Override
	public int getPosition(int row, int col) {
		return board.getPosition(row, col);
	}

	@Override
	public boolean setPosition(int row, int col, int num) {
		throw new UnsupportedOperationException("Cannot modify a read only board");
	}

	@Override
	public int getWidth() {
		return board.getWidth();
	}

	@Override
	public int getHeight() {
		return board.getHeight();
	}
	
	@Override
	public String toString() {
		return board.toString();
	}

}
