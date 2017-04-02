package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import service.CrudServiceI;


public class DeleteTitleableListener implements ActionListener {

	@SuppressWarnings("rawtypes")
	private CrudServiceI service;
	private int id;

	@SuppressWarnings("rawtypes")
	public DeleteTitleableListener(CrudServiceI service) {
		id = 0;
		this.service = service;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure? It is irreversable.", "Confirm",
				JOptionPane.YES_NO_OPTION);

		if (dialogResult == JOptionPane.OK_OPTION)
			service.delete(id);

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
