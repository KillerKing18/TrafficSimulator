package tp.examples.clientserver.ex2;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

	private boolean stopped;

	private void handleRequest(Socket s) throws IOException {
		int i = 0;

		Scanner in = new Scanner(s.getInputStream());
		PrintStream out = new PrintStream(s.getOutputStream());

		do {
			i = in.nextInt();
			System.out.println("Recevied: "+i);
			out.println(2+"*"+i+"="+(2*i));
		} while (i != -1);

		s.close();
	}

	private void startServer() throws IOException {
		ServerSocket server = new ServerSocket(2000);

		while (!stopped) {
			System.out.println("Waiting for a connection");
			Socket s = server.accept();
			System.out.println("Someone has connected!");
			handleRequest(s);
		}

		server.close();

	}

	public void lanchServer() throws IOException {
		startServer();
	}
	
	public static void main(String[] args) throws IOException {
		Server s = new Server();
		s.lanchServer();
	}
}
