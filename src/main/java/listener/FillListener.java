package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import service.SheetRowService;

public class FillListener implements ActionListener {

	private JPanel customPanel;
	private JComboBox<Integer> yearBox;
	private SheetRowService service;

	public FillListener() {
		this.service = new SheetRowService();
		this.createPanel();
	}

	private void createPanel() {
		this.customPanel = new JPanel();

		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);

		this.yearBox = new JComboBox<Integer>();

		for (int i = 0; i <= 5; i++) {
			this.yearBox.addItem(new Integer(year));
			year++;
		}

		this.customPanel.add(this.yearBox);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		int input = JOptionPane.showConfirmDialog(null, this.customPanel, "Generate sheets till year:",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

		if (input == JOptionPane.OK_OPTION) {
			Integer lastYear = (Integer) yearBox.getSelectedItem();
			this.service.fill(lastYear);
		}

	}

}
