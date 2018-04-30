package racingview;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class SelectedCharactersTable extends GenericRacingTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	class MySelectedCharactersTableModel extends MyGenericRacingTableModel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		public MySelectedCharactersTableModel() {
			header = new String[]{ "Name", "Speed", "Luck", "Icon" };
		}
		
		@Override
		public int getRowCount() {
			return _imagesPanel.getSelectedCharacters().size();
		}
		
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			String v = null;
			switch (columnIndex) {
			case 0:
				 v = _imagesPanel.getSelectedCharacters().get(rowIndex);
				 break;
			case 1:
				 v = _characterChooserPanel.getSpeedMap().get(_imagesPanel.getSelectedCharacters().get(rowIndex)).toString();
				 break;
			case 2:				
				v = _characterChooserPanel.getLuckMap().get(_imagesPanel.getSelectedCharacters().get(rowIndex)).toString();
				 break;
			case 3:
				ImageIcon icon = new ImageIcon(getClass().getResource("/images/" + _imagesPanel.getSelectedCharacters().get(rowIndex) + ".png"));
				icon.setImage(icon.getImage().getScaledInstance(42, 42, 1));
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
	
	public SelectedCharactersTable() {
		initGUI();
	}
	
	protected void initGUI() {
		tableModel = new MySelectedCharactersTableModel();
		this.setLayout(new BorderLayout());
		JTable t = new JTable(tableModel);
		t.setShowGrid(false);
		JScrollPane jscroll = new JScrollPane(t);
		jscroll.getViewport().setBackground(Color.WHITE);
		this.add(jscroll, BorderLayout.CENTER);
		t.setRowHeight(42);
	}
}