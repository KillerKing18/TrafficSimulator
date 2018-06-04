package tp.examples.swing.mvc.slidepuzzle.control;

public class ResetCommand implements Command {

	int row;
	int col;

	public ResetCommand(int row, int col) {
		this.row = row;
		this.col = col;
	}

	@Override
	public void execute(Controller c) {
		c.reset(row, col);
	}

	@Override
	public String helpText() {
		return "RESET [row col]";
	}

	@Override
	public Command parse(String[] lineWords) {

		if (lineWords.length < 1)
			return null;

		if (!lineWords[0].equalsIgnoreCase("reset"))
			return null;

		if ( lineWords.length == 1 )
			return new ResetCommand(2, 2);
		
		if ( lineWords.length != 3 )
			return null;
		
		try {
			return new ResetCommand(Integer.parseInt(lineWords[1]),
					Integer.parseInt(lineWords[2]));
		} catch (NumberFormatException ex) {
			return null;
		}

	}

}
