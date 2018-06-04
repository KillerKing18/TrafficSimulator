package tp.examples.spreadsheet.ver0;

public class SPCellMul extends SPCell {

	private int r1, c1, r2, c2, row, col;
	private SpreadSheet sp;

	public SPCellMul(int r1, int c1, int r2, int c2) {
		this.r1 = r1;
		this.c1 = c1;
		this.r2 = r2;
		this.c2 = c2;
	}

	@Override
	public void valueChanged(int row, int col, double v) {
		updateValue();
	}

	@Override
	public void installed(SpreadSheet sp, int row, int col) {
		this.sp = sp;
		this.row = row;
		this.col = col;
		sp.regCellObserver(r1, c1, this);
		sp.regCellObserver(r2, c2, this);
		updateValue();
	}

	private void updateValue() {
		double v = sp.getCell(r1, c1).getNumericValue() * sp.getCell(r2, c2).getNumericValue();
		if ( v != numericValue ) {
			numericValue = v;
			sp.notifyChange(row, col);
		}
	}

	@Override
	public void removed() {
		sp.unregCellObserver(r1, c1, this);
		sp.unregCellObserver(r2, c2, this);
	}

}
