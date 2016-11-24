package service;

import java.util.List;

import model.SheetMonth;
import repository.SheetMonthRepository;

public class SheetMonthService {

	private SheetMonthRepository repo;

	public SheetMonthService() {
		this.repo = new SheetMonthRepository();
	}

	public void fill(int year) {
		repo.fill(year);
	}
	
	public List<SheetMonth> getByYear(int year) {
		return repo.getSheets(year);
	}


}
