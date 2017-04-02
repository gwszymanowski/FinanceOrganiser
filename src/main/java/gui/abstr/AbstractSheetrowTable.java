package gui.abstr;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import listener.EditNumberListener;
import model.SheetRow;

public abstract class AbstractSheetrowTable extends JPanel {

	private static final long serialVersionUID = 1L;

	protected JTable table;
	protected JMenuItem editMenu;
	protected JPopupMenu mainpop, secondpop;
	protected EditNumberListener editNumberListener;
	protected int monthNum, yearNum, col, tablevalue;

	public AbstractSheetrowTable(int monthNum, int yearNum) {
		this.monthNum = monthNum;
		this.yearNum = yearNum;
		initializeBody();
		initializeLayout();
		initializePopups();
		initializeTableListener();
		initializeEditListener();
	}

	private void initializeLayout() {
		setLayout(new BorderLayout());

		JScrollPane scroll = new JScrollPane(table);
		add(scroll, BorderLayout.CENTER);
	}

	protected int getCol() {
		return col;
	}

	protected void setCol(int col) {
		this.col = col;
	}

	protected int getTablevalue() {
		return tablevalue;
	}

	protected void setTablevalue(int tablevalue) {
		this.tablevalue = tablevalue;
	}

	protected abstract void initializeBody();

	protected abstract void initializePopups();

	protected abstract void initializeTableListener();

	protected abstract void initializeEditListener();

	public abstract void refresh(int monthNum, int yearNum);

	public abstract List<SheetRow> getList();

}
