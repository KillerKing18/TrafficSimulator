package tp.examples.swing.mvc.primes;

import java.awt.BorderLayout;
import java.awt.Font;
import java.math.BigInteger;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class NumsPanel extends JPanel implements PrimesObserver {

	private JList<BigInteger> nums;
	private DefaultListModel<BigInteger> listModel;

	NumsPanel(Observable<PrimesObserver> model) {
		initGUI();
		model.addObserver(this);
	}

	private void initGUI() {
		this.setLayout(new BorderLayout());
		listModel = new DefaultListModel<BigInteger>();
		nums = new JList<BigInteger>(listModel);
		nums.setFont(new Font("Courier", Font.PLAIN, 16));
		this.add(new JScrollPane(nums));
	}

	@Override
	public void onStart() {
	}

	@Override
	public void onFinish(boolean halt) {
	}

	@Override
	public void onGen(final BigInteger n) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				listModel.add(0, n);
			}
		});
	}

}
