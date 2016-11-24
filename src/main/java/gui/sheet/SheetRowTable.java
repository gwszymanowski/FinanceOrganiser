package gui.sheet;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class SheetRowTable extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JTable table;
	private SheetRowTableModel model;
	
	public SheetRowTable(int monthNum, int yearNum) {
		model = new SheetRowTableModel(monthNum, yearNum);
		table = new JTable(model);
		
		setLayout(new BorderLayout());
		
		JScrollPane scroll = new JScrollPane(table);  
		add(scroll, BorderLayout.CENTER);
	
	}
	
	public void refresh(int monthNum, int yearNum) {
		model.reloadData(monthNum, yearNum);
		model.fireTableDataChanged();
	}

}
