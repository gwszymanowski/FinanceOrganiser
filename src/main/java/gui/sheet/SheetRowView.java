package gui.sheet;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JPanel;

import gui.BasicOptionsPanel;
import listener.BasicOptionsListener;

public class SheetRowView extends JPanel {

	private static final long serialVersionUID = 1L;

	private BasicOptionsPanel options;
	private SheetRowListPanel listPanel;
	private SheetRowAddPanel addPanel;
	private JPanel cards;
	
	public SheetRowView() {
		setLayout(new BorderLayout());
		options = new BasicOptionsPanel();

		cards = new JPanel(new CardLayout());

			addPanel = new SheetRowAddPanel();
			listPanel = new SheetRowListPanel(addPanel);
			cards.add(listPanel, "list");	
			cards.add(addPanel, "add");

		options.addCardListeners(new BasicOptionsListener(options, cards));

		add(options, BorderLayout.NORTH);
		add(cards, BorderLayout.CENTER);
	}
}
