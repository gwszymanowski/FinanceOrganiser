package gui.staticsheet;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class GeneralSheetView extends JPanel {

	private static final long serialVersionUID = 1L;

	private GeneralSheetListPanel listPanel;

	public GeneralSheetView() {
		setLayout(new BorderLayout());
		this.listPanel = new GeneralSheetListPanel();
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
