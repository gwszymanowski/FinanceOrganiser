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
		this.repo.add(object);
	}

	public void update(Item object) {
		this.repo.update(object);
	}

	public void delete(int id) {
		this.repo.delete(id);
	}

	public List<Item> getAll() {
		return this.repo.getAll();
	}

	public int getCount() {
		return this.repo.getCount();
	}

	@Override
	public String toString() {
		return "ItemService []";
	}

}
