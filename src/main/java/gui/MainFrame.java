package gui;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import general.Settings;
import gui.category.CategoryView;
import listener.MenuListener;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	PreparedMenu menu;
	CategoryView categoryView;
	JPanel cards;

	public MainFrame() {
		initCards();
		initComponents();
		initSettings();
	}

	private void initCards() {

		categoryView = new CategoryView();
		cards = new JPanel(new CardLayout());

		cards.add(categoryView, "category");
		add(cards);

	}

	private void initComponents() {
		
		menu = new PreparedMenu();
		menu.addActionListener(new MenuListener(menu, cards));

		setJMenuBar(menu);
	}

	private void initSettings() {
		setAlwaysOnTop(true);
		setSize(Settings.WIDTH, Settings.HEIGHT);
		setTitle("Personal finances");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation(Settings.X, Settings.Y);
		setVisible(true);
	}

}
