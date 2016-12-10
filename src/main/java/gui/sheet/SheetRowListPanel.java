package gui.sheet;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import service.SheetRowService;

public class SheetRowListPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	JPanel bottomPanel;
	JSlider slider;
	JComboBox<String> yearBox;
	SheetRowAddPanel sheetrowPanel;
	JLabel actualPriceLabel;
	private SheetRowTable table;
	private String month, year, pickDate;

	public SheetRowListPanel(SheetRowAddPanel sheetrowPanel) {

		this.sheetrowPanel = sheetrowPanel;
		setLayout(new BorderLayout());

		JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		topPanel.add(new JLabel("LIST OF EXPENSES:"));
		add(topPanel, BorderLayout.NORTH);

		bottomPanel = new JPanel(new BorderLayout());
		setSlider();

		table = new SheetRowTable(slider.getValue(), 2016);
		add(table, BorderLayout.CENTER);

		setYearBoxPanel();
		setSumLabel();

		addListeners();
		initializeYearMonthValues();
		sheetrowPanel.setDatePick(pickDate);

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

	private void setYearBoxPanel() {

		JPanel mainPanel = new JPanel(new BorderLayout());

		JPanel yearBoxPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		yearBox = new JComboBox<>();

		for (String k : getYears())
			yearBox.addItem(k);

		yearBox.setToolTipText(new String("Choose year"));
		yearBox.setBorder(BorderFactory.createEmptyBorder(10, 30, 0, 0));

		yearBoxPanel.add(yearBox);

		mainPanel.add(yearBoxPanel, BorderLayout.WEST);

		JPanel pricePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		actualPriceLabel = new JLabel("");
		actualPriceLabel.setBorder(BorderFactory.createEmptyBorder(15, 0, 10, 40));

		pricePanel.add(actualPriceLabel);

		mainPanel.add(pricePanel, BorderLayout.EAST);

		bottomPanel.add(mainPanel, BorderLayout.CENTER);
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
				setGeneralValues();

				refresh(Integer.parseInt(getMonth()), Integer.parseInt(getYear()));
			}

		});

		yearBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String boxVal = (String) yearBox.getSelectedItem();
				setYear(boxVal);
				setGeneralValues();

				refresh(Integer.parseInt(getMonth()), Integer.parseInt(getYear()));
			}

		});

	}

	private void setGeneralValues() {
		setPickDate();
		sheetrowPanel.setDatePick(pickDate);
		refresh(Integer.parseInt(month), Integer.parseInt(year));
		setSumLabel();
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getMonth() {
		return month;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getYear() {
		return year;
	}

	public void refresh(int monthNum, int yearNum) {
		table.refresh(monthNum, yearNum);
	}

	private void setSumLabel() {
		double sum = table.getList().stream().mapToDouble(x -> x.getPrice().getActual()).sum();
		actualPriceLabel.setText(String.valueOf(sum));
	}

	private void setPickDate() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.year + "/");
		sb.append(this.month);
		this.pickDate = sb.toString();
	}

	private List<String> getYears() {
		List<String> years = new LinkedList<>();

		SheetRowService service = new SheetRowService();
		Instant inst = service.getEarliestDate();
		ZonedDateTime zdt = ZonedDateTime.ofInstant(inst, ZoneId.systemDefault());

		int year = zdt.getYear();

		for (int i = 0; i < 10; i++) {
			years.add(String.valueOf(year));
			year++;
		}

		return years;
	}

}
