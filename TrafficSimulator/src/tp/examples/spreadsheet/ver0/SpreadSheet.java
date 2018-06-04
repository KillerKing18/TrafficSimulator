package tp.examples.spreadsheet.ver0;

public interface SpreadSheet {
	public int getWidth();
	public int getHeight();
	public SPCell getCell(int row, int col);
	public void setCell(int row, int col, SPCell c);
	public void regCellObserver(int row, int col, SPCellObserver o);
	public void unregCellObserver(int row, int col, SPCellObserver o);
	public void notifyChange(int row, int col);
}
