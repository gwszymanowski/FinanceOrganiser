package gui.sheet;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class SheetRowListPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private SheetRowTable table;
	
	public SheetRowListPanel() {
		setLayout(new BorderLayout());
		
		JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		topPanel.add(new JLabel("LIST OF EXPENSES:"));
		add(topPanel, BorderLayout.NORTH);
		
		table = new SheetRowTable();
		add(table, BorderLayout.CENTER);
	}

}
