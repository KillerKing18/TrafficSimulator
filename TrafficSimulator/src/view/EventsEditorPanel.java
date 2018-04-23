package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;

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

public class EventsEditorPanel extends TextAreaPanel{
	
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

	public EventsEditorPanel(String title, String text, boolean editable){
		super(title, editable);
		this.setText(text);
		JPopupMenu _editorPopupMenu = new JPopupMenu();
		
		JMenuItem clearOption = new JMenuItem("Clear");
		clearOption.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EventsEditorPanel.this.clear();
			}
		});

		JMenuItem loadOption = new JMenuItem("Load");
		loadOption.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO 
			}
		});
		
		JMenuItem saveOption = new JMenuItem("Save");
		saveOption.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO 
			}
		});

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
}
