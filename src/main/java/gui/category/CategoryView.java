package gui.category;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JPanel;

import gui.BasicOptionsPanel;
import listener.BasicOptionsListener;

public class CategoryView extends JPanel {

	private static final long serialVersionUID = 1L;

	BasicOptionsPanel options;
	CategoryListPanel listPanel;
	CategoryAddPanel addPanel;
	JPanel cards;

	public CategoryView() {
		this.setLayout(new BorderLayout());
		this.options = new BasicOptionsPanel();

		this.cards = new JPanel(new CardLayout());

		this.listPanel = new CategoryListPanel();
		this.cards.add(this.listPanel, "list");

		this.addPanel = new CategoryAddPanel(this.listPanel.table);
		this.cards.add(this.addPanel, "add");

		this.options.addCardListeners(new BasicOptionsListener(this.options, this.cards));

		this.add(this.options, BorderLayout.NORTH);
		this.add(this.cards, BorderLayout.CENTER);

	}

}
