package gui.staticsheet;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class GeneralSheetView extends JPanel {

	private static final long serialVersionUID = 1L;

	private GeneralSheetListPanel listPanel;

	public GeneralSheetView() {
		setLayout(new BorderLayout());
		listPanel = new GeneralSheetListPanel();
		add(listPanel, BorderLayout.CENTER);
	}
	
	public void refresh(int m, int y) {
		listPanel.refresh(m, y);
	}
	
	public void refreshToCurrent() {
		listPanel.initializeYearMonthValues();
		int month = Integer.valueOf(listPanel.getMonth());
		int year = Integer.valueOf(listPanel.getYear());
		refresh(month, year);
	}

}
