package tp.examples.swing.mvc.slidepuzzle.control;

import tp.examples.swing.mvc.slidepuzzle.logic.GameError;

public class MoveCommand implements Command {

	int row;
	int col;

	public MoveCommand(int row, int col) {
		this.row = row;
		this.col = col;
	}

	@Override
	public void execute(Controller c) throws GameError {
		c.move(row, col);
	}

	@Override
	public String helpText() {
		return "MOVE row col (note that row and col start from 1)";
	}

	@Override
	public Command parse(String[] lineWords) {

		if (lineWords.length < 3)
			return null;

		if (!lineWords[0].equalsIgnoreCase("move"))
			return null;

		try {
			return new MoveCommand(Integer.parseInt(lineWords[1]),
					Integer.parseInt(lineWords[2]));
		} catch (NumberFormatException ex) {
			return null;
		}

	}

}
