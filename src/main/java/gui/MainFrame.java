package gui;

import javax.swing.JFrame;

import general.Settings;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	public MainFrame() {

		initComponents();

	}

	private void initComponents() {
		setAlwaysOnTop(true);
		setSize(Settings.WIDTH, Settings.HEIGHT);
		setTitle("Personal finances");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation(Settings.X,Settings.Y);
		setVisible(true);
	}

}
