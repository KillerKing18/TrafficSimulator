package tp.examples.clientserver.ex5;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import com.sun.org.apache.xml.internal.serialize.Printer;

public class Server {

	private volatile boolean stopped;
	private volatile Integer numClients;
	private volatile ServerSocket server;

	private void handleRequest(Socket s) throws IOException, ClassNotFoundException {
		boolean flag = false;

		ObjectInputStream in = new ObjectInputStream(s.getInputStream());

		do {
			Object o = in.readObject();
			if ( o instanceof Command) {
				((Command) o).exec(this);
			} else {
				flag = true;
			}
		} while ( !flag );

	}

	private void handleRequestInAThread(Socket s) {
		new Thread() {
			@Override
			public void run() {
				try {
					handleRequest(s);
				} catch (IOException | ClassNotFoundException e) {
				}
			}
		}.start();
	}

	private void startServer() throws IOException {
		server = new ServerSocket(2000);
		Socket s = null;
		numClients = 0;
		stopped = false;

		while (!stopped) {
			try {
				s = server.accept();
			} catch (IOException e) {
			}
			handleRequestInAThread(s);
		}
	}
	
	public void launchserver() throws IOException {
		startServer();
	}

	public void f(Integer n) {
		System.out.println("f was called with "+n);
	}
	
	public void g(Integer n) {
		System.out.println("g was called with "+n);
	}
	
	public static void main(String[] args) throws IOException {
		Server s = new Server();
		s.launchserver();
		
	}


}
