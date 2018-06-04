package tp.examples.clientserver.webclient;

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

	public static void fetchWebPage(String url) throws UnknownHostException, IOException {
		Socket s = new Socket(url, 80);

		PrintStream p = new PrintStream(s.getOutputStream());
		p.println("GET /");
		p.flush();
		
		Scanner in = new Scanner(s.getInputStream());
		while (in.hasNext()) {
			System.out.println(in.nextLine());
		}

		s.close();
	}

	public static void main(String[] args) throws UnknownHostException, IOException {
		fetchWebPage("www.ucm.es");
	}
}
