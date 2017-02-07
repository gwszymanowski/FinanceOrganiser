package gui.category;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import listener.CompositeActionListener;
import listener.ConfirmListener;
import model.Category;
import service.CategoryService;

public class CategoryAddPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JButton submit;
	private JTextField field;
	private CategoryTable table;
	private Category cat;
	
	public CategoryAddPanel(CategoryTable table) {
		this.table = table;
		this.initializeBody();
	}
	
	private void initializeBody() {
		
		this.setLayout(new BorderLayout());

		JPanel topPanel = new JPanel(new GridBagLayout());
		topPanel.add(new JLabel("ADD NEW CATEGORY"));

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

		this.submit = new JButton("Submit");
		
		this.addListeners();

		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		bottomPanel.add(this.submit);
		this.add(bottomPanel, BorderLayout.SOUTH);
	}

	private void addListeners() {
		this.cat = new Category();

		CompositeActionListener listener = new CompositeActionListener();

		listener.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String title = field.getText();
				cat.setTitle(title);
			}

		}, 1);

		listener.addActionListener(new ConfirmListener(new CategoryService(), this.cat), 2);
		
		listener.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				table.refresh();			
			}
			
		}, 3);

		this.submit.addActionListener(listener);
	}

}
