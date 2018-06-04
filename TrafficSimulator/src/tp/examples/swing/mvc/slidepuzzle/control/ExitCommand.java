package tp.examples.swing.mvc.slidepuzzle.control;

public class ExitCommand implements Command {

	int row;
	int col;

	public ExitCommand() {
	}

	@Override
	public void execute(Controller c) {
		c.requestExit();
	}

	@Override
	public String helpText() {
		return "EXIT";
	}

	@Override
	public Command parse(String[] lineWords) {

		if (lineWords.length != 1)
			return null;

		if (!lineWords[0].equalsIgnoreCase("exit"))
			return null;

		return new ExitCommand();

	}

}
