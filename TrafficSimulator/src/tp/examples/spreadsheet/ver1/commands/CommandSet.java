package tp.examples.spreadsheet.ver1.commands;

public class CommandSet {
	static Command[] cmds = { new AlarmCmd() , new PrintCmd(), new SetCmd(), new SumCmd(), new QuitCmd() };
	
	public static Command parse(String line) {
		for( Command c : cmds) {
			Command cmd = c.parse(line);
			if ( cmd != null ) return cmd;
		}
		return null;
	}

}
