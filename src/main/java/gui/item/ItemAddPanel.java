package gui.item;

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
import model.Item;
import service.CategoryService;
import service.ItemService;

public class ItemAddPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTextField field;
	private JButton submit;
	private JComboBox<Category> categoryBox;
	private ItemTable table;
	private Item item;
	private CategoryService categoryService;

	public ItemAddPanel(ItemTable table) {
		this.table = table;
		this.initializeBody();
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
		this.field = new JTextField(10);
		body.add(this.field, c);
		this.add(body, BorderLayout.CENTER);

		c.gridx = 0;
		c.gridy++;
		body.add(new JLabel("Category: "), c);
		c.gridx++;
		this.categoryBox = getCategoryComboBox();
		body.add(this.categoryBox, c);

		this.submit = new JButton("Submit");

		this.addListeners();

		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		bottomPanel.add(this.submit);
		this.add(bottomPanel, BorderLayout.SOUTH);
	}

	private void addListeners() {
		this.item = new Item();

		CompositeActionListener listener = new CompositeActionListener();
		listener.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String title = field.getText();
				item.setTitle(title);
				Category t = (Category) categoryBox.getSelectedItem();
				item.setCategory(t);

			}

		}, 1);

		listener.addActionListener(new ConfirmListener(new ItemService(), item), 2);

		listener.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				table.refresh();
			}

		}, 3);

		this.submit.addActionListener(listener);
	}

	private JComboBox<Category> getCategoryComboBox() {
		JComboBox<Category> box = new JComboBox<Category>();

		List<Category> categories = this.categoryService.getAll();

		for (Category cat : categories)
			box.addItem(cat);

		return box;
	}

}
