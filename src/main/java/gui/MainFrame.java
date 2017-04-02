package gui;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import general.Settings;
import gui.category.CategoryView;
import gui.item.ItemView;
import gui.sheet.ExceptionalSheetView;
import gui.staticsheet.GeneralSheetView;
import listener.CompositeActionListener;
import listener.DeleteMenuListener;
import listener.MenuListener;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private PreparedMenu menu;
	private CategoryView categoryView;
	private ItemView itemView;
	private EntryPanel entryPanel;
	private ExceptionalSheetView sheetRowView;
	private GeneralSheetView staticSheetView;
	private JPanel cards;

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
		sheetRowView = new ExceptionalSheetView();
		staticSheetView = new GeneralSheetView();
	}

	private void initMenu() {
		menu = new PreparedMenu();

		CompositeActionListener compositeChange = new CompositeActionListener();
		compositeChange.addActionListener(new MenuListener(menu, cards), 1);
		compositeChange.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (e.getSource() == menu.general)
					staticSheetView.refreshToCurrent();
				else if (e.getSource() == menu.exceptional)
					sheetRowView.refreshToCurrent();

			}

		}, 2);

		menu.addMenuViewListener(compositeChange);

		CompositeActionListener deleteComposite = new CompositeActionListener();
		deleteComposite.addActionListener(new DeleteMenuListener(menu), 1);
		deleteComposite.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sheetRowView.refreshToCurrent();
				staticSheetView.refreshToCurrent();
			}

		}, 2);

		menu.addDeleteListener(deleteComposite);

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
