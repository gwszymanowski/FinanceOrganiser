package gui.category;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class CategoryTable extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTable table;
	private CategoryTableModel model;
	
	public CategoryTable() {
		model = new CategoryTableModel();
		table = new JTable(model);
		
		setLayout(new BorderLayout());
		
		JScrollPane scroll = new JScrollPane(table);  
		add(scroll, BorderLayout.CENTER);
	
	}
	
	public void refresh() {
		model.reloadData();
		model.fireTableDataChanged();
	}

}
