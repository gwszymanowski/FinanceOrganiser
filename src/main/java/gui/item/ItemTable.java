package gui.item;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;

import gui.abstr.AbstractGeneralTable;
import listener.DeleteTitleableListener;
import listener.EditTitleableListener;
import model.Item;
import service.ItemService;

public class ItemTable extends AbstractGeneralTable {

	private static final long serialVersionUID = 1L;

	private ItemTableModel model;

	@Override
	protected void initializeBody() {
		this.model = new ItemTableModel();
		this.table = new JTable(this.model);
		this.table.getColumnModel().getColumn(2).setMinWidth(0);
		this.table.getColumnModel().getColumn(2).setMaxWidth(0);
		this.table.getTableHeader().setReorderingAllowed(false);
	}

	@Override
	protected void initializeListeners() {
		this.table.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {

				int row = table.rowAtPoint(e.getPoint());
				int col = table.columnAtPoint(e.getPoint());
				setCol(col);
				int tablevalue = (int) table.getValueAt(row, 2);
				setTablevalue(tablevalue);
				editTitleListener.setId(tablevalue);
				deleteTitleableListener.setId(tablevalue);
			
				table.getSelectionModel().setSelectionInterval(row, row);

				if (e.getButton() == MouseEvent.BUTTON3 && col == 0)
					popup.show(table, e.getX(), e.getY());

			}
		});

		ItemService service = new ItemService();
		
		this.editTitleListener = new EditTitleableListener(service, new Item());	
		this.edit.addActionListener(this.editTitleListener);
		
		this.deleteTitleableListener = new DeleteTitleableListener(service);
		this.delete.addActionListener(this.deleteTitleableListener);
	}

	@Override
	public void refresh() {
		this.model.reloadData();
		this.model.fireTableDataChanged();
	}

}