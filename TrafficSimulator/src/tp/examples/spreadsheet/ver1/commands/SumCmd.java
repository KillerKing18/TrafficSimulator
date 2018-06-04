package tp.examples.spreadsheet.ver1.commands;

import tp.examples.spreadsheet.ver1.Controller;
import tp.examples.spreadsheet.ver1.SPCellSum;
import tp.examples.spreadsheet.ver1.SpreadSheet;

public class SumCmd implements Command {

	private int x;
	private int y;
	private int x1;
	private int y1;
	private int x2;
	private int y2;

	private SumCmd(int x, int y, int x1, int y1, int x2, int y2 ) {	
		this.x = x;
		this.y = y;
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	public SumCmd() {
	}

	@Override
	public void execute(SpreadSheet s, Controller c) {
		s.setCell(x, y, new SPCellSum(x1, y1, x2, y2));
	}

	@Override
	public Command parse(String line) {
		// sum 1 1 2 2 3 3
		String[] tokens = line.split("\\s+");
		if ( tokens.length != 7 || !tokens[0].equalsIgnoreCase("sum")) return null;
		try {
			int x = Integer.parseInt(tokens[1]);
			int y = Integer.parseInt(tokens[2]);
			int x1 = Integer.parseInt(tokens[3]);
			int y1 = Integer.parseInt(tokens[4]);
			int x2 = Integer.parseInt(tokens[5]);
			int y2 = Integer.parseInt(tokens[6]);
			return new SumCmd(x,y,x1,y1,x2,y2);
		} catch ( NumberFormatException e) {
			return null;
		}
	}

}
