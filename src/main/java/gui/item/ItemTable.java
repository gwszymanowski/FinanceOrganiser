package gui.item;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ItemTable extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTable table;
	ItemTableModel model;
	JPopupMenu popup;
	JMenuItem edit, delete;

	public ItemTable() {
		initializeBody();
		initializePopup();
		initializeListeners();
	}

	private void initializeBody() {
		this.model = new ItemTableModel();
		this.table = new JTable(this.model);

		this.setLayout(new BorderLayout());

		JScrollPane scroll = new JScrollPane(this.table);
		this.add(scroll, BorderLayout.CENTER);

	}

	private void initializePopup() {
		this.popup = new JPopupMenu();

		this.edit = new JMenuItem("Edit");
		this.popup.add(edit);

		this.delete = new JMenuItem("Delete");
		this.popup.add(delete);

	}

	private void initializeListeners() {
		this.table.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {

				int row = table.rowAtPoint(e.getPoint());
				int col = table.columnAtPoint(e.getPoint());
				// int tablevalue = (int) table.getValueAt(row, 4);

				table.getSelectionModel().setSelectionInterval(row, row);

				if (e.getButton() == MouseEvent.BUTTON3 && col == 0)
					popup.show(table, e.getX(), e.getY());

			}
		});

		edit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}

		});

		delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}

		});
	}

	public void refresh() {
		this.model.reloadData();
		this.model.fireTableDataChanged();
	}

}