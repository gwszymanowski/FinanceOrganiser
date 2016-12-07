package gui;

import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import listener.CompositeParserListener;
import listener.FillListener;
import listener.ParserListener;
import listener.ParserPropertiesListener;

public class PreparedMenu extends JMenuBar {

	private static final long serialVersionUID = 1L;

	JMenu file, options, advanced, help;
	public JMenuItem categoriesSubmenu, itemSubmenu, general, exceptional, fill;
	public JMenuItem deleteAll, deleteTrues, deleteFalses;
	public JMenuItem toJSON, toXML, fromJSON, fromXML, toProperties;

	public PreparedMenu() {
		addFile();
		addOptions();
		addAdvanced();
		addHelp();
	}

	private void addFile() {
		file = new JMenu("File");

		JMenu exportMenu = new JMenu("Export");

		toProperties = new JMenuItem("Settings");

		toProperties.addActionListener(new ParserPropertiesListener());

		exportMenu.add(toProperties);

		toJSON = new JMenuItem("JSON");

		exportMenu.add(toJSON);

		toXML = new JMenuItem("XML");
		toXML.addActionListener(new CompositeParserListener(true));
		exportMenu.add(toXML);

		file.add(exportMenu);

		JMenu importMenu = new JMenu("Import");

		fromJSON = new JMenuItem("JSON");
		importMenu.add(fromJSON);

		fromXML = new JMenuItem("XML");
		importMenu.add(fromXML);

		file.add(importMenu);

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

		addConfigurations();
		addDeletes();
		addFill();

		add(advanced);
	}

	private void addConfigurations() {
		JMenu addConfigure = new JMenu("Configure");

		categoriesSubmenu = new JMenuItem("Categories");
		addConfigure.add(categoriesSubmenu);

		itemSubmenu = new JMenuItem("Static items");
		addConfigure.add(itemSubmenu);

		advanced.add(addConfigure);
	}

	private void addDeletes() {
		JMenu deleteConfigure = new JMenu("Delete");

		deleteAll = new JMenuItem("all");
		deleteConfigure.add(deleteAll);

		deleteTrues = new JMenuItem("general sheets");
		deleteConfigure.add(deleteTrues);

		deleteFalses = new JMenuItem("exceptional sheets");
		deleteConfigure.add(deleteFalses);

		advanced.add(deleteConfigure);
	}

	private void addFill() {
		fill = new JMenuItem("Fill");
		fill.addActionListener(new FillListener());
		advanced.add(fill);
	}

	private void addHelp() {
		help = new JMenu("Help");
		add(help);
	}

	public void addInsertListener(ActionListener listener) {
		general.addActionListener(listener);
		exceptional.addActionListener(listener);
		categoriesSubmenu.addActionListener(listener);
		itemSubmenu.addActionListener(listener);
	}

	public void addDeleteListener(ActionListener listener) {
		deleteAll.addActionListener(listener);
		deleteTrues.addActionListener(listener);
		deleteFalses.addActionListener(listener);
	}

}
