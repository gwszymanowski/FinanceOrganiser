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

}