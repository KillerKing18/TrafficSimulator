package tp.examples.spreadsheet.ver1.commands;

import tp.examples.spreadsheet.ver1.Alarm;
import tp.examples.spreadsheet.ver1.Controller;
import tp.examples.spreadsheet.ver1.SpreadSheet;

public class AlarmCmd implements Command {

	private int x;
	private int y;
	private double z;

	private AlarmCmd(int x, int y, double z) {	
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public AlarmCmd() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(SpreadSheet s, Controller c) {
		s.regCellObserver(x, y, new Alarm(z));		
	}

	@Override
	public Command parse(String line) {
		// alarm 1 1 100
		String[] tokens = line.split("\\s+");
		if ( tokens.length != 4 || !tokens[0].equalsIgnoreCase("alarm")) return null;
		try {
			int x = Integer.parseInt(tokens[1]);
			int y = Integer.parseInt(tokens[2]);
			double z = Double.parseDouble(tokens[3]);
			return new AlarmCmd(x,y,z);
		} catch ( NumberFormatException e) {
			return null;
		}
	}

}
