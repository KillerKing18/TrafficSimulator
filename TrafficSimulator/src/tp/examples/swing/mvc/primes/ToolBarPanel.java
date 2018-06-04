package tp.examples.swing.mvc.primes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

@SuppressWarnings("serial")
class ToolBarPanel extends JPanel implements PrimesObserver {

	private JButton startButton;
	private JButton stopButton;
	private JButton quitButton;

	private GUIControler guiCtrl;

	ToolBarPanel(Observable<PrimesObserver> model, GUIControler guiCtrl) {
		this.guiCtrl = guiCtrl;
		initGUI();
		model.addObserver(this);
	}

	private void initGUI() {

		startButton = new JButton("Start");
		this.add(startButton);
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				startButton.setEnabled(false);
				stopButton.setEnabled(true);
				quitButton.setEnabled(false);
				new SwingWorker<Void,Void>() {
					@Override
					protected Void doInBackground() throws Exception {
						guiCtrl.generatePrimes();
						return null;
					}	
				}.execute();	
			}
		});

		// stop
		stopButton = new JButton("Stop");
		stopButton.setEnabled(false);
		this.add(stopButton);
		stopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiCtrl.stop();
			}
		});

		// quit
		quitButton = new JButton("Quit");
		this.add(quitButton);
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiCtrl.quit();
				System.exit(0);
			}
		});
	}

	@Override
	public void onStart() {
	}

	@Override
	public void onFinish(boolean halt) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				startButton.setEnabled(true);
				stopButton.setEnabled(false);
				quitButton.setEnabled(true);
			}
		});
	}

	@Override
	public void onGen(BigInteger n) {
	}

}
