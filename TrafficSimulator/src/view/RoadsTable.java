package view;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import model.Event;
import model.RoadMap;
import model.SimulatorError;
import model.TrafficSimulator;
import model.TrafficSimulatorObserver;

public class RoadsTable extends JPanel implements TrafficSimulatorObserver {

	MyTableModel tableModel_;
	RoadMap map_;
	
	class MyTableModel extends AbstractTableModel {
		String[] header = { "ID", "Source", "Target", "Length", "Max Speed", "Vehicles" };
		
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
			return map_ == null ? 0 : map_.getRoads().size();
		}
		
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			String v = null;
			switch ( columnIndex ) {
			case 0:
				 v = map_.getRoads().get(rowIndex).getId();
				 break;
			case 1:
				 v = map_.getRoads().get(rowIndex).getSource().getId();
				 break;
			case 2:
				 v = map_.getRoads().get(rowIndex).getDestination().getId();
				 break;
			case 3:
				 v = "" + map_.getRoads().get(rowIndex).getLength();
				 break;
			case 4:
				 v = "" + map_.getRoads().get(rowIndex).getMaxSpeed();
				 break;
			case 5:
				 v = "" + map_.getRoads().get(rowIndex).getVehiclesString();
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
	
	public RoadsTable(TrafficSimulator model) {
		initGUI();
		model.addObserver(this);
	}
	
	private void initGUI() {
		this.setLayout(new BorderLayout());
		tableModel_ = new MyTableModel();
		JTable t = new JTable(tableModel_); //t registra tableModel como un listener
		this.add(new JScrollPane(t) , BorderLayout.CENTER);
	}

	@Override
	public void registered(int time, RoadMap map, List<Event> events) {
		map_ = map;
		tableModel_.refresh();
	}

	@Override
	public void simulatorError(int time, RoadMap map, List<Event> events,
			SimulatorError e) {
		map_ = map;
		tableModel_.refresh();
	}

	@Override
	public void advanced(int time, RoadMap map, List<Event> events) {
		map_ = map;
		tableModel_.refresh();
	}

	@Override
	public void eventAdded(int time, RoadMap map, List<Event> events) {
		map_ = map;
		tableModel_.refresh();
	}

	@Override
	public void reset(int time, RoadMap map, List<Event> events) {
		map_ = map;
		tableModel_.refresh();
	}	
}