package tp.examples.clientserver.ex4;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.Scanner;

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
		Scanner in = new Scanner(s.getInputStream());

		for (int i = 0; i < 10; i++) {
			p.println(r.nextInt(1000));
			p.flush();
			System.out.println(in.nextLine());
			sleep(3000);
		}

		p.print(-1);
		s.close();

	}

	public static void main(String[] args) throws UnknownHostException, IOException {
		sendToServer();
	}
}
