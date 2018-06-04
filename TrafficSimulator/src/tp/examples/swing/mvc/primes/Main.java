package tp.examples.swing.mvc.primes;

import javax.swing.SwingUtilities;

public class Main {

	public static void main(String[] args) {
		final PrimesGenerator model = new PrimesGenerator();
		final GUIControler guiCtrl = new GUIControler(model);

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainWindow(model, guiCtrl);
			}
		});
	}

}
