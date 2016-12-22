package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import gui.PreparedMenu;
import service.SheetRowService;

public class DeleteMenuListener implements ActionListener {

	private SheetRowService service;
	private PreparedMenu menu;

	public DeleteMenuListener(PreparedMenu menu) {
		this.menu = menu;
		this.service = new SheetRowService();
	}

	public void actionPerformed(ActionEvent e) {

		int dialogButton = JOptionPane.YES_NO_OPTION;
		int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure? It is irreversable.", "Confirm",
				dialogButton);

		if (dialogResult == JOptionPane.YES_OPTION) {
			if (e.getSource() == this.menu.deleteAll) {
				this.service.deleteAll();
				JOptionPane.showMessageDialog(null, "Action finished");
			} else if (e.getSource() == this.menu.deleteTrues) {
				this.service.delete(true);
				JOptionPane.showMessageDialog(null, "Action finished");
			} else if (e.getSource() == this.menu.deleteFalses) {
				this.service.delete(false);
				JOptionPane.showMessageDialog(null, "Action finished");
			} else
				throw new IllegalAccessError();
		}

	}

}
