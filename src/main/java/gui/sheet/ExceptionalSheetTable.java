package gui.sheet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;

import gui.abstr.AbstractSheetrowTable;
import listener.CompositeActionListener;
import listener.EditNumberListener;
import model.SheetRow;

public class ExceptionalSheetTable extends AbstractSheetrowTable {

	private static final long serialVersionUID = 1L;

	private ExceptionalSheetTableModel model;

	public ExceptionalSheetTable(int monthNum, int yearNum) {
		super(monthNum, yearNum);
	}

	@Override
	protected void initializeBody() {
		this.model = new ExceptionalSheetTableModel(monthNum, yearNum);
		this.table = new JTable(model);
		this.table.getColumnModel().getColumn(4).setMinWidth(0);
		this.table.getColumnModel().getColumn(4).setMaxWidth(0);

	}

	@Override
	protected void initializePopups() {
		this.mainpop = new JPopupMenu();
		this.secondpop = new JPopupMenu();

		JMenuItem deleteMenu = new JMenuItem("Delete");
		this.mainpop.add(deleteMenu);

		this.editMenu = new JMenuItem("Edit");
		this.secondpop.add(this.editMenu);

		JMenuItem deleteTwoMenu = new JMenuItem("Delete");
		this.secondpop.add(deleteTwoMenu);
	}

	@Override
	protected void initializeTableListener() {
		this.table.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {

				int row = table.rowAtPoint(e.getPoint());
				int col = table.columnAtPoint(e.getPoint());
				int tablevalue = (int) table.getValueAt(row, 4);

				table.getSelectionModel().setSelectionInterval(row, row);

				CompositeActionListener composite = new CompositeActionListener();

				editNumberListener = new EditNumberListener(tablevalue, col, false);

				composite.addActionListener(editNumberListener, 1);
				composite.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						refresh(monthNum, yearNum);
					}

				}, 2);

				editMenu.addActionListener(composite);

				if (e.getButton() == MouseEvent.BUTTON3) {
					if (col > 1)
						secondpop.show(table, e.getX(), e.getY());
					else
						mainpop.show(table, e.getX(), e.getY());
				}
			}

		});
	}

	@Override
	public void refresh(int monthNum, int yearNum) {
		this.model.reloadData(monthNum, yearNum);
		this.model.fireTableDataChanged();
	}

	@Override
	public List<SheetRow> getList() {
		return this.model.getList();
	}

}
