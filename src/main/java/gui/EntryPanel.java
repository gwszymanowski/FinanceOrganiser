package gui;

import java.awt.GridBagLayout;
import java.awt.Label;

import javax.swing.JPanel;

public class EntryPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public EntryPanel() {
		setLayout(new GridBagLayout());
		add(new Label("Choose mode"));
	}

}
