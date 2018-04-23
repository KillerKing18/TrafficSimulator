package model;

/**
 * All the exceptions inherit from this class, each of them for an specific purpose or situation:
 *  - UnexistingObjectException
 *  - SameIdException
 *  - InvalidParametersException
 *  - ExecutionOfEventException
 *  - OutOfTimeException
 * 
 */
public class SimulatorError extends Exception {
	
	public SimulatorError(String str){
		super(str);
	}
}
