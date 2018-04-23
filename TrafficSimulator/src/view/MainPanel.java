package view;


import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.swing.*;

import model.Event;
import model.RoadMap;
import model.SimulatorError;
import model.TrafficSimulator;
import model.TrafficSimulatorObserver;
import control.Controller;

public class MainPanel extends JFrame implements TrafficSimulatorObserver {
	
	private Controller _control;
	private RoadMap _map; // observer update methods
	private int _time; // observer update methods
	private List<Event> _events; // observer update methods
	private OutputStream _reportsOutputStream;
	private File _inFile;
	
	
	
	private JPanel _mainPanel;
	
	
	private JPanel _topPanel;
	
	private EventsEditorPanel _eventsEditor;
	private ReportsAreaPanel _reportsArea;
	
	// Down Panel
	private JPanel _downPanel;
		// Down Left Panel
	private JPanel _downLeftPanel;
			// Vehicles Table
	private JPanel _vehiclesTablePanel;
	private JTable _vehiclesTable;
	private VehiclesTableModel _vehiclesTableModel;
			// Roads Table
	private JPanel _roadsTablePanel;
	private JTable _roadsTable;
	private RoadsTableModel _roadsTableModel;
			// Junctions Table
	private JPanel _junctionsTablePanel;
	private JTable _junctionsTable;
	private JunctionsTableModel _junctionsTableModel;
		// Down Right Panel
	private JPanel _downRightPanel;
	
	
	private MenuBar _menuBar;

	
	public MainPanel(TrafficSimulator model, String inFile, Controller control) throws IOException {
		super("Traffic Simulator");
		_control = control;
		_inFile = inFile == null ? null : new File(inFile);
		initGUI();
		model.addObserver(this);
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
		
		// Top Panel
		createTopPanel();
		_mainPanel.add(_topPanel);
		
		// Down Panel
		createDownPanel();
		_mainPanel.add(_downPanel);
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
	}
	
	private void createDownLeftPanel() {
		_downLeftPanel = new JPanel();
		_downLeftPanel.setLayout(new BoxLayout(_downLeftPanel, BoxLayout.Y_AXIS));
		
		// Vehicles Table
		createVehiclesTable();
		_downLeftPanel.add(_vehiclesTablePanel);
		
		// Roads Table
		createRoadsTable();
		_downLeftPanel.add(_roadsTablePanel);
		
		// Junctions Table
		createJunctionsTable();
		_downLeftPanel.add(_junctionsTablePanel);
	}
	
	private void createVehiclesTable() {
		_vehiclesTablePanel = new JPanel();
		_vehiclesTableModel = new VehiclesTableModel();
		_vehiclesTable = new JTable(_vehiclesTableModel);
		_vehiclesTablePanel.add(new JScrollPane(_vehiclesTable));
		_vehiclesTablePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Vehicles"));
	}
	
	private void createRoadsTable() {
		_roadsTablePanel = new JPanel();
		_roadsTableModel = new RoadsTableModel();
		_roadsTable = new JTable(_roadsTableModel);
		_roadsTablePanel.add(new JScrollPane(_roadsTable));
		_roadsTablePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Roads"));
	}
	
	private void createJunctionsTable() {
		_junctionsTablePanel = new JPanel();
		_junctionsTableModel = new JunctionsTableModel();
		_junctionsTable = new JTable(_junctionsTableModel);
		_junctionsTablePanel.add(new JScrollPane(_junctionsTable));
		_junctionsTablePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Junctions"));
	}
	
	private void createTopPanel() throws IOException {
		_topPanel = new JPanel();
		_topPanel.setLayout(new BoxLayout(_topPanel, BoxLayout.X_AXIS));
		
		// Events Editor
		createEventsEditor();
		_topPanel.add(_eventsEditor);
		
		// Reports Area
		createReportsArea();
		_topPanel.add(_reportsArea);
	}
	
	private void createEventsEditor() throws IOException {
		_eventsEditor = new EventsEditorPanel("Events: ", "", true, _inFile);
	}
	
	private void createReportsArea() {
		_reportsArea = new ReportsAreaPanel("Reports", false, _control);
	}

	
	
	public void clearReports() {
		_reportsArea.clear();
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eventAdded(int time, RoadMap map, List<Event> events) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reset(int time, RoadMap map, List<Event> events) {
		// TODO Auto-generated method stub
		
	}

	
}
