package racingview;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.Kart;
import view.GenericTable;

public class ClassificationTable extends GenericTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	class MyClassificationTableModel extends MyGenericTableModel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		public MyClassificationTableModel() {
			header = new String[]{ "#", "Name", "Speed", "Lap", "Icon" };
		}
		
		@Override
		public int getRowCount() {
			return map == null ? 0 : map.getVehicles().size();
		}
		
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			String v = null;
			switch (columnIndex) {
			case 0:
				 v = "" + (rowIndex + 1);
				 break;
			case 1:
				 v = map.getVehicles().get(rowIndex).getId();
				 break;
			case 2:
				 v = "" + map.getVehicles().get(rowIndex).getSpeed();
				 break;
			case 3:
				v = "" + ((Kart) map.getVehicles().get(rowIndex)).getLap();
				 break;
			case 4:
				 ImageIcon icon = new ImageIcon(getClass().getResource("/images/" + map.getVehicles().get(rowIndex).getId() + ".png"));
				 icon.setImage(icon.getImage().getScaledInstance(90, 90, 1));
				 return icon;
			default:
				break;
			}
			return v;
		}
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		@Override
		public Class getColumnClass(int column)
        {
			return getValueAt(0, column).getClass();
        }
	}
	
	public ClassificationTable() {
		initGUI();
	}
	
	protected void initGUI() {
		tableModel = new MyClassificationTableModel();
		this.setLayout(new BorderLayout());
		JTable t = new JTable(tableModel);
		t.setShowGrid(false);
		JScrollPane jscroll = new JScrollPane(t);
		jscroll.getViewport().setBackground(Color.WHITE);
		this.add(jscroll, BorderLayout.CENTER);
		t.setRowHeight(90);
	}
}