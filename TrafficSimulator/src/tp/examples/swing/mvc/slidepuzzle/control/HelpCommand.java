package tp.examples.swing.mvc.slidepuzzle.control;


/**
 * @y.exclude
 */
public class HelpCommand implements Command {

	@Override
	public void execute(Controller c) {
		System.out.println("The available commands are:");
		System.out.println();
		System.out.println(CommandSet.helpText());
	}

	@Override
	public String helpText() {
		return "HELP: show this help.";
	}

	@Override
	public Command parse(String[] lineWords) {
		
		if (lineWords.length != 1) return null;
		
		if (lineWords[0].equalsIgnoreCase("help"))
			return this;
		
		return null;
	}

}
