package service;

import java.util.List;

import model.Category;
import repository.CategoryRepository;

public class CategoryService implements CrudServiceI<Category> {

	private CategoryRepository repo;

	public CategoryService() {
		this.repo = new CategoryRepository();
	}

	public void add(Category object) {
		repo.add(object);
	}

	public void update(Category object) {
		repo.update(object);
	}

	public void delete(int id) {
		repo.delete(id);
	}

	public List<Category> getAll() {
		return repo.getAll();
	}

	public int getCount() {
		return repo.getCount();
	}

	@Override
	public String toString() {
		return "CategoryService []";
	}

}
