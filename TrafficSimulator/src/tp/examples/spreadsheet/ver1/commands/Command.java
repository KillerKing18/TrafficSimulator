package tp.examples.spreadsheet.ver1.commands;

import tp.examples.spreadsheet.ver1.Controller;
import tp.examples.spreadsheet.ver1.SpreadSheet;

public interface Command {
	public void execute(SpreadSheet s, Controller controller);
	public Command parse(String line);
}
