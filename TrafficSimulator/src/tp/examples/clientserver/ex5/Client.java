package tp.examples.clientserver.ex5;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

public class Client {

	public static void sleep(int n) {
		try {
			Thread.sleep(n);
		} catch (InterruptedException e) {
		}
	}

	public static void sendToServer() throws UnknownHostException, IOException, ClassNotFoundException {
		Random r = new Random();

		Socket s = new Socket("localhost", 2000);
		ObjectOutputStream p = new ObjectOutputStream(s.getOutputStream());

		for (int i = 0; i < 10; i++) {
			Integer x = r.nextInt(1000);
			Command cmd = r.nextBoolean() ? new CallFCommand(x) : new CallGCommand(x);
			p.writeObject(cmd);
			p.flush();
			p.reset();
			sleep(300);
		}

		p.writeObject("stop");
		s.close();

	}

	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
		sendToServer();
	}
}
