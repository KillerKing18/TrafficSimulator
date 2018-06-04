package tp.examples.clientserver.ex1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

	private boolean stopped;

	private void handleRequest(Socket s) throws IOException {
		int i = 0;

		Scanner in = new Scanner(s.getInputStream());

		do {
			i = in.nextInt();
			System.out.println("Recevied: " + i);
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

	public void launchServer() throws IOException {
		startServer();
	}

	public static void main(String[] args) throws IOException {
		Server s = new Server();
		s.launchServer();
	}
}
