package gui.staticsheet;

import java.util.List;

import gui.sheet.SheetRowTableModel;
import model.SheetRow;

public class StaticSheetTableModel extends SheetRowTableModel {

	private static final long serialVersionUID = 1L;

	public StaticSheetTableModel(int monthNum, int yearNum) {
		super(monthNum, yearNum);

	}

	@Override
	public List<SheetRow> getRows(int monthNum, int yearNum) {
		return service.getByYearMonth(yearNum, monthNum, true);
	}

}
