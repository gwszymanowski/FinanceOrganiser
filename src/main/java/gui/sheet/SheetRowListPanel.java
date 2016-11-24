package gui.sheet;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import listener.CompositeActionListener;

public class SheetRowListPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private SheetRowTable table;
	JPanel bottomPanel;
	JSlider slider;
	JComboBox<String> yearBox;
	SheetRowAddPanel sheetrowPanel;
	private String month, year, pickDate;

	public SheetRowListPanel(SheetRowAddPanel sheetrowPanel) {

		this.sheetrowPanel = sheetrowPanel;
		setLayout(new BorderLayout());

		JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		topPanel.add(new JLabel("LIST OF EXPENSES:"));
		add(topPanel, BorderLayout.NORTH);

		bottomPanel = new JPanel(new BorderLayout());
		setSlider();
		setYearBox();

		addListeners();
		initializeYearMonthValues();
		sheetrowPanel.setDatePick(pickDate);

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

	private void setYearBox() {
		yearBox = new JComboBox<>();
		yearBox.addItem(new String("2016"));
		yearBox.addItem(new String("2017"));

		bottomPanel.add(yearBox, BorderLayout.CENTER);
	}

	private void initializeYearMonthValues() {
		int sliderVal = slider.getValue();
		setMonth(String.valueOf(sliderVal));
		String boxVal = (String) yearBox.getSelectedItem();
		setYear(boxVal);
		setPickDate();
	}

	private void addListeners() {

	
		slider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				int sliderVal = slider.getValue();
				setMonth(String.valueOf(sliderVal));
				setPickDate();
				sheetrowPanel.setDatePick(pickDate);
				refresh(Integer.parseInt(month), Integer.parseInt(year));
			}

		});

		yearBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String boxVal = (String) yearBox.getSelectedItem();
				setYear(boxVal);
				setPickDate();
				sheetrowPanel.setDatePick(pickDate);
				refresh(Integer.parseInt(month), Integer.parseInt(year));
			}

		});

	}

	public void setMonth(String month) {
		this.month = month;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
	private void refresh(int monthNum, int yearNum) {
		table.refresh(monthNum, yearNum);
	}

	private void setPickDate() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.year + "/");
		sb.append(this.month);
		this.pickDate = sb.toString();
	}

}
