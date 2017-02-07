package gui;

import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import listener.CompositeParserListener;
import listener.FillListener;
import listener.ParserPropertiesListener;

public class PreparedMenu extends JMenuBar {

	private static final long serialVersionUID = 1L;

	private JMenu file, options, advanced, help;
	public JMenuItem categoriesSubmenu, itemSubmenu, general, exceptional, fill;
	public JMenuItem deleteAll, deleteTrues, deleteFalses;
	public JMenuItem toJSON, toXML, fromJSON, fromXML, toProperties;

	public PreparedMenu() {
		this.addFile();
		this.addOptions();
		this.addAdvanced();
		this.addHelp();
	}

	private void addFile() {
		this.file = new JMenu("File");

		JMenu exportMenu = new JMenu("Export");

		this.toProperties = new JMenuItem("Settings");

		this.toProperties.addActionListener(new ParserPropertiesListener());

		exportMenu.add(this.toProperties);

		this.toJSON = new JMenuItem("JSON");
		this.toJSON.addActionListener(new CompositeParserListener(false));
		exportMenu.add(this.toJSON);

		this.toXML = new JMenuItem("XML");
		this.toXML.addActionListener(new CompositeParserListener(true));
		exportMenu.add(this.toXML);

		this.file.add(exportMenu);

		JMenu importMenu = new JMenu("Import");

		this.fromJSON = new JMenuItem("JSON");
		importMenu.add(fromJSON);

		this.fromXML = new JMenuItem("XML");
		importMenu.add(fromXML);

		this.file.add(importMenu);

		add(this.file);
	}

	private void addOptions() {
		this.options = new JMenu("Options");

		JMenu mode = new JMenu("Choose mode");

		this.general = new JMenuItem("General");
		mode.add(this.general);

		this.exceptional = new JMenuItem("Exceptional");
		mode.add(this.exceptional);

		this.options.add(mode);

		this.add(this.options);
	}

	private void addAdvanced() {
		this.advanced = new JMenu("Advanced");

		this.addConfigurations();
		this.addDeletes();
		this.addFill();

		this.add(advanced);
	}

	private void addConfigurations() {
		JMenu addConfigure = new JMenu("Configure");

		this.categoriesSubmenu = new JMenuItem("Categories");
		addConfigure.add(this.categoriesSubmenu);

		this.itemSubmenu = new JMenuItem("Static items");
		addConfigure.add(this.itemSubmenu);

		this.advanced.add(addConfigure);
	}

	private void addDeletes() {
		JMenu deleteConfigure = new JMenu("Delete");

		this.deleteAll = new JMenuItem("all");
		deleteConfigure.add(this.deleteAll);

		this.deleteTrues = new JMenuItem("general sheets");
		deleteConfigure.add(this.deleteTrues);

		this.deleteFalses = new JMenuItem("exceptional sheets");
		deleteConfigure.add(this.deleteFalses);

		this.advanced.add(deleteConfigure);
	}

	private void addFill() {
		this.fill = new JMenuItem("Fill");
		this.fill.addActionListener(new FillListener());
		this.advanced.add(this.fill);
	}

	private void addHelp() {
		this.help = new JMenu("Help");
		this.add(this.help);
	}

	public void addMenuViewListener(ActionListener listener) {
		this.general.addActionListener(listener);
		this.exceptional.addActionListener(listener);
		this.categoriesSubmenu.addActionListener(listener);
		this.itemSubmenu.addActionListener(listener);
	}

	public void addDeleteListener(ActionListener listener) {
		this.deleteAll.addActionListener(listener);
		this.deleteTrues.addActionListener(listener);
		this.deleteFalses.addActionListener(listener);
	}

}
