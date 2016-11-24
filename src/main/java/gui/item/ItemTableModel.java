package gui.item;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Item;
import service.ItemService;

public class ItemTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private List<Item> list;
	@SuppressWarnings("unused")
	private ItemService service;
	private String[] colNames = { "Title", "Order number" };

	public ItemTableModel() {
		service = new ItemService();
		list = new ArrayList<Item>();
		//list = service.getAll();
	}

	@Override
	public String getColumnName(int col) {
		return colNames[col];
	}

	public int getColumnCount() {
		return 2;
	}

	public int getRowCount() {
		return 0;
	}

	public Object getValueAt(int row, int col) {

		Item item = list.get(row);

		switch (col) {
		case 0:
			return item.getTitle();
		case 1:
			return item.getOrder();
		}
		return null;
	}

}
