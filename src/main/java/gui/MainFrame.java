package gui;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import general.Settings;
import gui.category.CategoryView;
import gui.item.ItemView;
import gui.sheet.SheetRowView;
import listener.MenuListener;
import service.SheetMonthService;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	PreparedMenu menu;
	CategoryView categoryView;
	ItemView branchView;
	EntryPanel entryPanel;
	SheetRowView sheetRowView;
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
		cards.add(sheetRowView, "exceptional");
		add(cards);

	}

	private void initPanels() {
		categoryView = new CategoryView();
		branchView = new ItemView();
		entryPanel = new EntryPanel();
		sheetRowView = new SheetRowView();
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
	
	@SuppressWarnings("unused")
	private void fill() {
		SheetMonthService s = new SheetMonthService();
		s.fill(2016);
	}

}
