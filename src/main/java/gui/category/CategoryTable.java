package gui.category;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;

import gui.abstr.AbstractGeneralTable;
import listener.CompositeActionListener;
import listener.DeleteTitleableListener;
import listener.EditTitleableListener;
import model.Category;
import service.CategoryService;

public class CategoryTable extends AbstractGeneralTable {

	private static final long serialVersionUID = 1L;

	private CategoryTableModel model;

	@Override
	protected void initializeBody() {
		this.model = new CategoryTableModel();
		this.table = new JTable(this.model);
		this.table.getColumnModel().getColumn(1).setMinWidth(0);
		this.table.getColumnModel().getColumn(1).setMaxWidth(0);

	}

	@Override
	protected void initializeListeners() {
		this.table.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {

				int row = table.rowAtPoint(e.getPoint());
				int col = table.columnAtPoint(e.getPoint());
				setCol(col);
				int tablevalue = (int) table.getValueAt(row, 1);
				setTablevalue(tablevalue);
				editTitleListener.setId(tablevalue);
				deleteTitleableListener.setId(tablevalue);

				table.getSelectionModel().setSelectionInterval(row, row);

				if (e.getButton() == MouseEvent.BUTTON3)
					popup.show(table, e.getX(), e.getY());

			}
		});

		CategoryService service = new CategoryService();

		this.editTitleListener = new EditTitleableListener(service, new Category());

		CompositeActionListener editListeners = new CompositeActionListener();

		editListeners.addActionListener(this.editTitleListener, 1);
		editListeners.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				refresh();
			}

		}, 2);

		this.edit.addActionListener(editListeners);

		this.deleteTitleableListener = new DeleteTitleableListener(service);

		CompositeActionListener deleteListeners = new CompositeActionListener();

		deleteListeners.addActionListener(this.deleteTitleableListener, 1);

		deleteListeners.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				refresh();
			}

		}, 2);

		this.delete.addActionListener(deleteListeners);
	}

	public void refresh() {
		this.model.reloadData();
		this.model.fireTableDataChanged();
	}

}
