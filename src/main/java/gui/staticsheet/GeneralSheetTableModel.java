package gui.staticsheet;

import java.util.List;

import gui.abstr.AbstractSheetrowTableModel;
import model.SheetRow;
import service.SheetRowService;

public class GeneralSheetTableModel extends AbstractSheetrowTableModel {

	private static final long serialVersionUID = 1L;

	public GeneralSheetTableModel(int monthNum, int yearNum) {
		super();
		service = new SheetRowService(false);
		reloadData(monthNum, yearNum);
	}

	@Override
	public List<SheetRow> getRows(int monthNum, int yearNum) {
		return service.getByYearMonth(yearNum, monthNum, true);
	}

}
