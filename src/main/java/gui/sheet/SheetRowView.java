package gui.sheet;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JPanel;

import gui.BasicOptionsPanel;
import listener.BasicOptionsListener;

public class SheetRowView extends JPanel {

	private static final long serialVersionUID = 1L;

	BasicOptionsPanel options;
	SheetRowListPanel listPanel;
	SheetRowAddPanel addPanel;
	JPanel cards;

	public SheetRowView() {
		this.setLayout(new BorderLayout());
		this.options = new BasicOptionsPanel();

		this.cards = new JPanel(new CardLayout());

		this.addPanel = new SheetRowAddPanel();
		this.listPanel = new SheetRowListPanel(this.addPanel);
		this.cards.add(this.listPanel, "list");
		this.cards.add(this.addPanel, "add");

		this.options.addCardListeners(new BasicOptionsListener(this.options, this.cards));

		this.add(options, BorderLayout.NORTH);
		this.add(cards, BorderLayout.CENTER);
	}

	public void refresh(int m, int y) {
		this.listPanel.refresh(m, y);
	}

	public void refreshToCurrent() {
		this.listPanel.initializeYearMonthValues();
		int month = Integer.valueOf(this.listPanel.getMonth());
		int year = Integer.valueOf(this.listPanel.getYear());
		this.refresh(month, year);
	}
}
