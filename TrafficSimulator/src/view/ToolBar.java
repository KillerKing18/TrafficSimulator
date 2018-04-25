package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SpinnerNumberModel;

import control.Controller;
import model.Event;
import model.RoadMap;
import model.SimulatorError;
import model.TrafficSimulator;
import model.TrafficSimulatorObserver;

public class ToolBar extends JToolBar implements TrafficSimulatorObserver {
	
	private JButton loadEventsButton;
	private JButton saveEventsButton;
	private JButton clearEventsButton;
	private JButton checkInEventsButton;
	private JButton runButton;
	private JButton resetButton;
	private JLabel stepsLabel;
	private JSpinner stepsSpinner;
	private JLabel timeLabel;
	private JTextField timeTextField;
	private JButton generateReportsButton;
	private JButton clearReportsAreaButton;
	private JButton saveReportsButton;
	private JButton quitButton;
	
	private EventsEditorPanel _eventsEditorPanel;
	private ReportsAreaPanel _reportsArea;
	private Controller _control;
	
	public ToolBar(EventsEditorPanel eventsEditorPanel, ReportsAreaPanel reportsArea, Controller control, TrafficSimulator model) {
		super();
		_eventsEditorPanel = eventsEditorPanel;
		_reportsArea = reportsArea;
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
		
		createStepsLabel();
		this.add(stepsLabel);
		createStepsSpinner();
		this.add(stepsSpinner);
		
		createTimeLabel();
		this.add(timeLabel);
		createTimeTextField();
		this.add(timeTextField);
		
		this.addSeparator();
		
		createGenerateReportsButton();
		this.add(generateReportsButton);
		
		createClearReportsAreaButton();
		this.add(clearReportsAreaButton);
		
		createSaveReportsButton();
		this.add(saveReportsButton);
		
		this.addSeparator();
		
		createQuitButton();
		this.add(quitButton);
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
				try {
					_control.run((Integer)stepsSpinner.getValue());
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
				_control.reset(); // TODO
			}
			
		});
		String pathReset = "/icons/reset.png";  
		URL urlReset = this.getClass().getResource(pathReset);  
		ImageIcon iconReset = new ImageIcon(urlReset);
		resetButton.setIcon(iconReset);
	}
	
	private void createStepsLabel() {
		stepsLabel = new JLabel("Steps: ");
	}
	
	private void createStepsSpinner() {
		stepsSpinner = new JSpinner(new SpinnerNumberModel(0, 0, null, 1));
	}
	
	private void createTimeLabel() {
		timeLabel = new JLabel(" Time: ");
	}
	
	private void createTimeTextField() {
		timeTextField = new JTextField();
		timeTextField.setEditable(false);
	}
	
	private void createGenerateReportsButton() {
		generateReportsButton = new JButton();
		generateReportsButton.setActionCommand("GENERATE");
		generateReportsButton.addActionListener(_reportsArea);
		String pathGenerateReports = "/icons/report.png";  
		URL urlGenerateReports = this.getClass().getResource(pathGenerateReports);  
		ImageIcon iconGenerateReports = new ImageIcon(urlGenerateReports);
		generateReportsButton.setIcon(iconGenerateReports);
	}
	
	private void createClearReportsAreaButton() {
		clearReportsAreaButton = new JButton();
		clearReportsAreaButton.setActionCommand("CLEAR");
		clearReportsAreaButton.addActionListener(_reportsArea);
		String pathClearReportsArea = "/icons/delete_report.png";  
		URL urlClearReportsArea = this.getClass().getResource(pathClearReportsArea);  
		ImageIcon iconClearReportsArea = new ImageIcon(urlClearReportsArea);
		clearReportsAreaButton.setIcon(iconClearReportsArea);
	}

	private void createSaveReportsButton() {
		saveReportsButton = new JButton();
		saveReportsButton.setActionCommand("SAVE");
		saveReportsButton.addActionListener(_eventsEditorPanel);
		String pathSaveReports = "/icons/save_report.png";  
		URL urlSaveReports = this.getClass().getResource(pathSaveReports);  
		ImageIcon iconSaveReports = new ImageIcon(urlSaveReports);
		saveReportsButton.setIcon(iconSaveReports);
	}
	
	private void createQuitButton() {
		quitButton = new JButton();
		quitButton.setActionCommand("QUIT");
		quitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		String pathQuit = "/icons/exit.png";  
		URL urlQuit = this.getClass().getResource(pathQuit);  
		ImageIcon iconQuit = new ImageIcon(urlQuit);
		quitButton.setIcon(iconQuit);		
	}

	@Override
	public void registered(int time, RoadMap map, List<Event> events) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void simulatorError(int time, RoadMap map, List<Event> events, SimulatorError e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void advanced(int time, RoadMap map, List<Event> events) {
		timeTextField.setText("" + time);
	}

	@Override
	public void eventAdded(int time, RoadMap map, List<Event> events) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reset(int time, RoadMap map, List<Event> events) {
		timeTextField.setText("0");
		
	}
}
