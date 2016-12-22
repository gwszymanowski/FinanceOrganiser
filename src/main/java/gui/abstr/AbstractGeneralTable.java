package gui.abstr;

import java.awt.BorderLayout;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public abstract class AbstractGeneralTable extends JPanel {

	private static final long serialVersionUID = 1L;

	protected JTable table;
	protected JPopupMenu popup;
	protected JMenuItem edit, delete;

	public AbstractGeneralTable() {
		this.initializeBody();
		this.initializeLayout();
		this.initializePopup();
		this.initializeListeners();
	}

	private void initializeLayout() {
		this.setLayout(new BorderLayout());
		JScrollPane scroll = new JScrollPane(this.table);
		this.add(scroll, BorderLayout.CENTER);
	}

	private void initializePopup() {
		this.popup = new JPopupMenu();

		this.edit = new JMenuItem("Edit");
		this.popup.add(this.edit);

		this.delete = new JMenuItem("Delete");
		this.popup.add(this.delete);

	}

	protected abstract void initializeBody();

	protected abstract void initializeListeners();
	
	public abstract void refresh();
}
