package gui.category;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Category;
import service.CategoryService;

public class CategoryTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private List<Category> list;
	private CategoryService service;
	private String[] colNames = { "Title" };

	public CategoryTableModel() {
		this.service = new CategoryService();
		this.list = this.service.getAll();
	}

	@Override
	public String getColumnName(int col) {
		return this.colNames[col];
	}

	public int getColumnCount() {
		return 1;
	}

	public int getRowCount() {
		return this.list.size();
	}

	public Object getValueAt(int row, int col) {

		Category cat = this.list.get(row);

		switch (col) {
		case 0:
			return cat.getTitle();
		case 1:
			return cat.getId();
		}
		return null;
	}
	
	public void reloadData() {
		this.list = this.service.getAll();
	}

}
