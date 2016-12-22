package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class BasicOptionsPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public JButton persistView, listView;

	public BasicOptionsPanel() {
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.initComponents();
	}

	private void initComponents() {
		this.persistView = new JButton("Add new");
		this.add(persistView);
		this.listView = new JButton("View list");
		this.add(listView);

	}

	public void addCardListeners(ActionListener listener) {
		this.persistView.addActionListener(listener);
		this.listView.addActionListener(listener);
	}

}
