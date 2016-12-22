package gui.staticsheet;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class StaticSheetView extends JPanel {

	private static final long serialVersionUID = 1L;

	private StaticSheetListPanel listPanel;

	public StaticSheetView() {
		setLayout(new BorderLayout());
		this.listPanel = new StaticSheetListPanel();
		add(this.listPanel, BorderLayout.CENTER);
	}
	
	public void refresh(int m, int y) {
		this.listPanel.refresh(m, y);
	}
	
	public void refreshToCurrent() {
		this.listPanel.initializeYearMonthValues();
		int month = Integer.valueOf(this.listPanel.getMonth());
		int year = Integer.valueOf(this.listPanel.getYear());
		this.refresh(month, year);
	}

}
