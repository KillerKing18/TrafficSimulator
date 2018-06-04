package tp.examples.spreadsheet.ver0;

public class SPCellNum extends SPCell {

	public SPCellNum(double v) {
		this.numericValue = v;
	}
	
	@Override
	public void valueChanged(int row, int col, double v) {
	}

	@Override
	public void installed(SpreadSheet sp, int row, int col) {
	}

	@Override
	public void removed() {
	}

}
