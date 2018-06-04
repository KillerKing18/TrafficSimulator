package tp.examples.spreadsheet.ver0.commands;

import tp.examples.spreadsheet.ver0.Controller;
import tp.examples.spreadsheet.ver0.SpreadSheet;

public class PrintCmd implements Command {

	@Override
	public void execute(SpreadSheet s, Controller c) {
		System.out.print(s);
	}

	@Override
	public Command parse(String line) {
		String[] tokens = line.split("\\s+");
		if ( tokens.length != 1 || !tokens[0].equalsIgnoreCase("print") )
			return null;
		return this;
	}

}
