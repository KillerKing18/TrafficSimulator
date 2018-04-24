package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JToolBar;

import control.Controller;
import model.SimulatorError;

public class ToolBar extends JToolBar {
	
	private JButton loadEventsButton;
	private JButton saveEventsButton;
	private JButton clearEventsButton;
	private JButton checkInEventsButton;
	private JButton runButton;
	private JButton resetButton;
	private JSpinner time;
	
	private EventsEditorPanel _eventsEditorPanel;
	private Controller _control;
	
	public ToolBar(EventsEditorPanel eventsEditorPanel, Controller control) {
		super();
		_eventsEditorPanel = eventsEditorPanel;
		_control = control;
		initGUI();
	}

	private void initGUI() {	
		createLoadEventsButton();
		this.add(loadEventsButton);
		
		createSaveEventsButton();
		this.add(saveEventsButton);
		
		createClearEventsButton();
		this.add(clearEventsButton);
		
		this.addSeparator();
		
		createCheckInEventsButton();
		this.add(checkInEventsButton);
		
		createRunButton();
		this.add(runButton);
		
		createResetButton();
		this.add(resetButton);
	}
	
	private void createLoadEventsButton() {
		loadEventsButton = new JButton();
		loadEventsButton.setActionCommand("LOAD");
		loadEventsButton.addActionListener(_eventsEditorPanel);
		String pathLoad = "/icons/open.png";  
		URL urlLoad = this.getClass().getResource(pathLoad);  
		ImageIcon iconLoad = new ImageIcon(urlLoad);
		loadEventsButton.setIcon(iconLoad);
	}
	
	private void createSaveEventsButton() {
		saveEventsButton = new JButton();
		saveEventsButton.setActionCommand("SAVE");
		saveEventsButton.addActionListener(_eventsEditorPanel);
		String pathSave = "/icons/save.png";  
		URL urlSave = this.getClass().getResource(pathSave);  
		ImageIcon iconSave = new ImageIcon(urlSave);
		saveEventsButton.setIcon(iconSave);
	}
	
	private void createClearEventsButton() {
		clearEventsButton = new JButton();
		clearEventsButton.setActionCommand("CLEAR");
		clearEventsButton.addActionListener(_eventsEditorPanel);
		String pathClear = "/icons/clear.png";  
		URL urlClear = this.getClass().getResource(pathClear);  
		ImageIcon iconClear = new ImageIcon(urlClear);
		clearEventsButton.setIcon(iconClear);
	}
	
	private void createCheckInEventsButton() {
		checkInEventsButton = new JButton();
		checkInEventsButton.setActionCommand("CHECK IN");
		checkInEventsButton.addActionListener(_eventsEditorPanel);
		String pathCheckIn = "/icons/events.png";  
		URL urlCheckIn = this.getClass().getResource(pathCheckIn);  
		ImageIcon iconCheckIn = new ImageIcon(urlCheckIn);
		checkInEventsButton.setIcon(iconCheckIn);
	}
	
	private void createRunButton() {
		runButton = new JButton();
		runButton.setActionCommand("RUN");
		runButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					_control.run((Integer)time.getValue());
				} catch (IOException | SimulatorError e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		String pathRun = "/icons/play.png";  
		URL urlRun = this.getClass().getResource(pathRun);  
		ImageIcon iconRun = new ImageIcon(urlRun);
		runButton.setIcon(iconRun);
	}
	
	private void createResetButton() {
		resetButton = new JButton();
		resetButton.setActionCommand("RESET");
		resetButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				_control.reset();
			}
			
		});
		String pathReset = "/icons/reset.png";  
		URL urlReset = this.getClass().getResource(pathReset);  
		ImageIcon iconReset = new ImageIcon(urlReset);
		resetButton.setIcon(iconReset);
	}
}
