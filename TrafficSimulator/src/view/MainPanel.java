package view;


import graphlayout.Graph;
import graphlayout.GraphComponent;

import java.awt.BorderLayout;
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

public class MainPanel extends JFrame{
	
	private TrafficSimulator _model;
	private Controller _control;
	private File _inFile;
	
	
	
	private JPanel _mainPanel;
	
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
	private GraphComponent _graphComp;
	
	
	private MenuBar _menuBar;

	
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
		_downRightPanel = new JPanel(new BorderLayout());		
		_graphComp = new GraphComponent();
		_downRightPanel.add(_graphComp, BorderLayout.CENTER);
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
		_eventsEditor = new EventsEditorPanel("Events: ", "", true, _inFile);
	}
	
	private void createEventsQueue() {
		_eventsQueue = new EventsQueueTable(_model);
		_eventsQueue.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Events Queue"));
	}
	
	private void createReportsArea() {
		_reportsArea = new ReportsAreaPanel("Reports", false, _control);
	}
	
	public void clearReports() {
		_reportsArea.clear();
	}
	
	protected void generateGraph() {
/* TODO
		Graph g = new Graph();
		int numNodes = _rand.nextInt(20)+5;
		int numEdges = _rand.nextInt(2*numNodes);		
		
		for (int i=0; i<numNodes; i++) {
			g.addNode(new Node("n"+i));
		}
		
		for (int i=0; i<numEdges; i++) {
			int s = _rand.nextInt(numNodes);
			int t = _rand.nextInt(numNodes);
			if ( s == t ) {
				t = (t + 1) % numNodes;
			}
			int l = _rand.nextInt(30)+20;
			Edge e = new Edge("e"+i, g.getNodes().get(s), g.getNodes().get(t), l);
			
			int numDots = _rand.nextInt(5);
			for(int j=0; j<numDots; j++) {
				l = Math.max(0, _rand.nextBoolean() ? l/2 : l);
				e.addDot( new Dot("d"+j, l));
			}
			
			g.addEdge(e);
		}
		
		_graphComp.setGraph(g);
*/
	}
}
