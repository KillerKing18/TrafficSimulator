package tp.examples.swing.mvc.primes;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class MainWindow extends JFrame {

	Observable<PrimesObserver> model;
	

	public MainWindow(Observable<PrimesObserver> model, GUIControler guiCtrl) {
		super("Primes");
		initGUI(model,guiCtrl);
	}

	private void initGUI(Observable<PrimesObserver> model, GUIControler guiCtrl) {
	
		ToolBarPanel toolBar = new ToolBarPanel(model,guiCtrl);
		NumsPanel numsPanel = new NumsPanel(model);
		StatusBarPanel statusBar = new StatusBarPanel(model);

		JPanel mainPanel = new JPanel(new BorderLayout());
		this.setContentPane(mainPanel);

		mainPanel.add( toolBar, BorderLayout.PAGE_START );
		mainPanel.add( numsPanel, BorderLayout.CENTER );
		mainPanel.add( statusBar, BorderLayout.PAGE_END );

		this.pack();
		this.setVisible(true);
	}

}
