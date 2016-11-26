package gui.staticsheet;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.SheetRow;

public class StaticSheetTable extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTable table;
	private StaticSheetTableModel model;

	public StaticSheetTable(int monthNum, int yearNum) {
		model = new StaticSheetTableModel(monthNum, yearNum);
		table = new JTable(model);

		setLayout(new BorderLayout());

		JScrollPane scroll = new JScrollPane(table);
		add(scroll, BorderLayout.CENTER);
	}

	public void refresh(int monthNum, int yearNum) {
		model.reloadData(monthNum, yearNum);
		model.fireTableDataChanged();
	}

	public List<SheetRow> getList() {
		return model.getList();
	}

}