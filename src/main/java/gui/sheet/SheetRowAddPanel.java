package gui.sheet;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import listener.CompositeActionListener;
import listener.ConfirmListener;
import model.Category;
import model.SheetRow;
import service.CategoryService;
import service.SheetRowService;

public class SheetRowAddPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JButton submit;
	private JTextField nameField;
	private JComboBox<Category> categoryBox;
	private SheetRow sheetrow;
	private CategoryService categoryService;

	public SheetRowAddPanel() {
		categoryService = new CategoryService();
		setLayout(new BorderLayout());

		JPanel topPanel = new JPanel(new GridBagLayout());
		topPanel.add(new JLabel("ADD NEW ITEM"));

		add(topPanel, BorderLayout.NORTH);

		JPanel body = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.gridy = 0;
		c.gridx = 0;
		body.add(new JLabel("Name: "), c);
		c.gridx++;
		nameField = new JTextField(10);
		body.add(nameField, c);
		add(body, BorderLayout.CENTER);

		c.gridx = 0;
		c.gridy++;

		body.add(new JLabel("Category: "), c);

		categoryBox = new JComboBox<Category>();

		List<Category> categories = categoryService.getAll();

		for (Category cat : categories)
			categoryBox.addItem(cat);
		
		c.gridx++;
		body.add(categoryBox, c);

		submit = new JButton("Submit");

		sheetrow = new SheetRow();
		
		CompositeActionListener listener = new CompositeActionListener();

		listener.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String title = nameField.getText();
				Category t = (Category)categoryBox.getSelectedItem();
				sheetrow.setCategory(t);
				sheetrow.setItem(title);
				
			}

		}, 1);

		listener.addActionListener(new ConfirmListener(new SheetRowService(), sheetrow), 2);

		submit.addActionListener(listener);

		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		bottomPanel.add(submit);
		add(bottomPanel, BorderLayout.SOUTH);
	}

}
