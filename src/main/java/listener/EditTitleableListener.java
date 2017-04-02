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

	private JPanel customPanel;
	private JTextField field;
	private CrudServiceI service;
	private int id;
	private Titleable titleable;

	public EditTitleableListener(CrudServiceI service, Titleable titleable) {
		id = 0;
		this.service = service;
		this.titleable = titleable;
		createPanel();
	}

	private void createPanel() {
		customPanel = new JPanel();
		field = new JTextField(10);
		customPanel.add(field);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		int input = JOptionPane.showConfirmDialog(null, customPanel, "Choose new title",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

		if (input == JOptionPane.OK_OPTION) {

			String val = field.getText();

			titleable.setId(id);
			titleable.setTitle(val);

			service.update(titleable);

		}

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
