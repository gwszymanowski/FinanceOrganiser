package gui.sheet;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.SheetRow;

public class SheetRowTable extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTable table;
	private SheetRowTableModel model;
	private JPopupMenu mainpop, secondpop;
	private int selectedRow, selectedCol;

	public SheetRowTable(int monthNum, int yearNum) {
		model = new SheetRowTableModel(monthNum, yearNum);
		table = new JTable(model);

		initializePopups();

		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {

				int row = table.rowAtPoint(e.getPoint());
				setSelectedRow(row);
				int col = table.columnAtPoint(e.getPoint());
				setSelectedCol(col);
				
				for (int i = 0; i < table.getColumnCount(); i++) {
					System.out.println(table.getValueAt(row, i));
				}
				
				
				if (e.getButton() == MouseEvent.BUTTON3) {

					if (col > 1)
						secondpop.show(table, e.getX(), e.getY());
					else
						mainpop.show(table, e.getX(), e.getY());
				}
			}

		});

		setLayout(new BorderLayout());

		JScrollPane scroll = new JScrollPane(table);
		add(scroll, BorderLayout.CENTER);

	}

	private void initializePopups() {
		mainpop = new JPopupMenu();

		JMenuItem deleteMenu = new JMenuItem("Delete");
		mainpop.add(deleteMenu);

		secondpop = new JPopupMenu();

		JMenuItem editMenu = new JMenuItem("Edit");

		editMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}

		});

		secondpop.add(editMenu);

		JMenuItem deleteTwoMenu = new JMenuItem("Delete");
		secondpop.add(deleteTwoMenu);
	}

	public void refresh(int monthNum, int yearNum) {
		model.reloadData(monthNum, yearNum);
		model.fireTableDataChanged();
	}

	public List<SheetRow> getList() {
		return model.getList();
	}

	public void setSelectedRow(int selectedRow) {
		this.selectedRow = selectedRow;
	}

	public void setSelectedCol(int selectedCol) {
		this.selectedCol = selectedCol;
	}

}
