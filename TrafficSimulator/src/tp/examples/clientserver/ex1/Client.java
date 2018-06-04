package tp.examples.clientserver.ex1;

import java.io.IOException;
import java.io.PrintStream;
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

	public static void sendToServer() throws UnknownHostException, IOException {
		Random r = new Random();

		Socket s = new Socket("localhost", 2000);
		PrintStream p = new PrintStream(s.getOutputStream());

		for (int i = 0; i < 10; i++) {
			p.println(r.nextInt(1000));
			p.flush();
			sleep(300);
		}
		p.print(-1);
		s.close();

	}

	public static void main(String[] args) throws UnknownHostException, IOException {
		sendToServer();
	}
}
