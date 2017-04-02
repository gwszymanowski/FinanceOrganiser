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
		service = new SheetRowService();
		createPanel();
	}

	private void createPanel() {
		customPanel = new JPanel();

		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);

		yearBox = new JComboBox<Integer>();

		for (int i = 0; i <= 5; i++) {
			yearBox.addItem(new Integer(year));
			year++;
		}

		customPanel.add(yearBox);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		int input = JOptionPane.showConfirmDialog(null, customPanel, "Generate sheets till year:",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

		if (input == JOptionPane.OK_OPTION) {
			Integer lastYear = (Integer) yearBox.getSelectedItem();
			service.fill(lastYear);
		}

	}

}
