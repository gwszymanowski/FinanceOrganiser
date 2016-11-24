package gui.sheet;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class SheetRowListPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private SheetRowTable table;
	private JPanel bottomPanel;
	private JSlider slider;

	public SheetRowListPanel() {
		setLayout(new BorderLayout());

		JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		topPanel.add(new JLabel("LIST OF EXPENSES:"));
		add(topPanel, BorderLayout.NORTH);

		bottomPanel = new JPanel(new BorderLayout());
		setSlider();

		table = new SheetRowTable(slider.getValue(), 2016);
		add(table, BorderLayout.CENTER);

		add(bottomPanel, BorderLayout.SOUTH);

	}

	private void setSlider() {
		Calendar c = Calendar.getInstance();

		int currentMonth = c.get(Calendar.MONTH) + 1;

		slider = new JSlider(JSlider.HORIZONTAL, 1, 12, 1);

		slider.setMajorTickSpacing(3);
		slider.setMinorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setValue(currentMonth);

		slider.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 5));
		slider.setToolTipText(new String("Choose month"));

		bottomPanel.add(slider, BorderLayout.WEST);
	}

}
