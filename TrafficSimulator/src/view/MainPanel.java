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
	//PRUEBA COMMIT
	
	private final String EVENTS = "Events: ";
	
	private JPanel _mainPanel;
	private JPanel _topPanel;
	private JPanel _centerPanel;
	private JFileChooser _fc;
	private EventsEditorPanel _eventsEditor;
	private ReportsAreaPanel _reportsArea;
	private MenuBar _menuBar;

	
	public MainPanel(TrafficSimulator model, String inFile, Controller control) throws IOException {
		super("Traffic Simulator");
		_control = control;
		_fc = new JFileChooser();
		_inFile = inFile == null ? null : new File(inFile);
		initGUI();
		model.addObserver(this);
	}

	void initGUI() throws IOException{
		this.setTitle("Traffic Simulator");
		
		// Menu Bar
		createMenuBar();
		this.setJMenuBar(_menuBar);
		
		// 
		
		// Main Panel
		createMainPanel();
		this.setContentPane(_mainPanel);
				
		
		this.setSize(900,900);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void createMenuBar() {
		_menuBar = new MenuBar(this, _control);
	}
	
	private void createMainPanel() throws IOException {
		_mainPanel = new JPanel();
		_mainPanel.setLayout(new BoxLayout(_mainPanel, BoxLayout.Y_AXIS));
		
		// Top Panel
		createTopPanel();
		_mainPanel.add(_topPanel);
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
		_eventsEditor = new EventsEditorPanel(EVENTS, "", true);
		if(_inFile != null) {
			_eventsEditor.setText(readFile(_inFile));
			_eventsEditor.setBorder(BorderFactory.createTitledBorder
				(BorderFactory.createLineBorder(Color.BLACK), EVENTS + _inFile.getName()));
		}
	}
	
	private void createReportsArea() {
		_reportsArea = new ReportsAreaPanel("Reports", false, _control);
	}

	public void loadFile() {
		int returnVal = this._fc.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = this._fc.getSelectedFile();
			try {
				String str = readFile(file);
				// TODO _control.restart();
				_inFile = file;
				_eventsEditor.setText(str);
				_eventsEditor.setBorder(BorderFactory.createTitledBorder
						(BorderFactory.createLineBorder(Color.BLACK), EVENTS + _inFile.getName()));
				// TODO panelBarraEstado.setMensaje("Fichero " + fichero.getName() + " de eventos cargado into the editor");
			}
			catch (IOException e) {
				// TODO
			}
		}
	}

	private String readFile(File file) throws IOException {
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String str = "";
		String finalStr = "";
		str = br.readLine();
		while(str != null) {
			if(!finalStr.equals(""))
				finalStr += "\n";
			finalStr = finalStr + str;
			str = br.readLine();
		}
		return finalStr;
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
