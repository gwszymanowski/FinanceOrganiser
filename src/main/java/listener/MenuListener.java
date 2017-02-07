package listener;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import gui.PreparedMenu;

public class MenuListener implements ActionListener {

	private PreparedMenu menu;
	private JPanel cards;

	public MenuListener(PreparedMenu menu, JPanel cards) {
		this.menu = menu;
		this.cards = cards;
	}

	public void actionPerformed(ActionEvent e) {

		CardLayout cardLayout = (CardLayout) cards.getLayout();

		if (e.getSource() == this.menu.itemSubmenu)
			cardLayout.show(this.cards, "item");
		else if (e.getSource() == this.menu.categoriesSubmenu)
			cardLayout.show(this.cards, "category");
		else if (e.getSource() == this.menu.general)
			cardLayout.show(this.cards, "general");
		else if (e.getSource() == this.menu.exceptional)
			cardLayout.show(this.cards, "exceptional");
		else
			throw new IllegalAccessError();

	}

}
