package gui.abstr;

import java.awt.BorderLayout;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import listener.DeleteTitleableListener;
import listener.EditTitleableListener;

public abstract class AbstractGeneralTable extends JPanel {

	private static final long serialVersionUID = 1L;

	protected JTable table;
	protected JPopupMenu popup;
	protected JMenuItem edit, delete;
	private int col, tablevalue;
	protected EditTitleableListener editTitleListener;
	protected DeleteTitleableListener deleteTitleableListener;

	public AbstractGeneralTable() {
		initializeBody();
		initializeLayout();
		initializePopup();
		initializeListeners();
	}

	private void initializeLayout() {
		setLayout(new BorderLayout());
		JScrollPane scroll = new JScrollPane(table);
		add(scroll, BorderLayout.CENTER);
	}

	private void initializePopup() {
		popup = new JPopupMenu();

		edit = new JMenuItem("Edit");
		popup.add(edit);

		delete = new JMenuItem("Delete");
		popup.add(delete);

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

	protected abstract void initializeListeners();

	public abstract void refresh();

}
