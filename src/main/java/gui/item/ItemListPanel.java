package gui.item;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ItemListPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	ItemTable table;
	
	public ItemListPanel() {
		setLayout(new BorderLayout());
		
		JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		topPanel.add(new JLabel("LIST OF STATIC ITEMS:"));
		add(topPanel, BorderLayout.NORTH);
		
		table = new ItemTable();
		add(table, BorderLayout.CENTER);

	}

}
