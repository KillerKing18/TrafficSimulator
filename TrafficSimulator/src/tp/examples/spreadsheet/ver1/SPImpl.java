package tp.examples.spreadsheet.ver1;

import java.util.ArrayList;
import java.util.List;

public class SPImpl implements SpreadSheet {

	private SPCell[][] table;
	private List<SPCellObserver>[][] obs;
	private boolean[][] notifyStatus;

	private int rows;
	private int cols;

	@SuppressWarnings("unchecked")
	public SPImpl(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;

		table = new SPCell[rows][cols];
		notifyStatus = new boolean[rows][cols];
		obs = (List<SPCellObserver>[][]) new List<?>[rows][cols];

		for (int i = 0; i < rows; i++)
			for (int j = 0; j < cols; j++) {
				obs[i][j] = new ArrayList<SPCellObserver>();
				table[i][j] = new SPCellNum(0.0);
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
	public SPCell getCell(int row, int col) {
		return table[row][col];
	}

	@Override
	public void setCell(final int row, final int col, SPCell c) {
		table[row][col].removed();
		table[row][col] = c;
		table[row][col].installed( new SPConnection() {
			
			@Override
			public void unregCellObserver(int row, int col, SPCellObserver o) {
				SPImpl.this.unregCellObserver(row, col, o);
			}
			
			@Override
			public void regCellObserver(int row, int col, SPCellObserver o) {
				SPImpl.this.regCellObserver(row, col, o);
			}

			@Override
			public void notifyChange() {
				SPImpl.this.notifyChange(row, col);
			}
			
			@Override
			public SPCell getCell(int row, int col) {
				return SPImpl.this.getCell(row, col);
			}
		});
		notifyChange(row, col);
	}

	@Override
	public void regCellObserver(int row, int col, SPCellObserver o) {
		obs[row][col].add(o);
	}

	@Override
	public void unregCellObserver(int row, int col, SPCellObserver o) {
		obs[row][col].remove(o);
	}


	private void notifyChange(int row, int col) {
		for (SPCellObserver o : obs[row][col]) {
			if (!notifyStatus[row][col]) {
				notifyStatus[row][col] = true;
				o.valueChanged(row, col, table[row][col].getNumericValue());
				notifyStatus[row][col] = false;
			} else {
				throw new UnsupportedOperationException("Cycle!");
			}
		}
	}

	@Override
	public String toString() {
		String s = "";
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				s = s + table[i][j].getNumericValue() + "  ";
			}
			s = s + "\n";
		}
		return s;
	}
}
