package listener;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import service.CategoryService;
import service.CrudServiceI;
import service.ItemService;
@SuppressWarnings("rawtypes")
public class ParserPropertiesListener implements ActionListener {

	JPanel customPanel;
	JCheckBox categoryCheckBox, itemCheckBox, excSheetrowCheckBox, genSheetrowCheckBox;
	private List<JCheckBox> checkBoxes;
	private List<CrudServiceI> services;

	public ParserPropertiesListener(List<CrudServiceI> services) {	
		this.checkBoxes = new ArrayList<JCheckBox>();
		this.services = services;
		createPanel();
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

	@Override
	public void actionPerformed(ActionEvent e) {
		int input = JOptionPane.showConfirmDialog(null, customPanel, "Which should be serialized:",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

		if (input == JOptionPane.OK_OPTION) {

		
			
			for (int i = 0; i < checkBoxes.size(); i++) {
//				if (checkBoxes.get(i).isSelected())
//					props.set(i, new Boolean(true));

			}
		}
	}

}
