package service;

import java.util.List;

import interfaces.CrudI;
import model.Category;
import repository.CategoryRepository;

public class CategoryService implements CrudI<Category> {

	private CategoryRepository repo;

	public CategoryService() {
		this.repo = new CategoryRepository();
	}

	public boolean add(Category object) {
		return repo.add(object);
	}

	public boolean update(Category object) {
		return repo.update(object);
	}

	public boolean delete(int id) {
		return repo.delete(id);
	}

	public List<Category> getAll() {
		return repo.getAll();
	}

}
