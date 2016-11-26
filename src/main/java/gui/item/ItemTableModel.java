package gui.item;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Item;
import service.ItemService;

public class ItemTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private List<Item> list;
	private ItemService service;
	private String[] colNames = { "Item", "Category" };

	public ItemTableModel() {
		service = new ItemService();
		list = service.getAll();
	}

	@Override
	public String getColumnName(int col) {
		return colNames[col];
	}

	public int getColumnCount() {
		return 2;
	}

	public int getRowCount() {
		return list.size();
	}

	public Object getValueAt(int row, int col) {

		Item item = list.get(row);

		switch (col) {
		case 0:
			return item.getTitle();
		case 1:
			return item.getCategory().getTitle();
		}
		return null;
	}

}
