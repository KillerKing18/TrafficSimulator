package view;

import java.awt.Color;

import javax.swing.BorderFactory;

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
			header = new String[]{ "Name", "Previous", "Next" };
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
				 v = _cupChooserPanel.getCupsMap().get(_cupChooserPanel.getSelectedCup())[rowIndex];
				 break;
			case 1:
				 if(rowIndex != 0)
					v = _cupChooserPanel.getCupsMap().get(_cupChooserPanel.getSelectedCup())[rowIndex - 1];
				 else
					v = _cupChooserPanel.getCupsMap().get(_cupChooserPanel.getSelectedCup())[_cupChooserPanel.getCupsMap().get(_cupChooserPanel.getSelectedCup()).length - 1];
				 break;
			case 2:
				 if(rowIndex != _cupChooserPanel.getCupsMap().get(_cupChooserPanel.getSelectedCup()).length - 1)
					v = _cupChooserPanel.getCupsMap().get(_cupChooserPanel.getSelectedCup())[rowIndex + 1];
				 else
					v = _cupChooserPanel.getCupsMap().get(_cupChooserPanel.getSelectedCup())[0];
				 break;
			default:
				break;
			}
			return v;
		}
	}
	
	public SelectedCupTable() {
		initGUI();
	}
	
	protected void initGUI() {
		tableModel = new MySelectedCupTableModel();
		super.initGUI();
	}
	
	@Override
	public void cupSelected() {
		setBorder(BorderFactory.createTitledBorder
				(BorderFactory.createLineBorder(Color.BLACK), "Cup: " + _cupChooserPanel.getSelectedCup()));
		tableModel.refresh();
	}
}