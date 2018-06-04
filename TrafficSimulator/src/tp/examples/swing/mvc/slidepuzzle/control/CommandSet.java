package tp.examples.swing.mvc.slidepuzzle.control;


public class CommandSet {

	private static Command[] appCommands = {
		new HelpCommand(),
		new ResetCommand(4,4),
		new MoveCommand(1, 1),
		new ExitCommand()
	};

	public static Command parse(String line) {
		String []words = line.split(" ");
		for (Command com : appCommands) {
			Command tryCommand = com.parse(words);
			if (tryCommand != null)
				return tryCommand;
		}
		return null;
	}
	
	public static String helpText() {
		StringBuilder sb = new StringBuilder();
		
		for (Command com : appCommands) {
			sb.append(com.helpText());
			sb.append("\n");
		}
		return sb.toString();
	}		
}
