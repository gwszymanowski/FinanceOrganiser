package gui;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
		
		CompositeActionListener deleteListener = new CompositeActionListener();
		deleteListener.addActionListener(new DeleteListener(menu), 1);
		
		deleteListener.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == menu.deleteAll) {
					sheetRowView.refresh(0,0);		
					staticSheetView.refresh(0, 0);
				}
				else if (e.getSource() == menu.deleteTrues) {
					staticSheetView.refresh(0, 0);
				}				
				else if (e.getSource() == menu.deleteFalses) {
					sheetRowView.refresh(0,0);	
				}	
			}
			
		}, 2);
		
		menu.addDeleteListener(deleteListener);

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
