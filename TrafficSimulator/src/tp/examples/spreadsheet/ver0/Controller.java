package tp.examples.spreadsheet.ver0;

import java.util.Scanner;

import tp.examples.spreadsheet.ver0.commands.Command;
import tp.examples.spreadsheet.ver0.commands.CommandSet;

public class Controller {

	SpreadSheet s;
	private boolean quit;

	public Controller(SpreadSheet s) {
		this.s = s;
	}

	public void requestQuit() {
		this.quit = true;
	}

	public void start() {
		Scanner in = new Scanner(System.in);

		while (!quit) {
			System.out.print("> ");
			String line = in.nextLine();
			Command cmd = CommandSet.parse(line);
			if (cmd != null)
				cmd.execute(s, this);
			else
				System.err.print("Error: " + line);
		}

		in.close();
	}

}
