package repository;

import java.util.List;

import interfaces.CrudI;
import model.Item;

public class ItemRepository implements CrudI<Item> {

	public boolean add(Item object) {
		return false;
	}

	public boolean update(Item object) {
		return false;
	}

	public boolean delete(int id) {
		return false;
	}

	public List<Item> getAll() {
		return null;
	}

}
