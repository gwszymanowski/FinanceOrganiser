package gui;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import general.Settings;
import gui.category.CategoryView;
import gui.item.ItemView;
import listener.MenuListener;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	PreparedMenu menu;
	CategoryView categoryView;
	ItemView branchView;
	EntryPanel entryPanel;
	JPanel cards;

	public MainFrame() {
		initPanels();
		initCards();
		initMenu();
		initSettings();
	}

	private void initCards() {

		cards = new JPanel(new CardLayout());
		cards.add(entryPanel, "entry");
		cards.add(branchView, "branch");
		cards.add(categoryView, "category");
		add(cards);

	}

	private void initPanels() {
		categoryView = new CategoryView();
		branchView = new ItemView();
		entryPanel = new EntryPanel();
	}

	private void initMenu() {

		menu = new PreparedMenu();
		menu.addActionListener(new MenuListener(menu, cards));

		setJMenuBar(menu);
	}

	private void initSettings() {
	
		setSize(Settings.WIDTH, Settings.HEIGHT);
		setTitle("Personal finances");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation(Settings.X, Settings.Y);
		setVisible(true);
	}

}
