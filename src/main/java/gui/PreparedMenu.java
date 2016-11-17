package gui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class PreparedMenu extends JMenuBar {

	private static final long serialVersionUID = 1L;

	JMenu file, options, advanced, help;
	JMenuItem categoriesSubmenu, branchesSubmenu, general, exceptional;
	
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

		JMenu i1 = new JMenu("Choose mode");
		general = new JMenuItem("General");
		i1.add(general);
		exceptional = new JMenuItem("Exceptional");
		i1.add(exceptional);
		options.add(i1);

		add(options);
	}

	private void addAdvanced() {
		advanced = new JMenu("Advanced");

		JMenu configure = new JMenu("Configure");
		
		categoriesSubmenu = new JMenuItem("categories");
		configure.add(categoriesSubmenu);
		
		branchesSubmenu = new JMenuItem("branches");
		configure.add(branchesSubmenu);
		
		advanced.add(configure);

		add(advanced);
	}

	private void addHelp() {
		help = new JMenu("Help");
		add(help);
	}

}
