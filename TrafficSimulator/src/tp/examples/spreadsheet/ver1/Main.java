package tp.examples.spreadsheet.ver1;

import tp.examples.spreadsheet.ver1.Controller;

public class Main {

	static public void test1() {
		SPImpl s = new SPImpl(5, 5);
		s.setCell(1, 1, new SPCellNum(5));
		s.setCell(2, 1, new SPCellNum(9));
		s.setCell(3, 3, new SPCellSum(1, 1, 2, 1));
		System.out.println(s);
		s.setCell(2, 1, new SPCellNum(17));
		System.out.println(s);
		s.setCell(1, 1, new SPCellNum(10));
		System.out.println(s);

	}

	static public void interpreter() {
		SPImpl s = new SPImpl(5, 5);
		Controller ctrl = new Controller(s);
		ctrl.start();
	}

	static public void main(String[] args) {
		interpreter();
	}
}
