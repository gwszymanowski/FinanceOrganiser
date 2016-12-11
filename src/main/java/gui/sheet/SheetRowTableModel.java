package gui.sheet;

import java.util.List;

import model.SheetRow;
import service.SheetRowService;

public class SheetRowTableModel extends SheetRowBridge {

	private static final long serialVersionUID = 1L;

	public SheetRowTableModel(int monthNum, int yearNum) {
		super();
		this.service = new SheetRowService(false);
		reloadData(monthNum, yearNum);
	}

	public List<SheetRow> getRows(int monthNum, int yearNum) {
		return service.getByYearMonth(yearNum, monthNum, false);
	}

}
