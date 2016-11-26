package gui;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import general.Settings;
import gui.category.CategoryView;
import gui.item.ItemView;
import gui.sheet.SheetRowView;
import gui.staticsheet.StaticSheetView;
import listener.DeleteListener;
import listener.MenuListener;


public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	PreparedMenu menu;
	CategoryView categoryView;
	ItemView itemView;
	EntryPanel entryPanel;
	SheetRowView sheetRowView;
	StaticSheetView staticSheetView;
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
		cards.add(itemView, "item");
		cards.add(categoryView, "category");
		cards.add(sheetRowView, "exceptional");
		cards.add(staticSheetView, "general");
		add(cards);

	}

	private void initPanels() {
		categoryView = new CategoryView();
		itemView = new ItemView();
		entryPanel = new EntryPanel();
		sheetRowView = new SheetRowView();
		staticSheetView = new StaticSheetView();
	}

	private void initMenu() {

		menu = new PreparedMenu();
		menu.addInsertListener(new MenuListener(menu, cards));
		menu.addDeleteListener(new DeleteListener(menu));

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
