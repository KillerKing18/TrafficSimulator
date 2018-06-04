package tp.examples.swing.mvc.slidepuzzle;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import tp.examples.swing.mvc.slidepuzzle.control.ConsoleControler;
import tp.examples.swing.mvc.slidepuzzle.control.Controller;
import tp.examples.swing.mvc.slidepuzzle.control.WindowControler;
import tp.examples.swing.mvc.slidepuzzle.logic.SlidePuzzle;
import tp.examples.swing.mvc.slidepuzzle.views.console.ConsoleView;
import tp.examples.swing.mvc.slidepuzzle.views.log.LogView;
import tp.examples.swing.mvc.slidepuzzle.views.window.MainWindow1;
import tp.examples.swing.mvc.slidepuzzle.views.window.MainWindow2;
import tp.examples.swing.mvc.slidepuzzle.views.window.MainWindow3;
import tp.examples.swing.mvc.slidepuzzle.views.window.MainWindow4;


public class Main {

	public static void main_0() {
		SlidePuzzle game = new SlidePuzzle();
		Controller ctrl = new ConsoleControler( new Scanner(System.in), game);
		new ConsoleView(game);
		new LogView(game, "/tmp/log.txt");
		new LogView(game, "/tmp/log_1.txt");
		ctrl.run();
	}

	public static void main_1() {
		SlidePuzzle game = new SlidePuzzle();
		Controller ctrl = new ConsoleControler( new Scanner(System.in), game);
		//new ConsoleView(game);
		//new LogView(game, "/tmp/log.txt");
		new MainWindow1(game);
		ctrl.run();
	}

	public static void main_2() {
		SlidePuzzle game = new SlidePuzzle();
		Controller ctrl = new WindowControler(game);
		//new ConsoleView(game);
		//new LogView(game, "/tmp/log.txt");
		new MainWindow2(ctrl,game);
		ctrl.run();
	}

	public static void main_3() {
		SlidePuzzle game = new SlidePuzzle();
		Controller ctrl = new WindowControler(game);
		//new ConsoleView(game);
		//new LogView(game, "/tmp/log.txt");
		new MainWindow3(ctrl,game);
		ctrl.run();
	}

	public static void main_4() {
		SlidePuzzle game = new SlidePuzzle();
		Controller ctrl = new WindowControler(game);
		//new ConsoleView(game);
		//new LogView(game, "/tmp/log.txt");
		//new MainWindow1(game);
		new MainWindow4(ctrl,game);
		ctrl.run();
	}

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		main_4();
	}

}
