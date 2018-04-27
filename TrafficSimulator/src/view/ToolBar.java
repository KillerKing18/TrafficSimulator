package view;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SpinnerNumberModel;

import model.Event;
import model.RoadMap;
import model.SimulatorError;
import model.TrafficSimulatorObserver;

public class ToolBar extends JToolBar implements TrafficSimulatorObserver {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static String[] _songs = {"BowserCastleMarioKart",
			"Kirby",
			"MooMooFarmMarioKart",
			"MountWarioMarioKart",
			"SuperMarioWorldAthletic",
			"SweetSweetCanyonMarioKart",
			"ToadHarborMarioKart",
			"WarioGoldMineMarioKart",
			"YoshiCircuitMarioKart"};
	
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
	private JButton playMusicButton;
	private JButton stopMusicButton;
	private JButton randomMusicButton;
	private JComboBox<String> playList;
	private JButton generateReportsButton;
	private JButton clearReportsAreaButton;
	private JButton saveReportsButton;
	private JButton quitButton;
	
	private MainPanel _mainPanel;
	private EventsEditorPanel _eventsEditorPanel;
	private ReportsAreaPanel _reportsArea;
	
	public ToolBar(MainPanel mainPanel, EventsEditorPanel eventsEditorPanel, ReportsAreaPanel reportsArea) {
		super();
		_mainPanel = mainPanel;
		_eventsEditorPanel = eventsEditorPanel;
		_reportsArea = reportsArea;
		initGUI();
	}

	private void initGUI() {
		loadEventsButton = new JButton();
		createGenericButton(loadEventsButton, "LOAD", _eventsEditorPanel, "/icons/open.png");
		this.add(loadEventsButton);
		
		saveEventsButton = new JButton();
		createGenericButton(saveEventsButton, "SAVE", _eventsEditorPanel, "/icons/save.png");
		this.add(saveEventsButton);
		
		clearEventsButton = new JButton();
		createGenericButton(clearEventsButton, "CLEAR", _eventsEditorPanel, "/icons/clear.png");
		this.add(clearEventsButton);
		
		this.addSeparator();
		
		checkInEventsButton = new JButton();
		createGenericButton(checkInEventsButton, "CHECK IN", _eventsEditorPanel, "/icons/events.png");
		this.add(checkInEventsButton);
		
		runButton = new JButton();
		createGenericButton(runButton, "RUN", _mainPanel, "/icons/play.png");
		this.add(runButton);
		
		
		resetButton = new JButton();
		createGenericButton(resetButton, "RESET", _mainPanel, "/icons/reset.png");
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
		
		playMusicButton = new JButton();
		createGenericButton(playMusicButton, "PLAY", _mainPanel, "/icons/play_music.png");
		this.add(playMusicButton);
		
		stopMusicButton = new JButton();
		createGenericButton(stopMusicButton, "STOP", _mainPanel, "/icons/stop_music.png");
		this.add(stopMusicButton);
		
		randomMusicButton = new JButton();
		createGenericButton(randomMusicButton, "RANDOM", _mainPanel, "/icons/random_music.png");
		this.add(randomMusicButton);
		
		playList = new JComboBox<String>(_songs);
		playList.setSelectedIndex(0);
		playList.setActionCommand("PLAYLIST");
		playList.addActionListener(_mainPanel);
		this.add(playList);
		
		this.addSeparator();
		
		generateReportsButton = new JButton();
		createGenericButton(generateReportsButton, "GENERATE", _reportsArea, "/icons/report.png");
		this.add(generateReportsButton);
		
		clearReportsAreaButton = new JButton();
		createGenericButton(clearReportsAreaButton, "CLEAR", _reportsArea, "/icons/delete_report.png");
		this.add(clearReportsAreaButton);
		
		saveReportsButton = new JButton();
		createGenericButton(saveReportsButton, "SAVE", _reportsArea, "/icons/save_report.png");
		this.add(saveReportsButton);
		
		this.addSeparator();
		
		quitButton = new JButton();
		createGenericButton(quitButton, "QUIT", _mainPanel, "/icons/exit.png");
		this.add(quitButton);
	}
	
	private void createGenericButton(JButton button, String actionCommand, ActionListener actionListener, String path) {
		button.setActionCommand(actionCommand);
		button.addActionListener(actionListener);
		button.setIcon(new ImageIcon(this.getClass().getResource(path)));
	}
	
	public int getTime(){
		return (Integer)stepsSpinner.getValue();
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
	
	public JComboBox<String> getComboBox() {
		return playList;
	}

	@Override
	public void registered(int time, RoadMap map, List<Event> events) {
		timeTextField.setText("0");
		
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
