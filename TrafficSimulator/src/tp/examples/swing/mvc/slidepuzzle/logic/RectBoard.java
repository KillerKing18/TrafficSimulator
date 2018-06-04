package tp.examples.swing.mvc.slidepuzzle.logic;

public class RectBoard implements Board {
	private int[][] board;
	private int rows;
	private int cols;

	public RectBoard(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		board = new int[rows][cols];
	}

	@Override
	public boolean setPosition(int row, int col, int v) {
		if ( row >= 1 && row <= rows && col >= 1 && col <= cols ) {
			board[row-1][col-1] = v;
			return true;
		} else
			return false;
	}

	@Override
	public int getPosition(int row, int col) {
		if ( row >= 1 && row <= rows && col >= 1 && col <= cols ) {
			return board[row-1][col-1];
		} else {
			return -1;
		}
	}
	
	@Override
	public int getWidth() {
		return cols;
	}

	@Override
	public int getHeight() {
		return rows;
	}

	@Override
	public String toString() {
		StringBuilder render = new StringBuilder();

		for (int i = 0; i < rows; i++) {
			render.append("|");
			for (int j = 0; j < cols; j++) {
				if (board[i][j] == 0 )
					render.append("       |");
				else						
					render.append(String.format(" %5d |",board[i][j]));
			}
			render.append("\n");
		}
		return render.toString();
	}


}
