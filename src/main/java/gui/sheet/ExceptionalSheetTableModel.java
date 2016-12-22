package gui.sheet;

import java.util.List;

import gui.abstr.AbstractSheetrowTableModel;
import model.SheetRow;
import service.SheetRowService;

public class ExceptionalSheetTableModel extends AbstractSheetrowTableModel {

	private static final long serialVersionUID = 1L;

	public ExceptionalSheetTableModel(int monthNum, int yearNum) {
		super();
		this.service = new SheetRowService(true);
		this.reloadData(monthNum, yearNum);
	}

	public List<SheetRow> getRows(int monthNum, int yearNum) {
		return this.service.getByYearMonth(yearNum, monthNum, false);
	}

}
