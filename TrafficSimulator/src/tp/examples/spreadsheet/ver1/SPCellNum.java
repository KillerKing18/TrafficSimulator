package tp.examples.spreadsheet.ver1;

public class SPCellNum extends SPCell {

	public SPCellNum(double v) {
		this.numericValue = v;
	}
	
	@Override
	public void valueChanged(int row, int col, double v) {
	}

	@Override
	public void installed(SpreadSheet.SPConnection sp) {
	}

	@Override
	public void removed() {
	}

}
