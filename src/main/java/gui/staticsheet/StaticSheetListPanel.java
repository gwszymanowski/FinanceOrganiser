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
		this.setLayout(new BorderLayout());

		JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		topPanel.add(new JLabel("LIST OF EXPENSES:"));
		this.add(topPanel, BorderLayout.NORTH);

		this.bottomPanel = new JPanel(new BorderLayout());
		this.setSlider();

		this.table = new StaticSheetTable(this.slider.getValue(), 2016);
		this.add(this.table, BorderLayout.CENTER);

		this.setYearBoxPanel();
		this.setSumLabel();
		this.addListeners();
		this.initializeYearMonthValues();

		this.add(this.bottomPanel, BorderLayout.SOUTH);

	}

	public void initializeYearMonthValues() {
		int sliderVal = this.slider.getValue();
		this.setMonth(String.valueOf(sliderVal));
		String boxVal = (String) this.yearBox.getSelectedItem();
		this.setYear(boxVal);
	}

	private void setSlider() {
		Calendar c = Calendar.getInstance();

		int currentMonth = c.get(Calendar.MONTH) + 1;

		this.slider = new JSlider(JSlider.HORIZONTAL, 1, 12, 1);

		this.slider.setMajorTickSpacing(3);
		this.slider.setMinorTickSpacing(1);
		this.slider.setPaintTicks(true);
		this.slider.setPaintLabels(true);
		this.slider.setValue(currentMonth);

		this.slider.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 5));
		this.slider.setToolTipText(new String("Choose month"));

		this.bottomPanel.add(slider, BorderLayout.WEST);
	}

	private void setYearBoxPanel() {

		JPanel mainPanel = new JPanel(new BorderLayout());

		JPanel yearBoxPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		this.yearBox = new JComboBox<>();

		for (String k : getYears())
			this.yearBox.addItem(k);

		this.yearBox.setToolTipText(new String("Choose year"));
		this.yearBox.setBorder(BorderFactory.createEmptyBorder(10, 30, 0, 0));

		yearBoxPanel.add(this.yearBox);

		mainPanel.add(yearBoxPanel, BorderLayout.WEST);

		JPanel pricePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		this.actualPriceLabel = new JLabel("");
		this.actualPriceLabel.setBorder(BorderFactory.createEmptyBorder(15, 0, 10, 40));

		pricePanel.add(this.actualPriceLabel);

		mainPanel.add(pricePanel, BorderLayout.EAST);

		this.bottomPanel.add(mainPanel, BorderLayout.CENTER);
	}

	private void addListeners() {

		this.slider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				int sliderVal = slider.getValue();
				setMonth(String.valueOf(sliderVal));
				setGeneralValues();
			}

		});

		this.yearBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String boxVal = (String) yearBox.getSelectedItem();
				setYear(boxVal);
				setGeneralValues();
			}

		});

	}

	private void setGeneralValues() {
		this.refresh(Integer.parseInt(month), Integer.parseInt(year));
		this.setSumLabel();
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public void refresh(int monthNum, int yearNum) {
		this.table.refresh(monthNum, yearNum);
	}

	private void setSumLabel() {
		double sum = table.getList().stream().mapToDouble(x -> x.getPrice().getActual()).sum();
		this.actualPriceLabel.setText(String.valueOf(sum));
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

	public String getMonth() {
		return month;
	}

	public String getYear() {
		return year;
	}

}
