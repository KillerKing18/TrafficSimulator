package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;

import control.Controller;
import control.EventBuilder;
import control.MakeVehicleFaultyEventBuilder;
import control.NewBikeEventBuilder;
import control.NewCarEventBuilder;
import control.NewDirtRoadEventBuilder;
import control.NewJunctionEventBuilder;
import control.NewLanesRoadEventBuilder;
import control.NewMostCrowdedJunctionEventBuilder;
import control.NewRoadEventBuilder;
import control.NewRoundRobinJunctionEventBuilder;
import control.NewVehicleEventBuilder;
import model.SimulatorError;

public class EventsEditorPanel extends TextAreaPanel implements ActionListener{
	
	private Controller _control;
	
	private File _inFile;
	
	private final String EVENTS = "Events: ";
	
	private static EventBuilder[] _events = {new NewMostCrowdedJunctionEventBuilder(), 
		new NewRoundRobinJunctionEventBuilder(),
		new NewJunctionEventBuilder(),
		new NewLanesRoadEventBuilder(),
		new NewDirtRoadEventBuilder(),
		new NewRoadEventBuilder(),
		new NewBikeEventBuilder(),
		new NewCarEventBuilder(),
		new NewVehicleEventBuilder(),
		new MakeVehicleFaultyEventBuilder()};

	public EventsEditorPanel(String title, String text, boolean editable, File inFile, Controller control) throws IOException{
		super(title, editable);
		_control = control;
		_inFile = inFile;
		if(_inFile != null)
			setText(readFile());
		_fc = new JFileChooser();
		JPopupMenu _editorPopupMenu = new JPopupMenu();
		
		JMenuItem clearOption = new JMenuItem("Clear");
		clearOption.setActionCommand("CLEAR");
		clearOption.addActionListener(this);

		JMenuItem loadOption = new JMenuItem("Load");
		loadOption.setActionCommand("LOAD");
		loadOption.addActionListener(this);
		
		JMenuItem saveOption = new JMenuItem("Save");
		saveOption.setActionCommand("SAVE");
		saveOption.addActionListener(this);

		JMenu subMenu = new JMenu("Add Template");

		for (EventBuilder eb : _events) {
			JMenuItem menuItem = new JMenuItem(eb.toString());
			menuItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					EventsEditorPanel.this.insert(eb.template() + "\n");
				}
			});
			subMenu.add(menuItem);
		}
		
		_editorPopupMenu.add(subMenu);
		_editorPopupMenu.addSeparator();
		_editorPopupMenu.add(loadOption);
		_editorPopupMenu.add(saveOption);
		_editorPopupMenu.add(clearOption);
		

		// connect the popup menu to the text area _editor
		textArea.addMouseListener(new MouseListener() {

			@Override
			public void mousePressed(MouseEvent e) {
				showPopup(e);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				showPopup(e);
			}

			private void showPopup(MouseEvent e) {
				if (e.isPopupTrigger() && _editorPopupMenu.isEnabled()) {
					_editorPopupMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		switch (str){
		case "LOAD":
			loadFile();
			break;
		case "SAVE":
			saveFile();
			break;
		case "CHECK IN":
			_control.setInputStream(new ByteArrayInputStream(this.getText().getBytes()));
			try {
				_control.loadEvents();
			} catch (SimulatorError e1) {
				e1.printStackTrace();
			}
			break;
		case "CLEAR":
			clear();
			break;
		}
		
	}

	public void loadFile() {
		int returnVal = this._fc.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			_inFile = this._fc.getSelectedFile();
			try {
				setText(readFile());
				// TODO panelBarraEstado.setMensaje("Fichero " + fichero.getName() + " de eventos cargado into the editor");
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void setText(String text) {
		textArea.setText(text);
		setBorder(BorderFactory.createTitledBorder
				(BorderFactory.createLineBorder(Color.BLACK), EVENTS + _inFile.getName()));
	}

	private String readFile() throws IOException {
		FileReader fr = new FileReader(_inFile);
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

	public void saveFile() {
		// TODO Auto-generated method stub
		
	}

}
