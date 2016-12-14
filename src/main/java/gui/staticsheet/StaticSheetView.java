package gui.staticsheet;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class StaticSheetView extends JPanel {

	private static final long serialVersionUID = 1L;

	private StaticSheetListPanel listPanel;

	public StaticSheetView() {
		setLayout(new BorderLayout());

		listPanel = new StaticSheetListPanel();

		add(listPanel, BorderLayout.CENTER);
	}
	
	public void refresh(int m, int y) {
		listPanel.refresh(m, y);
	}
	
	public void refreshToCurrent() {
		System.out.println("static refresh");
		listPanel.initializeYearMonthValues();
		int month = Integer.valueOf(listPanel.getMonth());
		int year = Integer.valueOf(listPanel.getYear());
		refresh(month, year);
	}

}
