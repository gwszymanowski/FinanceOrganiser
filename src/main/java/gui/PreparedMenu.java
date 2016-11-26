package gui;

import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class PreparedMenu extends JMenuBar {

	private static final long serialVersionUID = 1L;

	JMenu file, options, advanced, help;
	public JMenuItem categoriesSubmenu, itemSubmenu, general, exceptional;

	public PreparedMenu() {

		addFile();
		addOptions();
		addAdvanced();
		addHelp();

	}

	private void addFile() {
		file = new JMenu("File");
		add(file);
	}

	private void addOptions() {
		options = new JMenu("Options");

		JMenu mode = new JMenu("Choose mode");
		
			general = new JMenuItem("General");
			mode.add(general);
			
			exceptional = new JMenuItem("Exceptional");
			mode.add(exceptional);
		
		options.add(mode);

		add(options);
	}

	private void addAdvanced() {
		advanced = new JMenu("Advanced");

		JMenu configure = new JMenu("Configure");

			categoriesSubmenu = new JMenuItem("Categories");
			configure.add(categoriesSubmenu);
	
			itemSubmenu = new JMenuItem("Static items");
			configure.add(itemSubmenu);

		advanced.add(configure);

		add(advanced);
	}

	private void addHelp() {
		help = new JMenu("Help");
		add(help);
	}

	public void addActionListener(ActionListener listener) {
		general.addActionListener(listener);
		exceptional.addActionListener(listener);
		categoriesSubmenu.addActionListener(listener);
		itemSubmenu.addActionListener(listener);
	}

}
