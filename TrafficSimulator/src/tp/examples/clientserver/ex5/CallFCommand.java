package tp.examples.clientserver.ex5;

public class CallFCommand implements Command {

	private Integer n;
	CallFCommand(Integer n) {
		this.n = n;
	}
	
	@Override
	public void exec(Server s) {
		s.f(n);
	}

}
