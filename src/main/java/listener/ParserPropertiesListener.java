package listener;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import config.PropertiesManager;

public class ParserPropertiesListener implements ActionListener {

	private JPanel customPanel;
	private JCheckBox categoryCheckBox, itemCheckBox, excSheetrowCheckBox, genSheetrowCheckBox;
	private List<JCheckBox> checkBoxes;

	public ParserPropertiesListener() {
		checkBoxes = new ArrayList<JCheckBox>();
		createPanel();
		tickBoxes();
	}

	private void createPanel() {
		customPanel = new JPanel(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 0;
		c.gridy = 0;

		customPanel.add(new JLabel("Categories"), c);

		c.gridx++;

		categoryCheckBox = new JCheckBox();
		checkBoxes.add(categoryCheckBox);
		customPanel.add(categoryCheckBox, c);

		c.gridy++;
		c.gridx = 0;

		customPanel.add(new JLabel("Items"), c);
		c.gridx++;

		itemCheckBox = new JCheckBox();
		checkBoxes.add(itemCheckBox);
		customPanel.add(itemCheckBox, c);
		c.gridy++;
		c.gridx = 0;

		customPanel.add(new JLabel("Exceptional sheets"), c);
		c.gridx++;

		excSheetrowCheckBox = new JCheckBox();
		checkBoxes.add(excSheetrowCheckBox);
		customPanel.add(excSheetrowCheckBox, c);

		c.gridy++;
		c.gridx = 0;

		customPanel.add(new JLabel("General sheets"), c);

		c.gridx++;

		genSheetrowCheckBox = new JCheckBox();
		checkBoxes.add(genSheetrowCheckBox);
		customPanel.add(genSheetrowCheckBox, c);

	}

	private void tickBoxes() {

		PropertiesManager manager = new PropertiesManager();
		Properties p = manager.readExportProperties();

		if ("true".equals(p.getProperty("category")))
			categoryCheckBox.setSelected(true);
		else
			categoryCheckBox.setSelected(false);
		
		if ("true".equals(p.getProperty("item")))
			itemCheckBox.setSelected(true);
		else
			itemCheckBox.setSelected(false);	
		
		if ("true".equals(p.getProperty("exceptional")))
			excSheetrowCheckBox.setSelected(true);
		else
			excSheetrowCheckBox.setSelected(false);
		
		if ("true".equals(p.getProperty("general")))
			genSheetrowCheckBox.setSelected(true);
		else
			genSheetrowCheckBox.setSelected(false);

	}

	private void saveSettings() {
		PropertiesManager manager = new PropertiesManager();
		manager.writeExportProperties(getContent());

	}

	private String getContent() {
		StringBuilder sb = new StringBuilder();

		if (categoryCheckBox.isSelected())
			sb.append("category=true\n");
		else
			sb.append("category=false\n");

		if (itemCheckBox.isSelected())
			sb.append("item=true\n");
		else
			sb.append("item=false\n");

		if (excSheetrowCheckBox.isSelected())
			sb.append("exceptional=true\n");
		else
			sb.append("exceptional=false\n");

		if (genSheetrowCheckBox.isSelected())
			sb.append("general=true\n");
		else
			sb.append("general=false\n");

		return sb.toString();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int input = JOptionPane.showConfirmDialog(null, customPanel, "Which should be serialized:",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

		if (input == JOptionPane.OK_OPTION)
			saveSettings();

	}

}
