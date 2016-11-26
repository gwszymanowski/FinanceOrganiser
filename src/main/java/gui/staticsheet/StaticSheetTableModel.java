package gui.staticsheet;

import java.util.LinkedList;
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
		List<SheetRow> list = new LinkedList<SheetRow>();

		list = service.getByYearMonth(yearNum, monthNum, true);
		return list;
	}

}
