package gui.staticsheet;

import java.util.List;

import gui.sheet.SheetRowBridge;
import model.SheetRow;
import service.SheetRowService;

public class StaticSheetTableModel extends SheetRowBridge {

	private static final long serialVersionUID = 1L;

	public StaticSheetTableModel(int monthNum, int yearNum) {
		super();
		this.service = new SheetRowService(false);
		reloadData(monthNum, yearNum);
	}

	@Override
	public List<SheetRow> getRows(int monthNum, int yearNum) {
		return service.getByYearMonth(yearNum, monthNum, true);
	}

}
