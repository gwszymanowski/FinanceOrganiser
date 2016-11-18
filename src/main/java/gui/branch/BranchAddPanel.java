package gui.branch;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BranchAddPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public BranchAddPanel() {
		setLayout(new BorderLayout());
		
		JPanel topPanel = new JPanel(new GridBagLayout());
		topPanel.add(new JLabel("ADD NEW BRANCH"));
		
		add(topPanel, BorderLayout.NORTH);
		
		JPanel body = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridy = 0;
		c.gridx = 0;
		body.add(new JLabel("Name: "), c);
		c.gridx++;
		JTextField field = new JTextField(10);
		body.add(field, c);
		add(body, BorderLayout.CENTER);
		
		JButton submit = new JButton("Submit");
		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		bottomPanel.add(submit);
		add(bottomPanel, BorderLayout.SOUTH);
		
		
	}
	
}
