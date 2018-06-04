package tp.examples.spreadsheet.ver0;

abstract class SPCell implements SPCellObserver {
	protected double numericValue;

	protected SPCell() {
		numericValue = 0;
	}

	public double getNumericValue() {
		return numericValue;
	};

	public String toString() {
		return Double.toString(numericValue);
	}

	abstract public void installed(SpreadSheet sp, int row, int col);
	abstract public void removed();

}
