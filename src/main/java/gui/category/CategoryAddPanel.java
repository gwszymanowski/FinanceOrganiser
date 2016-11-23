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
	private Category cat;
	
	public CategoryAddPanel() {
		setLayout(new BorderLayout());

		JPanel topPanel = new JPanel(new GridBagLayout());
		topPanel.add(new JLabel("ADD NEW CATEGORY"));

		add(topPanel, BorderLayout.NORTH);

		JPanel body = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.gridy = 0;
		c.gridx = 0;
		body.add(new JLabel("Name: "), c);
		c.gridx++;
		field = new JTextField(10);
		body.add(field, c);
		add(body, BorderLayout.CENTER);

		submit = new JButton("Submit");
		
		cat = new Category();
		
		CompositeActionListener listener = new CompositeActionListener();
		
		listener.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String title = field.getText();
				cat.setTitle(title);
			}
			
		}, 1);
		
		listener.addActionListener(new ConfirmListener(new CategoryService(), cat), 2);

		submit.addActionListener(listener);
		
		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		bottomPanel.add(submit);
		add(bottomPanel, BorderLayout.SOUTH);
	}

}
