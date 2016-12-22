package listener;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import gui.BasicOptionsPanel;

public class BasicOptionsListener implements ActionListener {

	BasicOptionsPanel options;
	JPanel cards;

	public BasicOptionsListener(BasicOptionsPanel options, JPanel cards) {
		this.options = options;
		this.cards = cards;
	}

	public void actionPerformed(ActionEvent e) {

		CardLayout cardLayout = (CardLayout) cards.getLayout();

		if (e.getSource() == this.options.persistView) {
			cardLayout.show(this.cards, "add");
		} else if (e.getSource() == this.options.listView) {
			cardLayout.show(this.cards, "list");
		} else {
			throw new IllegalAccessError();
		}
	}

}
