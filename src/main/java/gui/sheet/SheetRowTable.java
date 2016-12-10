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

import model.SheetRow;

public class SheetRowTable extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTable table;
	private SheetRowTableModel model;
	private JPopupMenu popmenu;

	public SheetRowTable(int monthNum, int yearNum) {
		model = new SheetRowTableModel(monthNum, yearNum);
		table = new JTable(model);

		popmenu = new JPopupMenu();
		
		JMenuItem kek = new JMenuItem("KEK");
		popmenu.add(kek);
		
		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON3) {
					popmenu.show(table, e.getX(), e.getY());
				}
			}

		});

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
