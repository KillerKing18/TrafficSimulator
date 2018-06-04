package tp.examples.swing.misc;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import tp.examples.spreadsheet.ver1.Controller;
import tp.examples.spreadsheet.ver1.SPCellMul;
import tp.examples.spreadsheet.ver1.SPCellNum;
import tp.examples.spreadsheet.ver1.SPCellObserver;
import tp.examples.spreadsheet.ver1.SPCellSum;
import tp.examples.spreadsheet.ver1.SPImpl;
import tp.examples.spreadsheet.ver1.SpreadSheet;

import java.awt.BorderLayout;

@SuppressWarnings("serial")
public class TableExample_3 extends JFrame {

	SomeTableModel tableModel;
	SpreadSheet sp;

	public TableExample_3(SpreadSheet sp) {
		super("[=] Spread Sheet [=]");
		this.sp = sp;
		initGUI();
	}

	class SomeTableModel extends AbstractTableModel implements SPCellObserver {

		public SomeTableModel() {
			int rows = sp.getHeight();
			int cols = sp.getWidth();
			for (int i = 0; i < rows; i++)
				for (int j = 0; j < cols; j++)
					sp.regCellObserver(i, j, this);
		}

		public String getColumnName(int col) {
			return null;
		}

		public int getRowCount() {
			return sp.getHeight();
		}

		public int getColumnCount() {
			return sp.getWidth();
		}

		public Object getValueAt(int row, int col) {
			return sp.getCell(row, col);
		}

		public boolean isCellEditable(int row, int col) {
			return true;
		}

		// it assumes the input is correct, it might fail if provided with "bad"
		// input
		public void setValueAt(Object value, int row, int col) {
			String v = (String) value;
			v.trim(); // remove trailing and leading whitespaces

			// try to convert it to a number first
			Double d = null;
			try {
				d = new Double(v);
			} catch (NumberFormatException e) {
			}

			// I don't check for errors, might throw exceptions
			if (d != null) {
				sp.setCell(row, col, new SPCellNum(d));
			} else {
				String[] tokens = v.split("\\s+");
				Integer x1 = new Integer(tokens[1]);
				Integer y1 = new Integer(tokens[2]);
				Integer x2 = new Integer(tokens[3]);
				Integer y2 = new Integer(tokens[4]);
				if (tokens[0].equals("sum")) {
					sp.setCell(row, col, new SPCellSum(x1, y1, x2, y2));
				} else if (tokens[0].equals("mul")) {
					sp.setCell(row, col, new SPCellMul(x1, y1, x2, y2));
				}
			}
			fireTableCellUpdated(row, col);
		}

		@Override
		public void valueChanged(int x, int y, double v) {
			fireTableDataChanged();
		}

	}

	private void initGUI() {

		JPanel mainPanel = new JPanel(new BorderLayout(5, 5));

		tableModel = new SomeTableModel();
		JTable table = new JTable(tableModel);
		mainPanel.add(new JScrollPane(table));
		table.setFillsViewportHeight(true);

		mainPanel.setOpaque(true);
		this.setContentPane(mainPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);

	}

	public static void main(String[] args) {
		final SpreadSheet sp = new SPImpl(5, 5);
		Controller ctrl = new Controller(sp);

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new TableExample_3(sp);
			}
		});

		ctrl.start();
	}

}
