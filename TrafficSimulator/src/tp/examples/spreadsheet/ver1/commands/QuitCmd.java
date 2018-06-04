package tp.examples.spreadsheet.ver1.commands;

import tp.examples.spreadsheet.ver1.Controller;
import tp.examples.spreadsheet.ver1.SpreadSheet;

public class QuitCmd implements Command {

	@Override
	public void execute(SpreadSheet s, Controller c) {
		c.requestQuit();
	}

	@Override
	public Command parse(String line) {
		String[] tokens = line.split("\\s+");
		if ( tokens.length != 1 || !tokens[0].equalsIgnoreCase("quit") )
			return null;
		return this;
	}

}
