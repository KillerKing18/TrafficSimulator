package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import control.Controller;
import model.SimulatorError;

public class MenuBar extends JMenuBar{
	
	private EventsEditorPanel _eventsEditorPanel;
	private final String LOAD_EVENTS = "load events";
	private final String SAVE_EVENTS = "save events";
	private final String SAVE_REPORT = "save reports";
	private final String EXIT = "exit";
	private final String GENERATE = "generate reports";
	private final String CLEAR = "clear reports";
	private final String RUN = "save events";
	private final String RESET = "reset";
	private final String REDIRECT_OUTPUT = "redirect output";
	
	private Controller _control;
	
	public MenuBar(EventsEditorPanel eventsEditorPanel, Controller control){
		super();
		_control = control;
		_eventsEditorPanel = eventsEditorPanel;
		JMenu file = createFileMenu();
		this.add(file);
		JMenu simulator = createSimulatorMenu();
		this.add(simulator);
		JMenu reports = createReportsMenu();
		this.add(reports);
	}
	
	public JMenu createFileMenu() {
		JMenu file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F);
		
		JMenuItem loadEvents, saveEvents, checkinEvents, saveReport, exit;
		// TODO Hacer funcion generica para crear los JMenuItem

		loadEvents = new JMenuItem("Load Events");
		loadEvents.setActionCommand("LOAD");
		loadEvents.addActionListener(_eventsEditorPanel);
		loadEvents.setMnemonic(KeyEvent.VK_L);
		loadEvents.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,
				ActionEvent.ALT_MASK));

		saveEvents = new JMenuItem("Save Events");
		saveEvents.setActionCommand("SAVE");
		saveEvents.addActionListener(_eventsEditorPanel);
		saveEvents.setMnemonic(KeyEvent.VK_S);
		saveEvents.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				ActionEvent.ALT_MASK));
		
		checkinEvents = new JMenuItem("Check-in Events");
		checkinEvents.setActionCommand("CHECK IN");
		checkinEvents.addActionListener(_eventsEditorPanel);
		checkinEvents.setMnemonic(KeyEvent.VK_C);
		checkinEvents.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
				ActionEvent.ALT_MASK));
		
		saveReport = new JMenuItem("Save Report");
		saveReport.setActionCommand(SAVE_REPORT);
		//TODO saveReport.addActionListener();
		saveReport.setMnemonic(KeyEvent.VK_R);
		saveReport.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,
				ActionEvent.ALT_MASK));
		
		exit = new JMenuItem("Exit");
		exit.setActionCommand(EXIT);
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
			
		});
		exit.setMnemonic(KeyEvent.VK_E);
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
				ActionEvent.ALT_MASK));

		file.add(loadEvents);
		file.add(saveEvents);
		file.add(checkinEvents);
		file.addSeparator();
		file.add(saveReport);
		file.addSeparator();
		file.add(exit);
		
		return file;
	}
	
	public JMenu createReportsMenu() {
		JMenu reports = new JMenu("Reports");
		reports.setMnemonic(KeyEvent.VK_R);
		
		JMenuItem generate, clear;

		generate = new JMenuItem("Generate");
		generate.setActionCommand(GENERATE);
		//TODO generate.addActionListener();

		clear = new JMenuItem("Clear");
		clear.setActionCommand(CLEAR);
		clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO _mainPanel.clearReports();
			}
			
		});

		reports.add(generate);
		reports.add(clear);
		
		return reports;
	}

	public JMenu createSimulatorMenu() {
		JMenu simulator = new JMenu("Simulator");
		simulator.setMnemonic(KeyEvent.VK_S);
		
		JMenuItem run, reset, redirectOutput;

		run = new JMenuItem("Run");
		run.setActionCommand(RUN);
		run.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					_control.run(1);
				} catch (IOException | SimulatorError e1) {
					e1.printStackTrace();
				}
			}
		});

		reset = new JMenuItem("Reset");
		run.setActionCommand(RESET);
		//TODO reset.addActionListener();
		
		redirectOutput = new JMenuItem("Redirect Output");
		redirectOutput.setActionCommand(REDIRECT_OUTPUT);
		//TODO redirectOutput.addActionListener();

		simulator.add(run);
		simulator.add(reset);
		simulator.add(redirectOutput);
		
		return simulator;
	}
}
