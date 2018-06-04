package tp.examples.spreadsheet.ver1;

public abstract class SPCell implements SPCellObserver {
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

	abstract public void installed(SpreadSheet.SPConnection sp);
	abstract public void removed();

}
