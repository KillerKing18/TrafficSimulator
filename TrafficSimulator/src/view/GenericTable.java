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

public abstract class GenericTable extends JPanel implements TrafficSimulatorObserver {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected MyGenericTableModel tableModel;
	protected RoadMap map;
	protected List<Event> events;
	
	public abstract class MyGenericTableModel extends AbstractTableModel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		protected String[] header;
		
		@Override
		public int getColumnCount() {
			return header.length;
		}
		
		@Override
		public String getColumnName(int columnIndex) {
			return header[columnIndex];
		}
		
		void refresh() {
			fireTableStructureChanged();
		}

		@Override
		public int getRowCount() {
			return 0;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			return null;
		}
	}
	
	protected void initGUI() {
		this.setLayout(new BorderLayout());
		JTable t = new JTable(tableModel);  //t registra tableModel como un listener
		t.setShowGrid(false);
		JScrollPane jscroll = new JScrollPane(t);
		jscroll.getViewport().setBackground(Color.WHITE);
		this.add(jscroll, BorderLayout.CENTER);
	}

	@Override
	public void registered(int time, RoadMap map, List<Event> events) {
		this.map = map;
		tableModel.refresh();
	}

	@Override
	public void simulatorError(int time, RoadMap map, List<Event> events, SimulatorError e) {
	}

	@Override
	public void advanced(int time, RoadMap map, List<Event> events) {
		this.events = events;
		tableModel.refresh();
	}

	@Override
	public void eventAdded(int time, RoadMap map, List<Event> events) {
		this.events = events;
		tableModel.refresh();
	}

	@Override
	public void reset(int time, RoadMap map, List<Event> events) {
		this.events = events;
		tableModel.refresh();
	}	
}