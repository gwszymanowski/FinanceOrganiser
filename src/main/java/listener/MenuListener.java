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

		if (e.getSource() == menu.itemSubmenu)
			cardLayout.show(cards, "item");
		else if (e.getSource() == menu.categoriesSubmenu)
			cardLayout.show(cards, "category");
		else if (e.getSource() == menu.general)
			cardLayout.show(cards, "general");
		else if (e.getSource() == menu.exceptional)
			cardLayout.show(cards, "exceptional");
		else
			throw new IllegalAccessError();

	}

}
