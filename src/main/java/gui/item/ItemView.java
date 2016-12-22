package gui.item;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JPanel;

import gui.BasicOptionsPanel;
import listener.BasicOptionsListener;

public class ItemView extends JPanel {

	private static final long serialVersionUID = 1L;

	BasicOptionsPanel options;
	ItemListPanel listPanel;
	ItemAddPanel addPanel;
	JPanel cards;
	
	public ItemView() {
		this.setLayout(new BorderLayout());
		this.options = new BasicOptionsPanel();

		this.cards = new JPanel(new CardLayout());

		this.listPanel = new ItemListPanel();
		this.cards.add(this.listPanel, "list");

		this.addPanel = new ItemAddPanel(this.listPanel.table);
		this.cards.add(this.addPanel, "add");

		this.options.addCardListeners(new BasicOptionsListener(this.options, this.cards));

		this.add(this.options, BorderLayout.NORTH);

		this.add(this.cards, BorderLayout.CENTER);
	}
	
}
