package gui.staticsheet;

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

public class StaticSheetListPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	JPanel bottomPanel;
	JSlider slider;
	JLabel actualPriceLabel;
	JComboBox<String> yearBox;
	private StaticSheetTable table;
	private String month, year;

	public StaticSheetListPanel() {
		setLayout(new BorderLayout());

		JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		topPanel.add(new JLabel("LIST OF EXPENSES:"));
		add(topPanel, BorderLayout.NORTH);

		bottomPanel = new JPanel(new BorderLayout());
		setSlider();

		table = new StaticSheetTable(slider.getValue(), 2016);
		add(table, BorderLayout.CENTER);

		setYearBoxPanel();
		setSumLabel();
		addListeners();
		initializeYearMonthValues();

		add(bottomPanel, BorderLayout.SOUTH);

	}

	private void initializeYearMonthValues() {
		int sliderVal = slider.getValue();
		setMonth(String.valueOf(sliderVal));
		String boxVal = (String) yearBox.getSelectedItem();
		setYear(boxVal);
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

	private void addListeners() {

		slider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				int sliderVal = slider.getValue();
				setMonth(String.valueOf(sliderVal));
				setGeneralValues();
			}

		});

		yearBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String boxVal = (String) yearBox.getSelectedItem();
				setYear(boxVal);
				setGeneralValues();
			}

		});

	}

	private void setGeneralValues() {
		refresh(Integer.parseInt(month), Integer.parseInt(year));
		setSumLabel();
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public void refresh(int monthNum, int yearNum) {
		table.refresh(monthNum, yearNum);
	}

	private void setSumLabel() {
		double sum = table.getList().stream().mapToDouble(x -> x.getPrice().getActual()).sum();
		actualPriceLabel.setText(String.valueOf(sum));
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
