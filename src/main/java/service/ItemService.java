package service;

import java.util.List;

import interfaces.CrudI;
import model.Item;
import repository.ItemRepository;

public class ItemService implements CrudI<Item> {

	private ItemRepository repo;

	public ItemService() {
		this.repo = new ItemRepository();
	}

	public boolean add(Item object) {
		return repo.add(object);
	}

	public boolean update(Item object) {
		return repo.update(object);
	}

	public boolean delete(int id) {
		return repo.delete(id);
	}

	public List<Item> getAll() {
		return repo.getAll();
	}

}
