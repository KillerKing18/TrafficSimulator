package tp.examples.spreadsheet.ver1.commands;

import tp.examples.spreadsheet.ver1.Controller;
import tp.examples.spreadsheet.ver1.SPCellNum;
import tp.examples.spreadsheet.ver1.SpreadSheet;

public class SetCmd implements Command {

	private int x;
	private int y;
	private double z;

	private SetCmd(int x, int y, double z) {	
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public SetCmd() {
	}

	@Override
	public void execute(SpreadSheet s, Controller c) {
		s.setCell(x, y, new SPCellNum(z));
	}

	@Override
	public Command parse(String line) {
		// set 1 1 100
		String[] tokens = line.split("\\s+");
		if ( tokens.length != 4 || !tokens[0].equalsIgnoreCase("set")) return null;
		try {
			int x = Integer.parseInt(tokens[1]);
			int y = Integer.parseInt(tokens[2]);
			double z = Double.parseDouble(tokens[3]);
			return new SetCmd(x,y,z);
		} catch ( NumberFormatException e) {
			return null;
		}
	}

}
