package gui;

import javax.swing.JFrame;

import general.Settings;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	PreparedMenu menu;

	public MainFrame() {
		initComponents();
		initSettings();

	}

	private void initComponents() {
		menu = new PreparedMenu();
		setJMenuBar(menu);
	}

	private void initSettings() {
		setAlwaysOnTop(true);
		setSize(Settings.WIDTH, Settings.HEIGHT);
		setTitle("Personal finances");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation(Settings.X, Settings.Y);
		setVisible(true);
	}

}
