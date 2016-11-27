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
		setLayout(new BorderLayout());
		options = new BasicOptionsPanel();

		cards = new JPanel(new CardLayout());

		listPanel = new ItemListPanel();
		cards.add(listPanel, "list");

		addPanel = new ItemAddPanel(listPanel.table);
		cards.add(addPanel, "add");

		options.addCardListeners(new BasicOptionsListener(options, cards));

		add(options, BorderLayout.NORTH);

		add(cards, BorderLayout.CENTER);
	}
	
}
