package service;

import java.util.List;

import model.Item;
import repository.ItemRepository;

public class ItemService implements CrudServiceI<Item> {

	private ItemRepository repo;

	public ItemService() {
		this.repo = new ItemRepository();
	}

	public void add(Item object) {
		repo.add(object);
	}

	public void update(Item object) {
		repo.update(object);
	}

	public void delete(int id) {
		repo.delete(id);
	}

	public List<Item> getAll() {
		return repo.getAll();
	}

	public int getCount() {
		return repo.getCount();
	}

	@Override
	public String toString() {
		return "ItemService []";
	}

}
