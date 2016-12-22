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
	protected int monthNum, yearNum;

	public AbstractSheetrowTable(int monthNum, int yearNum) {
		this.monthNum = monthNum;
		this.yearNum = yearNum;
		this.initializeBody();
		this.initializeLayout();
		this.initializePopups();
		this.initializeTableListener();
	}

	private void initializeLayout() {
		this.setLayout(new BorderLayout());

		JScrollPane scroll = new JScrollPane(table);
		this.add(scroll, BorderLayout.CENTER);
	}

	protected abstract void initializeBody();

	protected abstract void initializePopups();

	protected abstract void initializeTableListener();

	public abstract void refresh(int monthNum, int yearNum);

	public abstract List<SheetRow> getList();

}
