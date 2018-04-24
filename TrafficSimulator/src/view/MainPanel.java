package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

import model.TrafficSimulator;
import control.Controller;

public class MainPanel extends JFrame {
	
	private TrafficSimulator _model;
	private Controller _control;
	private File _inFile;
	
	private JPanel _mainPanel;
	private MenuBar _menuBar;
	private ToolBar _toolBar;
	
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
		initGUI();
	}

	void initGUI() throws IOException{
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
		_menuBar = new MenuBar(_eventsEditor, _control);
	}
	
	private void createMainPanel() throws IOException {
		_mainPanel = new JPanel();
		_mainPanel.setLayout(new BoxLayout(_mainPanel, BoxLayout.Y_AXIS));
		
		createTopPanel();
		
		createDownPanel();
		
		createToolBar();
		
		_mainPanel.add(_toolBar);
		
		_mainPanel.add(_topPanel);
		
		_mainPanel.add(_downPanel);
	}
	
	private void createToolBar() {
		_toolBar = new ToolBar(_eventsEditor, _reportsArea, _control, _model);
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
		_roadmapGraph = new RoadMapGraph(_model);
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
		_vehiclesTable = new VehiclesTable(_model);
		_vehiclesTable.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Vehicles"));
	}
	
	private void createRoadsTable() {
		_roadsTable = new RoadsTable(_model);
		_roadsTable.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Roads"));
	}
	
	private void createJunctionsTable() {
		_junctionsTable = new JunctionsTable(_model);
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
		_eventsQueue = new EventsQueueTable(_model);
		_eventsQueue.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Events Queue"));
	}
	
	private void createReportsArea() {
		_reportsArea = new ReportsAreaPanel("Reports", false, _control);
	}


}
