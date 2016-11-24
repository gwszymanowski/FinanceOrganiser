package gui.sheet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.swing.table.AbstractTableModel;

import model.SheetMonth;
import model.SheetRow;
import service.SheetMonthService;

public class SheetRowTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private List<SheetRow> list;
	private SheetMonthService service;
	private String[] colNames = { "Item", "Category", "Estimated price", "Actual price" };
	private int monthNum, yearNum;
	private List<SheetMonth> sheets;
	
	@Override
	public String getColumnName(int col) {
		return colNames[col];
	}

	public SheetRowTableModel(int monthNum, int yearNum) {
		this.monthNum = monthNum;
		this.yearNum = yearNum;
		service = new SheetMonthService();
		
		sheets = service.getByYear(yearNum);
		Collections.sort(sheets);
		
		list = new ArrayList<SheetRow>();
		
		
		Optional<SheetMonth> sheetmonths = sheets.stream()
				.filter(x -> x.checkMonths(monthNum) == true).findAny();
		
		//sheetmonths.
		
		//list.addAll()
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

}
