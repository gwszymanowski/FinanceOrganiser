package gui.sheet;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;
import java.util.Calendar;
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

public class ExceptionalSheetAddPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	JButton submit;
	JTextField nameField;
	JComboBox<Category> categoryBox;
	JLabel datePickLabel;
	private SheetRow sheetrow;
	private CategoryService categoryService;

	public ExceptionalSheetAddPanel() {
		initializeBody();
	}

	private void initializeBody() {
		this.categoryService = new CategoryService();
		this.setLayout(new BorderLayout());

		JPanel topPanel = new JPanel(new GridBagLayout());
		topPanel.add(new JLabel("ADD NEW ITEM"));

		this.add(topPanel, BorderLayout.NORTH);

		JPanel body = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.gridy = 0;
		c.gridx = 0;
		body.add(new JLabel("Name: "), c);
		c.gridx++;
		this.nameField = new JTextField(10);
		body.add(this.nameField, c);

		c.gridx = 0;
		c.gridy++;
		body.add(new JLabel("Category: "), c);

		this.categoryBox = getCategoryComboBox();
		c.gridx++;
		body.add(this.categoryBox, c);

		c.gridx = 0;
		c.gridy++;
		body.add(new JLabel("Date:"), c);

		c.gridx++;
		this.datePickLabel = new JLabel("");
		body.add(this.datePickLabel, c);
		this.add(body, BorderLayout.CENTER);

		this.submit = new JButton("Submit");

		this.addListeners();

		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		bottomPanel.add(this.submit);
		this.add(bottomPanel, BorderLayout.SOUTH);
	}

	private void addListeners() {

		this.sheetrow = new SheetRow();

		CompositeActionListener listener = new CompositeActionListener();

		listener.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String title = nameField.getText();
				Category t = (Category) categoryBox.getSelectedItem();
				sheetrow.setCategory(t);
				sheetrow.setTitle(title);
				sheetrow.setStatic(false);

				String chosenDate = datePickLabel.getText();
				String[] yearmonth = chosenDate.split("/");

				Calendar c = Calendar.getInstance();

				c.set(Calendar.YEAR, Integer.parseInt(yearmonth[0]));
				c.set(Calendar.MONTH, (Integer.parseInt(yearmonth[1]) - 1));

				Instant inst = c.toInstant();

				sheetrow.setCurrent(inst);

			}
		}, 1);

		listener.addActionListener(new ConfirmListener(new SheetRowService(), sheetrow), 2);

		submit.addActionListener(listener);
	}

	private JComboBox<Category> getCategoryComboBox() {
		JComboBox<Category> box = new JComboBox<Category>();

		List<Category> categories = this.categoryService.getAll();

		for (Category cat : categories)
			box.addItem(cat);

		return box;
	}

	public void setDatePick(String datePick) {
		this.datePickLabel.setText(datePick);
	}

}
