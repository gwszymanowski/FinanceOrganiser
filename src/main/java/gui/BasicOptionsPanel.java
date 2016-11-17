package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class BasicOptionsPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public JButton persistView, listView;

	public BasicOptionsPanel() {

		setLayout(new FlowLayout(FlowLayout.LEFT));
		initComponents();
	}

	private void initComponents() {
		persistView = new JButton("Add new");
		add(persistView);
		listView = new JButton("View list");
		add(listView);

	}

	public void addCardListeners(ActionListener listener) {
		persistView.addActionListener(listener);
		listView.addActionListener(listener);
	}

}
