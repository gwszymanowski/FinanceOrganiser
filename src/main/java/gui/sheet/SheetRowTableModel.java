package gui.sheet;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.SheetRow;
import service.SheetRowService;

public class SheetRowTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private List<SheetRow> list;
	private SheetRowService service;
	
	public SheetRowTableModel() {
		service = new SheetRowService();
		list = service.getAll();
	}
	
	public int getColumnCount() {
		return 4;
	}

	public int getRowCount() {
		return service.getCount();
	}

	public Object getValueAt(int row, int col) {
		
		SheetRow sheetRow = list.get(row);

		switch(col) {
		case 0:
			return sheetRow.getItem().getTitle();
		case 1:
			return sheetRow.getCategory().getTitle();
		case 2:
			return sheetRow.getPrice().getEstimated();
		case 3:
			return sheetRow.getPrice().getActual();
		}
		
		return null;
	}

}
