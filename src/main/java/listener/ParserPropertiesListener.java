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
		this.checkBoxes = new ArrayList<JCheckBox>();
		this.createPanel();
		this.tickBoxes();
	}

	private void createPanel() {
		this.customPanel = new JPanel(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 0;
		c.gridy = 0;

		this.customPanel.add(new JLabel("Categories"), c);

		c.gridx++;

		this.categoryCheckBox = new JCheckBox();
		this.checkBoxes.add(this.categoryCheckBox);
		this.customPanel.add(this.categoryCheckBox, c);

		c.gridy++;
		c.gridx = 0;

		this.customPanel.add(new JLabel("Items"), c);
		c.gridx++;

		this.itemCheckBox = new JCheckBox();
		this.checkBoxes.add(itemCheckBox);
		this.customPanel.add(itemCheckBox, c);
		c.gridy++;
		c.gridx = 0;

		this.customPanel.add(new JLabel("Exceptional sheets"), c);
		c.gridx++;

		this.excSheetrowCheckBox = new JCheckBox();
		this.checkBoxes.add(this.excSheetrowCheckBox);
		this.customPanel.add(this.excSheetrowCheckBox, c);

		c.gridy++;
		c.gridx = 0;

		this.customPanel.add(new JLabel("General sheets"), c);

		c.gridx++;

		this.genSheetrowCheckBox = new JCheckBox();
		this.checkBoxes.add(this.genSheetrowCheckBox);
		this.customPanel.add(this.genSheetrowCheckBox, c);

	}

	private void tickBoxes() {

		PropertiesManager manager = new PropertiesManager();
		Properties p = manager.readExportProperties();

		if ("true".equals(p.getProperty("category")))
			this.categoryCheckBox.setSelected(true);
		else
			this.categoryCheckBox.setSelected(false);
		
		if ("true".equals(p.getProperty("item")))
			this.itemCheckBox.setSelected(true);
		else
			this.itemCheckBox.setSelected(false);	
		
		if ("true".equals(p.getProperty("exceptional")))
			this.excSheetrowCheckBox.setSelected(true);
		else
			this.excSheetrowCheckBox.setSelected(false);
		
		if ("true".equals(p.getProperty("general")))
			this.genSheetrowCheckBox.setSelected(true);
		else
			this.genSheetrowCheckBox.setSelected(false);

	}

	private void saveSettings() {
		PropertiesManager manager = new PropertiesManager();
		manager.writeExportProperties(getContent());

	}

	private String getContent() {
		StringBuilder sb = new StringBuilder();

		if (this.categoryCheckBox.isSelected())
			sb.append("category=true\n");
		else
			sb.append("category=false\n");

		if (this.itemCheckBox.isSelected())
			sb.append("item=true\n");
		else
			sb.append("item=false\n");

		if (this.excSheetrowCheckBox.isSelected())
			sb.append("exceptional=true\n");
		else
			sb.append("exceptional=false\n");

		if (this.genSheetrowCheckBox.isSelected())
			sb.append("general=true\n");
		else
			sb.append("general=false\n");

		return sb.toString();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int input = JOptionPane.showConfirmDialog(null, this.customPanel, "Which should be serialized:",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

		if (input == JOptionPane.OK_OPTION)
			this.saveSettings();

	}

}
