package service;

import java.util.List;

import interfaces.CrudI;
import model.SheetRow;
import repository.SheetRowRepository;

public class SheetRowService implements CrudI<SheetRow>{

	private SheetRowRepository repo;
	
	public SheetRowService() {
		this.repo = new SheetRowRepository();
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

	public List<SheetRow> getAll() {
		return repo.getAll();
	}

	public int getCount() {
		return repo.getCount();
	}
	
}
