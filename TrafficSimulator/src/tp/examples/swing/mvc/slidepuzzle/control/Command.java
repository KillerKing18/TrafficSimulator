package tp.examples.swing.mvc.slidepuzzle.control;

import tp.examples.swing.mvc.slidepuzzle.logic.GameError;

public interface Command {
	void execute(Controller c) throws GameError;
	String helpText();
	Command parse(String []lineWords);	
}
