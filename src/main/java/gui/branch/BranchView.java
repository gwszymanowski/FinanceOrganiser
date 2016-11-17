package gui.branch;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JPanel;

import gui.BasicOptionsPanel;
import listener.BasicOptionsListener;

public class BranchView extends JPanel {

	private static final long serialVersionUID = 1L;

	BasicOptionsPanel options;
	BranchListPanel listPanel;
	BranchAddPanel addPanel;
	JPanel cards;
	
	public BranchView() {
		setLayout(new BorderLayout());
		options = new BasicOptionsPanel();

		cards = new JPanel(new CardLayout());

		listPanel = new BranchListPanel();
		cards.add(listPanel, "list");

		addPanel = new BranchAddPanel();
		cards.add(addPanel, "add");

		options.addCardListeners(new BasicOptionsListener(options, cards));

		add(options, BorderLayout.NORTH);

		add(cards, BorderLayout.CENTER);
	}
	
}
