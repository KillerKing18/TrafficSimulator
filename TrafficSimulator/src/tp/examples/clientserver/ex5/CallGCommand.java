package tp.examples.clientserver.ex5;

public class CallGCommand implements Command {

	private Integer n;
	CallGCommand(Integer n) {
		this.n = n;
	}
	
	@Override
	public void exec(Server s) {
		s.g(n);
	}

}
