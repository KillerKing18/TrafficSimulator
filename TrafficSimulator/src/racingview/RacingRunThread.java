package racingview;

import java.io.IOException;

import javax.swing.SwingUtilities;

import control.Controller;
import model.SimulatorError;
import racingcontrol.RacingController;
import view.MenuBar;
import view.RunThread;
import view.ToolBar;

public class RacingRunThread extends RunThread {
	
	private Controller _control;
	private RacingPanel _racingPanel;
	private boolean _interrupted;

	public RacingRunThread(Controller control, ToolBar toolBar, MenuBar menuBar, RacingPanel racingPanel) {
		super(control, toolBar, menuBar);
		_control = control;
		_racingPanel = racingPanel;
		_interrupted = false;
	}

	@Override
	public void run() {
		_toolBar.able(false);
		for (int i = 0; !RacingRunThread.this._interrupted && i < _toolBar.getTime(); i++) {
			// Runs inside of the Swing UI thread
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					try {
					_control.run(1);
					if(((RacingController) _control).getTotalVehicles() == ((RacingController) _control).getArrivedVehicles()) {
						_racingPanel.raceFinished();
						((RacingToolBar)_toolBar).raceFinished();
						_interrupted = true;
					}
					} catch (IOException | SimulatorError e) {}
				}
			});
			try {
				Thread.sleep(_toolBar.getDelay() * 1000);
			} catch (InterruptedException e) {
				_interrupted = true;
			}
		}
		_toolBar.able(true);
	}
}
