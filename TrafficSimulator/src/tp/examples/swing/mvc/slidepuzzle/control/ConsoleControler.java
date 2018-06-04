package tp.examples.swing.mvc.slidepuzzle.control;

import java.util.Scanner;

import tp.examples.swing.mvc.slidepuzzle.logic.GameError;
import tp.examples.swing.mvc.slidepuzzle.logic.SlidePuzzle;

public class ConsoleControler extends Controller {

	private Scanner in;

	public ConsoleControler(Scanner in, SlidePuzzle game) {
		super(game);
		this.in = in;
	}

	@Override
	public void run() {
		String line;

		while (!exit) {

			System.out.print("Please enter a command: ");
			line = in.nextLine().toLowerCase();
			Command command = CommandSet.parse(line);

			if (command != null)
				try {
					command.execute(this);
				} catch (GameError e) {
				}
			else
				System.err.println(line
						+ ": command not understood, please try again (or use HELP)");

		}
		System.out.println("Closing the game... ");
	}
}
