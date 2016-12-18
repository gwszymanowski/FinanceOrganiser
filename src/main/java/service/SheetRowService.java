package service;

import java.time.Instant;
import java.util.List;

import model.SheetRow;
import repository.SheetRowRepository;

public class SheetRowService implements CrudServiceI<SheetRow> {

	private SheetRowRepository repo;

	public SheetRowService() {
		this.repo = new SheetRowRepository();
	}

	public SheetRowService(boolean isStatic) {
		this.repo = new SheetRowRepository(isStatic);
	}

	public void add(SheetRow object) {
		repo.add(object);
	}

	public void update(SheetRow object) {
		repo.update(object);
	}

	public void delete(int id) {
		repo.delete(id);
	}

	public void delete(boolean isStatic) {
		repo.delete(isStatic);
	}

	public void deleteAll() {
		repo.deleteAll();
	}

	public List<SheetRow> getAll() {
		return repo.getAll();
	}

	public int getCount() {
		return repo.getCount();
	}

	public List<SheetRow> getByMonth(int month) {
		return repo.getByMonth(month);
	}

	public List<SheetRow> getByYearMonth(int year, int month, boolean isStatic) {
		return repo.getByYearMonth(year, month, isStatic);
	}

	public void fill(int lastYear) {
		repo.fill(lastYear);
	}

	public Instant getEarliestDate() {
		return repo.getEarliestDate();
	}
	
	public void editPrice(int id, double value, boolean isActual){
		repo.editPrice(id, value, isActual);
	}

}
