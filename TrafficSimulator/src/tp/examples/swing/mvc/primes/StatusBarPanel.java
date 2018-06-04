package tp.examples.swing.mvc.primes;

import java.math.BigInteger;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


@SuppressWarnings("serial")
public class StatusBarPanel extends JPanel implements PrimesObserver {

	JLabel totalNumsLabeltext;
	JLabel statusLabel;
	int totalNums;

	public StatusBarPanel(Observable<PrimesObserver> model) {
		initGUI();
		model.addObserver(this);
	}

	private void initGUI() {
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.setBorder(BorderFactory.createLoweredBevelBorder());
		this.add( new JLabel("Total Primes Generated: "));
		totalNumsLabeltext = new JLabel(totalNums+"");
		this.add(totalNumsLabeltext);
		this.add( new JLabel("   Status: "));
		statusLabel = new JLabel("Fresh     ");		
		this.add(statusLabel);
	}

	public void onStart() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				statusLabel.setText("Running");
			}
		});
	}

	@Override
	public void onFinish(boolean halt) {
		final String status = halt ? "Stopped" : "Terminated";
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				statusLabel.setText(status);
			}
		});
	}

	@Override
	public void onGen(BigInteger n) {
		totalNums++;
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				totalNumsLabeltext.setText(totalNums+"");
			}
		});
	}

}
