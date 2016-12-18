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

import listener.CompositeActionListener;
import listener.EditNumberListener;
import model.SheetRow;

public class SheetRowTable extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTable table;
	private SheetRowTableModel model;
	private JPopupMenu mainpop, secondpop;
	private EditNumberListener editNumberListener;
	private JMenuItem editMenu;
	private int monthNum, yearNum;

	public SheetRowTable(int monthNum, int yearNum) {
		this.monthNum = monthNum;
		this.yearNum = yearNum;
		initializeTable();
		initializePopups();
		initializeTableListener();
	}

	private void initializeTable() {
		model = new SheetRowTableModel(monthNum, yearNum);
		table = new JTable(model);
		table.getColumnModel().getColumn(4).setMinWidth(0);
		table.getColumnModel().getColumn(4).setMaxWidth(0);

		setLayout(new BorderLayout());

		JScrollPane scroll = new JScrollPane(table);
		add(scroll, BorderLayout.CENTER);
	}

	private void initializeTableListener() {
		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {

				int row = table.rowAtPoint(e.getPoint());
				int col = table.columnAtPoint(e.getPoint());
				int tablevalue = (int) table.getValueAt(row, 4);

				table.getSelectionModel().setSelectionInterval(row, row);

				CompositeActionListener composite = new CompositeActionListener();

				editNumberListener = new EditNumberListener(tablevalue, col, false);

				composite.addActionListener(editNumberListener, 1);
				composite.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						refresh(monthNum, yearNum);
					}

				}, 2);

				editMenu.addActionListener(composite);

				if (e.getButton() == MouseEvent.BUTTON3) {
					if (col > 1)
						secondpop.show(table, e.getX(), e.getY());
					else
						mainpop.show(table, e.getX(), e.getY());
				}
			}

		});
	}

	private void initializePopups() {
		mainpop = new JPopupMenu();
		secondpop = new JPopupMenu();

		JMenuItem deleteMenu = new JMenuItem("Delete");
		mainpop.add(deleteMenu);

		editMenu = new JMenuItem("Edit");
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

}
