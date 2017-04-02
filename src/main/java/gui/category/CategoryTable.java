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
		model = new CategoryTableModel();
		table = new JTable(model);
		table.getColumnModel().getColumn(1).setMinWidth(0);
		table.getColumnModel().getColumn(1).setMaxWidth(0);

	}

	@Override
	protected void initializeListeners() {
		table.addMouseListener(new MouseAdapter() {

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

		editTitleListener = new EditTitleableListener(service, new Category());

		CompositeActionListener editListeners = new CompositeActionListener();

		editListeners.addActionListener(editTitleListener, 1);
		editListeners.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				refresh();
			}

		}, 2);

		edit.addActionListener(editListeners);

		deleteTitleableListener = new DeleteTitleableListener(service);

		CompositeActionListener deleteListeners = new CompositeActionListener();

		deleteListeners.addActionListener(deleteTitleableListener, 1);

		deleteListeners.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				refresh();
			}

		}, 2);

		delete.addActionListener(deleteListeners);
	}

	public void refresh() {
		model.reloadData();
		model.fireTableDataChanged();
	}

}
