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
		this.repo.add(object);
	}

	public void update(SheetRow object) {
		this.repo.update(object);
	}

	public void delete(int id) {
		this.repo.delete(id);
	}

	public void delete(boolean isStatic) {
		this.repo.delete(isStatic);
	}

	public void deleteAll() {
		this.repo.deleteAll();
	}

	public List<SheetRow> getAll() {
		return this.repo.getAll();
	}

	public int getCount() {
		return this.repo.getCount();
	}

	public List<SheetRow> getByMonth(int month) {
		return this.repo.getByMonth(month);
	}

	public List<SheetRow> getByYearMonth(int year, int month, boolean isStatic) {
		return this.repo.getByYearMonth(year, month, isStatic);
	}

	public void fill(int lastYear) {
		this.repo.fill(lastYear);
	}

	public Instant getEarliestDate() {
		return this.repo.getEarliestDate();
	}
	
	public void editPrice(int id, double value, boolean isActual){
		this.repo.editPrice(id, value, isActual);
	}

}
