package repository;

import java.util.List;

import interfaces.CrudI;
import model.Category;

public class CategoryRepository implements CrudI<Category> {

	public boolean add(Category object) {
		return false;
	}

	public boolean update(Category object) {
		return false;
	}

	public boolean delete(int id) {
		return false;
	}

	public List<Category> getAll() {
		return null;
	}

}
