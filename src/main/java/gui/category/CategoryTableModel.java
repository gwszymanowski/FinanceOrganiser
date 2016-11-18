package gui.category;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Category;
import service.CategoryService;

public class CategoryTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private List<Category> list;
	private CategoryService service;
	
	public CategoryTableModel() {
		service = new CategoryService();
		list = service.getAll();
	}
	
	public int getColumnCount() {
		return 2;
	}

	public int getRowCount() {
		return service.getCount();
	}

	public Object getValueAt(int row, int col) {
		
		Category cat = list.get(row);
		
		switch(col) {
		case 0:
			return cat.getTitle();
		case 1:
			return cat.getOrder();
		}
		return null;
	}

}
