package gui.item;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;

import gui.abstr.AbstractGeneralTable;

public class ItemTable extends AbstractGeneralTable {

	private static final long serialVersionUID = 1L;

	private ItemTableModel model;

	@Override
	protected void initializeBody() {
		this.model = new ItemTableModel();
		this.table = new JTable(this.model);
		this.table.getColumnModel().getColumn(2).setMinWidth(0);
		this.table.getColumnModel().getColumn(2).setMaxWidth(0);

	}

	@Override
	protected void initializeListeners() {
		this.table.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {

				int row = table.rowAtPoint(e.getPoint());
				int col = table.columnAtPoint(e.getPoint());
				// int tablevalue = (int) table.getValueAt(row, 4);

				table.getSelectionModel().setSelectionInterval(row, row);

				if (e.getButton() == MouseEvent.BUTTON3 && col == 0)
					popup.show(table, e.getX(), e.getY());

			}
		});

		this.edit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}

		});

		this.delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}

		});
	}

	@Override
	public void refresh() {
		this.model.reloadData();
		this.model.fireTableDataChanged();
	}

}