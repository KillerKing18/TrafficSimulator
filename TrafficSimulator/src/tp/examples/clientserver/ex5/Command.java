package tp.examples.clientserver.ex5;

import java.io.Serializable;

public interface Command extends Serializable {
	public void exec(Server s);
}
