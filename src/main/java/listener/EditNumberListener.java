package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.PlainDocument;

import filter.OnlyNumberFilter;
import service.SheetRowService;

public class EditNumberListener implements ActionListener {

	JPanel customPanel;
	JTextField field;
	private int id;
	private int col;
	private SheetRowService service;
	private String title;

	public EditNumberListener(int id, int col, boolean isStatic) {
		this.id = id;
		this.col = col;
		this.service = new SheetRowService(isStatic);

		if (col == 2)
			this.title = "Select new estimated price:";
		else
			this.title = "Select new actual price:";

		createPanel();
	}

	private void createPanel() {
		customPanel = new JPanel();

		field = new JTextField(10);

		PlainDocument doc = (PlainDocument) field.getDocument();
		doc.setDocumentFilter(new OnlyNumberFilter());

		customPanel.add(field);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		int input = JOptionPane.showConfirmDialog(null, customPanel, title, JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);

		if (input == JOptionPane.OK_OPTION) {

			Double val = Double.parseDouble(field.getText());

			if (col == 2)
				service.editPrice(getId(), val, false);
			else
				service.editPrice(getId(), val, true);

		}

	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

}
