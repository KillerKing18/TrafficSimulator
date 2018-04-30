package racingview;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SelectedCupTable extends GenericRacingTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	class MySelectedCupTableModel extends MyGenericRacingTableModel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		public MySelectedCupTableModel() {
			header = new String[]{ "Race", "Name" };
		}
		
		@Override
		public int getRowCount() {
			if(_cupChooserPanel.getSelectedCup() != null)
				return _cupChooserPanel.getCupsMap().get(_cupChooserPanel.getSelectedCup()).length;
			else
				return 0;
		}
		
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			String v = null;
			switch (columnIndex) {
			case 0:
				 v = "#" + (rowIndex + 1);
				 break;
			case 1:
				 v = _cupChooserPanel.getCupsMap().get(_cupChooserPanel.getSelectedCup())[rowIndex];
				 break;
			default:
				break;
			}
			return v;
		}
	}
	
	public SelectedCupTable(JPanel selectedCupImage) {
		initGUI();
	}
	
	protected void initGUI() {
		tableModel = new MySelectedCupTableModel();
		super.initGUI();
	}
	
	@Override
	public void cupSelected() {
		tableModel.refresh();
		_selectedCupImage.removeAll();
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/images/" + _cupChooserPanel.getSelectedCup() + "trophy" + ".jpg"));
		icon.setImage(icon.getImage().getScaledInstance(390, 230, 1));
		_selectedCupImage.add(new JLabel(icon));
		_selectedCupImage.updateUI();
		_selectedCupPanel.setBorder(BorderFactory.createTitledBorder
				(BorderFactory.createLineBorder(Color.BLACK), "Cup: " + _cupChooserPanel.getSelectedCup()));
	}
}