package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import service.CrudServiceI;

@SuppressWarnings("rawtypes")
public class DeleteTitleableListener implements ActionListener {

	private CrudServiceI service;
	private int id;

	public DeleteTitleableListener(CrudServiceI service) {
		this.id = 0;
		this.service = service;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure? It is irreversable.", "Confirm",
				JOptionPane.YES_NO_OPTION);

		if (dialogResult == JOptionPane.OK_OPTION)
			this.service.delete(this.id);

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
