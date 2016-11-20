package gui.sheet;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTable;

public class SheetRowTable extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JTable table;
	private SheetRowTableModel model;
	
	public SheetRowTable() {
		model = new SheetRowTableModel();
		table = new JTable(model);
		
		setLayout(new BorderLayout());
		add(table, BorderLayout.CENTER);
	
	}

}
