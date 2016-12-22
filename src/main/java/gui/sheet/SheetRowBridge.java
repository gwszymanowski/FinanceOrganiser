package gui.sheet;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.SheetRow;
import service.SheetRowService;

public abstract class SheetRowBridge extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private List<SheetRow> list;
	private String[] colNames = { "Item", "Category", "Estimated price", "Actual price", "id" };
	protected SheetRowService service;

	public SheetRowBridge() {
		this.list = new LinkedList<SheetRow>();
	}

	@Override
	public String getColumnName(int col) {
		return this.colNames[col];
	}

	public int getColumnCount() {
		return 5;
	}

	public int getRowCount() {
		return this.list.size();
	}

	public Object getValueAt(int row, int col) {

		SheetRow sheetRow = this.list.get(row);

		switch (col) {
		case 0:
			return sheetRow.getTitle();
		case 1:
			return sheetRow.getCategory().getTitle();
		case 2:
			return sheetRow.getPrice().getEstimated();
		case 3:
			return sheetRow.getPrice().getActual();
		case 4:
			return sheetRow.getId();
		}

		return null;
	}

	public void reloadData(int monthNum, int yearNum) {
		this.list = getRows(monthNum, yearNum);
		fireTableDataChanged();
	}

	public List<SheetRow> getList() {
		return this.list;
	}

	public abstract List<SheetRow> getRows(int monthNum, int yearNum);

}
