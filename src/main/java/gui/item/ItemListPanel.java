package gui.item;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ItemListPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	ItemTable table;

	public ItemListPanel() {
		this.initializeBody();
	}

	private void initializeBody() {
		this.setLayout(new BorderLayout());

		JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		topPanel.add(new JLabel("LIST OF STATIC ITEMS:"));
		this.add(topPanel, BorderLayout.NORTH);

		this.table = new ItemTable();
		this.add(this.table, BorderLayout.CENTER);

	}

}
