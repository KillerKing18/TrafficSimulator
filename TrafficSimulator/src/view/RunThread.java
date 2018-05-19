package view;

import java.io.IOException;

import javax.swing.SwingUtilities;

import control.Controller;
import model.SimulatorError;

public class RunThread extends Thread {
	
	protected Controller _control;
	protected ToolBar _toolBar;
	protected MenuBar _menuBar;
	
	public RunThread(Controller control, ToolBar toolBar, MenuBar menuBar) {
		_control = control;
		_toolBar = toolBar;
		_menuBar = menuBar;
	}
	
	@Override
	public void run() {
		boolean interrupted = false;
		_toolBar.able(false);
		_menuBar.able(false);
		for (int i = 0; !interrupted && i < _toolBar.getTime(); i++) {
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
				interrupted = true;
			}
		}
		_toolBar.able(true);
		_menuBar.able(true);
	}
}
