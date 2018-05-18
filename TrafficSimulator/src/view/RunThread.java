package view;

import java.io.IOException;

import javax.swing.SwingUtilities;

import control.Controller;
import model.SimulatorError;

public class RunThread extends Thread {
	
	private Controller _control;
	private ToolBar _toolBar;
	
	public RunThread(Controller control, ToolBar toolBar) {
		_control = control;
		_toolBar = toolBar;
	}
	
	@Override
	public void run() {
		_toolBar.able(false);
		for (int i = 0; i < _toolBar.getTime(); i++) {
			// Runs inside of the Swing UI thread
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					try {
					_control.run(1);
					} catch (IOException | SimulatorError e) {}
				}
			});
			try {
				Thread.sleep(_toolBar.getDelay() * 1000);
			} catch (InterruptedException e) {
			}
		}
		_toolBar.able(true);
	}
}
