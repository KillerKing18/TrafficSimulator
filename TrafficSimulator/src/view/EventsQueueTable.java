package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import model.Event;
import model.RoadMap;
import model.SimulatorError;
import model.TrafficSimulatorObserver;

public class EventsQueueTable extends JPanel implements TrafficSimulatorObserver {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	MyTableModel tableModel_;
	RoadMap map_;
	List<Event> events_;
	
	class MyTableModel extends AbstractTableModel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		String[] header = { "#", "Time", "Type" };
		
		@Override
		public int getColumnCount() {
			return header.length;
		}
		
		@Override
		public String getColumnName(int columnIndex) {
			
			return header[columnIndex];
		}
	
		@Override
		public int getRowCount() {
			return events_ == null ? 0 : events_.size();
		}
		
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			String v = null;
			switch (columnIndex) {
			case 0:
				v = "" + events_.get(rowIndex).getPositionIndex();
				break;
			case 1:
				v = "" + events_.get(rowIndex).getScheduledTime();
				break;
			case 2:
				v = events_.get(rowIndex).toString();
				break;
			default:
				break;
			}
			return v;
		}
		
		void refresh() {
			fireTableStructureChanged();
		}
	}
	
	public EventsQueueTable() {
		initGUI();
	}
	
	private void initGUI() {
		this.setLayout( new BorderLayout() );
		tableModel_ = new MyTableModel();
		JTable t = new JTable(tableModel_); //t registra tableModel como un listener
		t.setShowGrid(false);
		JScrollPane jscroll = new JScrollPane(t);
		jscroll.getViewport().setBackground(Color.WHITE);
		this.add(jscroll, BorderLayout.CENTER);
	}

	@Override
	public void registered(int time, RoadMap map, List<Event> events) {
		map_ = map;
		events_ = events;
		tableModel_.refresh();
	}

	@Override
	public void simulatorError(int time, RoadMap map, List<Event> events, SimulatorError e) {
		map_ = map;
		events_ = events;
		tableModel_.refresh();
	}

	@Override
	public void advanced(int time, RoadMap map, List<Event> events) {
		map_ = map;
		events_ = events;
		tableModel_.refresh();
	}

	@Override
	public void eventAdded(int time, RoadMap map, List<Event> events) {
		map_ = map;
		events_ = events;
		tableModel_.refresh();
	}

	@Override
	public void reset(int time, RoadMap map, List<Event> events) {
		map_ = map;
		events_ = events;
		tableModel_.refresh();
	}	
}