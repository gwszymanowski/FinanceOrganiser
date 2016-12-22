package gui;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import general.Settings;
import gui.category.CategoryView;
import gui.item.ItemView;
import gui.sheet.SheetRowView;
import gui.staticsheet.StaticSheetView;
import listener.CompositeActionListener;
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
		this.initPanels();
		this.initCards();
		this.initMenu();
		this.initSettings();
	}

	private void initCards() {
		this.cards = new JPanel(new CardLayout());
		this.cards.add(entryPanel, "entry");
		this.cards.add(itemView, "item");
		this.cards.add(categoryView, "category");
		this.cards.add(sheetRowView, "exceptional");
		this.cards.add(staticSheetView, "general");
		this.add(cards);
	}

	private void initPanels() {
		this.categoryView = new CategoryView();
		this.itemView = new ItemView();
		this.entryPanel = new EntryPanel();
		this.sheetRowView = new SheetRowView();
		this.staticSheetView = new StaticSheetView();
	}

	private void initMenu() {
		this.menu = new PreparedMenu();

		CompositeActionListener compositeChange = new CompositeActionListener();
		compositeChange.addActionListener(new MenuListener(this.menu, this.cards), 1);
		compositeChange.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource() == menu.general)		
					staticSheetView.refreshToCurrent();
				else if(e.getSource() == menu.exceptional)
					sheetRowView.refreshToCurrent();
				
			}

		}, 2);

		this.menu.addMenuViewListener(compositeChange);

		CompositeActionListener deleteComposite = new CompositeActionListener();
		deleteComposite.addActionListener(new DeleteListener(menu), 1);
		deleteComposite.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sheetRowView.refreshToCurrent();
				staticSheetView.refreshToCurrent();
			}

		}, 2);

		this.menu.addDeleteListener(deleteComposite);

		this.setJMenuBar(menu);
	}

	private void initSettings() {
		this.setSize(Settings.WIDTH, Settings.HEIGHT);
		this.setTitle("Personal finances");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocation(Settings.X, Settings.Y);
		this.setVisible(true);
	}

}
