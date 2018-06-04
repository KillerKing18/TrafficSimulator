package tp.examples.spreadsheet.ver0;


public class Alarm implements SPCellObserver {

	double limit;

	public Alarm(double limit) {
		this.limit = limit;
	}

	@Override
	public void valueChanged(int x, int y, double v) {
		if (v > limit)
			System.out.println("WARNNING!! Limit reached!!");
	}

}
