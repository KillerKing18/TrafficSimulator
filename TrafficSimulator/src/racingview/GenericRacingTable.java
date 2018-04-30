package racingview;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import model.RacingSimulatorObserver;

public abstract class GenericRacingTable extends JPanel implements RacingSimulatorObserver {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	protected MyGenericRacingTableModel tableModel;
	protected CupChooserPanel _cupChooserPanel;
	protected CharacterChooserPanel _characterChooserPanel;
	protected ImagesPanel _imagesPanel;
	protected JPanel _selectedCupImage;
	protected JPanel _selectedCupPanel;
	
	abstract class MyGenericRacingTableModel extends AbstractTableModel {
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
	
	public GenericRacingTable() {
		initGUI();
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
	public void registered(CharacterChooserPanel characterChooserPanel, ImagesPanel imagesPanel, CupChooserPanel cupChooserPanel, JPanel selectedCupImage, JPanel selectedCupPanel) {
		_selectedCupPanel = selectedCupPanel;
		_selectedCupImage = selectedCupImage;
		_characterChooserPanel = characterChooserPanel;
		_imagesPanel = imagesPanel;
		_cupChooserPanel = cupChooserPanel;
		tableModel.refresh();
	}

	@Override
	public void cupSelected() {
		tableModel.refresh();
	}

	@Override
	public void characterAdded() {
		tableModel.refresh();
	}

	@Override
	public void reset() {
		tableModel.refresh();
	}
}