package tp.examples.clientserver.ex3;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ServerWithExecutor {

	private static boolean stopped;

	// private final Executor exec = Executors.newFixedThreadPool(10);
	private final Executor exec = Executors.newCachedThreadPool();
	// private final Executor exec = Executors.newSingleThreadExecutor();

	private void handleRequest(Socket s) throws IOException {
		int i = 0;

		Scanner in = new Scanner(s.getInputStream());
		PrintStream out = new PrintStream(s.getOutputStream());

		do {
			i = in.nextInt();
			System.out.println(Thread.currentThread().getName() + " Recevied: " + i);
			out.println(2 + "*" + i + "=" + (2 * i));
		} while (i != -1);

	}

	private void handleRequestInAThread(Socket s) {
		exec.execute(new Runnable() {

			@Override
			public void run() {
				try {
					handleRequest(s);
				} catch (IOException e) {
				}
			}
		});
	}

	private void startServer() throws IOException {
		ServerSocket server = new ServerSocket(2000);

		while (!stopped) {
			System.out.println("Waiting for a connection");
			Socket s = server.accept();
			System.out.println("Someone has connected!");
			handleRequestInAThread(s);
		}

		server.close();

	}

	public void lanchServer() throws IOException {
		startServer();
	}
	
	public static void main(String[] args) throws IOException {
		ServerWithExecutor s = new ServerWithExecutor();
		s.lanchServer();
	}
}
