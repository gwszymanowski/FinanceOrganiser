package gui.item;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ItemTable extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTable table;
	private ItemTableModel model;
	
	public ItemTable() {
		model = new ItemTableModel();
		table = new JTable(model);
		
		setLayout(new BorderLayout());
		
		JScrollPane scroll = new JScrollPane(table);  
		add(scroll, BorderLayout.CENTER);
	
	}

}