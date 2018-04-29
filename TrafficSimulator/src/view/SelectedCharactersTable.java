package view;

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
			header = new String[]{ "Name", "Speed", "Luck" };
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
			default:
				break;
			}
			return v;
		}
	}
	
	public SelectedCharactersTable() {
		initGUI();
	}
	
	protected void initGUI() {
		tableModel = new MySelectedCharactersTableModel();
		super.initGUI();
	}
}