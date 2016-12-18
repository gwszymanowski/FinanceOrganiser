package gui.sheet;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import listener.EditNumberListener;
import model.SheetRow;

public class SheetRowTable extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTable table;
	private SheetRowTableModel model;
	private JPopupMenu mainpop, secondpop;
	private int selectedCol;
	private int selectedRowId;

	public SheetRowTable(int monthNum, int yearNum) {
		model = new SheetRowTableModel(monthNum, yearNum);
		table = new JTable(model);
		table.getColumnModel().getColumn(4).setMinWidth(0);
		table.getColumnModel().getColumn(4).setMaxWidth(0);
		initializePopups();

		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {

				int row = table.rowAtPoint(e.getPoint());

				int col = table.columnAtPoint(e.getPoint());
				setSelectedCol(col);

				table.getSelectionModel().setSelectionInterval(row, row);

				int tablevalue = (int) table.getValueAt(row, 4);
				System.out.println("TABLE VALUE " + tablevalue);
				setSelectedRowId(tablevalue);
				System.out.println("RETRIEVED VALUE " + getSelectedRowId());
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

		editMenu.addActionListener(new EditNumberListener(getSelectedRowId(), selectedCol, false));

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

	public void setSelectedCol(int selectedCol) {
		this.selectedCol = selectedCol;
	}

	public int getSelectedRowId() {
		return selectedRowId;
	}

	public void setSelectedRowId(int selectedRowId) {
		this.selectedRowId = selectedRowId;
	}

}
