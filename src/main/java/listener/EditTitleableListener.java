package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Titleable;
import service.CrudServiceI;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class EditTitleableListener implements ActionListener {

	JPanel customPanel;
	JTextField field;
	private CrudServiceI service;
	private int id;
	private Titleable titleable;

	public EditTitleableListener(CrudServiceI service, Titleable titleable) {
		this.id = 0;
		this.service = service;
		this.titleable = titleable;
		this.createPanel();
	}

	private void createPanel() {
		this.customPanel = new JPanel();
		this.field = new JTextField(10);
		this.customPanel.add(this.field);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		int input = JOptionPane.showConfirmDialog(null, this.customPanel, "Choose new title",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

		if (input == JOptionPane.OK_OPTION) {

			String val = this.field.getText();

			this.titleable.setId(id);
			this.titleable.setTitle(val);

			this.service.update(this.titleable);

		}

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
