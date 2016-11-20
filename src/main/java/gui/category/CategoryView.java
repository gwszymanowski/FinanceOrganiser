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
		setLayout(new BorderLayout());
		options = new BasicOptionsPanel();

		cards = new JPanel(new CardLayout());

			listPanel = new CategoryListPanel();
			cards.add(listPanel, "list");
	
			addPanel = new CategoryAddPanel();
			cards.add(addPanel, "add");

		options.addCardListeners(new BasicOptionsListener(options, cards));

		add(options, BorderLayout.NORTH);
		

		add(cards, BorderLayout.CENTER);

	}

}
