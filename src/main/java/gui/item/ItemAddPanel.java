package gui.item;

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
import model.Item;
import service.ItemService;

public class ItemAddPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private Item item;
	private JTextField field;

	public ItemAddPanel() {
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
		field = new JTextField(10);
		body.add(field, c);
		add(body, BorderLayout.CENTER);

		JButton submit = new JButton("Submit");

		item = new Item();

		
		CompositeActionListener listener = new CompositeActionListener();
		listener.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String title = field.getText();
				item.setTitle(title);
			}

		}, 1);
		
		listener.addActionListener(new ConfirmListener(new ItemService(), item), 2);
	
		submit.addActionListener(listener);
	
		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		bottomPanel.add(submit);
		add(bottomPanel, BorderLayout.SOUTH);

	}

}
