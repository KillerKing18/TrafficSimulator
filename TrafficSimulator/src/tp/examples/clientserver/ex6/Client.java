package tp.examples.clientserver.ex6;

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
		ObjectInputStream in = new ObjectInputStream(s.getInputStream());
		
		for (int i = 0; i < 10; i++) {
			p.writeObject( new MyNumber(r.nextInt(1000)));
			p.flush();
			p.reset();
			MyNumber n = (MyNumber) in.readObject();
			System.out.println( "Sent "+i+" Recieved back: "+ n.getValue());
			sleep(300);
		}

		p.writeObject( new MyNumber(-1) );
		s.close();

	}

	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
		sendToServer();
	}
}
