package tp.examples.clientserver.ex4;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import com.sun.org.apache.xml.internal.serialize.Printer;

public class Server {

	private volatile boolean stopped;
	private volatile ServerSocket server;
	private JTextArea infoArea;
	private boolean consoleMode;

	private void handleRequest(Socket s) throws IOException {
		int i = 0;

		Scanner in = new Scanner(s.getInputStream());
		PrintStream out = new PrintStream(s.getOutputStream());

		do {
			i = in.nextInt();
			out.println(2 + "*" + i + "=" + (2 * i));
		} while (i != -1);

		s.close();

	}

	private void handleRequestInAThread(Socket s) {
		new Thread() {
			@Override
			public void run() {
				try {
					handleRequest(s);
				} catch (IOException e) {
				}
			}
		}.start();
	}

	private void startServer() throws IOException {
		server = new ServerSocket(2000);
		Socket s = null;
		stopped = false;

		while (!stopped) {
			try {
				s = server.accept();
			} catch (IOException e) {
			}
			handleRequestInAThread(s);
		}
	}

	private void startSertverInAThread() {
		new Thread() {
			@Override
			public void run() {
				try {
					startServer();
				} catch (IOException e) {
				}
			}
		}.start();
	}

	private void control() {
		if (consoleMode) {
			consoleControl();
		} else {
			guiControl();
		}
	}

	private void consoleControl() {
		Scanner in = new Scanner(System.in);
		while (!stopped) {
			log("Type a command (status or exit): ");
			String cmd = in.nextLine();
			switch (cmd) {
			case "stop":
				stopped = true;
				try {
					server.close();
				} catch (IOException e) {
				}
			default:
				break;
			}
		}

	}

	private void guiControl() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				constructControGUI();
			}
		});
	}

	private void constructControGUI() {
		JFrame window = new JFrame("Simple Server");
		JPanel mainPanel = new JPanel(new BorderLayout());
		window.setContentPane(mainPanel);

		// text area for printing messages
		infoArea = new JTextArea();
		mainPanel.add(infoArea, BorderLayout.CENTER);

		// quit button
		JButton quitButton = new JButton("Stop Sever");
		mainPanel.add(quitButton, BorderLayout.PAGE_END);
		quitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				stopped = true;
				try {
					server.close();
				} catch (IOException e1) {
				}
				System.exit(0);
			}
		});

		window.setPreferredSize(new Dimension(400, 250));
		window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		window.pack();
		window.setVisible(true);
	}

	private void log(String msg) {
		if (consoleMode) {
			System.out.println("SERVER: " + msg);
			System.out.flush();
		} else {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					infoArea.append(msg + "\n");
				}
			});
		}
	}

	public void lanchServer(boolean consoleMode) {
		this.consoleMode = consoleMode;
		startSertverInAThread();
		control();
	}

	public static void main(String[] args) throws IOException {
		Server s = new Server();
		s.lanchServer(false);
	}

}
