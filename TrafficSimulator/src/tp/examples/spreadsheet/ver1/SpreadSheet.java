package tp.examples.spreadsheet.ver1;

public interface SpreadSheet {
	public int getWidth();
	public int getHeight();
	public SPCell getCell(int row, int col);
	public void setCell(int row, int col, SPCell c);
	public void regCellObserver(int row, int col, SPCellObserver o);
	public void unregCellObserver(int row, int col, SPCellObserver o);
	
	public interface SPConnection {
		public void notifyChange();
		public void regCellObserver(int row, int col, SPCellObserver o);
		public SPCell getCell(int row, int col);
		public void unregCellObserver(int row, int col, SPCellObserver o);
	}
}
