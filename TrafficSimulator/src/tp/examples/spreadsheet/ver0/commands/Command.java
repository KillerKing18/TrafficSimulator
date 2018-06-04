package tp.examples.spreadsheet.ver0.commands;

import tp.examples.spreadsheet.ver0.Controller;
import tp.examples.spreadsheet.ver0.SpreadSheet;

public interface Command {
	public void execute(SpreadSheet s, Controller controller);
	public Command parse(String line);
}
