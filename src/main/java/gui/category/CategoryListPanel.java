package gui.category;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class CategoryListPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public CategoryListPanel() {
		setLayout(new BorderLayout());
		
		JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		topPanel.add(new JLabel("LIST OF CATEGORIES:"));
		add(topPanel, BorderLayout.NORTH);
		
		CategoryTable table = new CategoryTable();
		add(table, BorderLayout.CENTER);

	}

}
