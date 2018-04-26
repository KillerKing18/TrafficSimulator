package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

import model.TrafficSimulator;
import music.Music;
import control.Controller;
import javax.sound.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MainPanel extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Music music;
	private TrafficSimulator _model;
	private Controller _control;
	private File _inFile;
	
	private JPanel _mainPanel;
	private MenuBar _menuBar;
	private ToolBar _toolBar;
	private StateBarPanel _stateBar;
	
	// Top Panel
	private JPanel _topPanel;
		// Events Editor
	private EventsEditorPanel _eventsEditor;
		// Events Queue
	private EventsQueueTable _eventsQueue;
		//Reports Area
	private ReportsAreaPanel _reportsArea;
	// Down Panel
	private JPanel _downPanel;
		// Down Left Panel
	private JPanel _downLeftPanel;
			// Vehicles Table
	private VehiclesTable _vehiclesTable;
			// Roads Table
	private RoadsTable _roadsTable;
			// Junctions Table
	private JunctionsTable _junctionsTable;
		// Down Right Panel
	private JPanel _downRightPanel;
	private RoadMapGraph _roadmapGraph;
	
	public MainPanel(TrafficSimulator model, String inFile, Controller control) throws IOException {
		super("Traffic Simulator");
		_control = control;
		_model = model;
		_inFile = inFile == null ? null : new File(inFile);
		try{
			initGUIandMusic();
		} catch (IOException e) {
			_stateBar.setMessage("Error initializing the GUI!");
			JOptionPane.showMessageDialog(this, "Problems initializing the GUI", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	void initGUIandMusic() throws IOException{
		
		//Music
		music = new Music("songHC.wav");
		music.loop();
	
		//GUI
		
		this.setTitle("Traffic Simulator");
			
		// Main Panel
		createMainPanel();
		this.setContentPane(_mainPanel);
				
		// Menu Bar
		createMenuBar();
		this.setJMenuBar(_menuBar);
		
		this.setSize(900,900);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void createMenuBar() {
		_menuBar = new MenuBar(_eventsEditor, _reportsArea, _toolBar, _control);
	}
	
	private void createMainPanel() throws IOException {
		_mainPanel = new JPanel();
		_mainPanel.setLayout(new BoxLayout(_mainPanel, BoxLayout.Y_AXIS));
		
		createTopPanel();
		
		createDownPanel();
		
		createToolBar();
		
		createStateBar();
		
		_mainPanel.add(_toolBar);
		
		_mainPanel.add(_topPanel);
		
		_mainPanel.add(_downPanel);
		
		_mainPanel.add(_stateBar);
	}
	
	private void createStateBar() {
		_stateBar = new StateBarPanel();
		_model.addObserver(_stateBar);
	}
	
	private void createToolBar() {
		_toolBar = new ToolBar(_eventsEditor, _reportsArea, _control, _model);
		_model.addObserver(_toolBar);
	}
	
	private void createDownPanel() {
		_downPanel = new JPanel();
		_downPanel.setLayout(new BoxLayout(_downPanel, BoxLayout.X_AXIS));
		
		createDownLeftPanel();
		_downPanel.add(_downLeftPanel);
		
		createDownRightPanel();
		_downPanel.add(_downRightPanel);		
	}
	
	private void createDownRightPanel() {
		_downRightPanel = new JPanel();	
		_downRightPanel.setLayout(new BorderLayout());
		
		createRoadMapGraph();
		_downRightPanel.add(_roadmapGraph, BorderLayout.CENTER);
	}
	
	private void createRoadMapGraph() {
		_roadmapGraph = new RoadMapGraph();
		_model.addObserver(_roadmapGraph);
	}
	
	private void createDownLeftPanel() {
		_downLeftPanel = new JPanel();
		_downLeftPanel.setLayout(new BoxLayout(_downLeftPanel, BoxLayout.Y_AXIS));
		
		// Vehicles Table
		createVehiclesTable();
		_downLeftPanel.add(_vehiclesTable);
		
		// Roads Table
		createRoadsTable();
		_downLeftPanel.add(_roadsTable);
		
		// Junctions Table
		createJunctionsTable();
		_downLeftPanel.add(_junctionsTable);
	}
	
	private void createVehiclesTable() {
		_vehiclesTable = new VehiclesTable();
		_model.addObserver(_vehiclesTable);
		_vehiclesTable.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Vehicles"));
	}
	
	private void createRoadsTable() {
		_roadsTable = new RoadsTable();
		_model.addObserver(_roadsTable);
		_roadsTable.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Roads"));
	}
	
	private void createJunctionsTable() {
		_junctionsTable = new JunctionsTable();
		_model.addObserver(_junctionsTable);
		_junctionsTable.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Junctions"));
	}
	
	private void createTopPanel() throws IOException {
		_topPanel = new JPanel();
		_topPanel.setLayout(new BoxLayout(_topPanel, BoxLayout.X_AXIS));
		
		// Events Editor
		createEventsEditor();
		_topPanel.add(_eventsEditor);
		
		// Events Queue
		createEventsQueue();
		_topPanel.add(_eventsQueue);
		
		// Reports Area
		createReportsArea();
		_topPanel.add(_reportsArea);
	}
	
	private void createEventsEditor() throws IOException {
		_eventsEditor = new EventsEditorPanel("Events: ", "", true, _inFile, _control);
	}
	
	private void createEventsQueue() {
		_eventsQueue = new EventsQueueTable();
		_model.addObserver(_eventsQueue);
		_eventsQueue.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Events Queue"));
	}
	
	private void createReportsArea() {
		_reportsArea = new ReportsAreaPanel(this, "Reports", false);
		_model.addObserver(_reportsArea);
	}


}
