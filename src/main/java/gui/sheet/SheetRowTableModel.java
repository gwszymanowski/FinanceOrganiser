package gui.sheet;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.SheetRow;
import service.SheetRowService;

public class SheetRowTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private List<SheetRow> list;
	private SheetRowService service;
	private String[] colNames = { "Item", "Category", "Estimated price", "Actual price" };

	@Override
	public String getColumnName(int col) {
		return colNames[col];
	}

	public SheetRowTableModel(int monthNum, int yearNum) {
		service = new SheetRowService();
		list = getRows(monthNum, yearNum);
	}

	public int getColumnCount() {
		return 4;
	}

	public int getRowCount() {
		return list.size();
	}

	public Object getValueAt(int row, int col) {

		SheetRow sheetRow = list.get(row);

		switch (col) {
		case 0:
			return sheetRow.getTitle();
		case 1:
			return sheetRow.getCategory().getTitle();
		case 2:
			return sheetRow.getPrice().getEstimated();
		case 3:
			return sheetRow.getPrice().getActual();
		}

		return null;
	}

	public List<SheetRow> getRows(int monthNum, int yearNum) {
		List<SheetRow> list = new LinkedList<SheetRow>();
		
		list = service.getByYearMonth(yearNum, monthNum);
		return list;

	}

	public void reloadData(int monthNum, int yearNum) {
		list = getRows(monthNum, yearNum);
		fireTableDataChanged();
	}

	public List<SheetRow> getList() {
		return list;
	}
	
	

}
