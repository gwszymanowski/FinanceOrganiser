package service;

import java.util.List;

public interface CrudServiceI<T> {
	public void add(T object);

	public void update(T object);

	public void delete(int id);

	public List<T> getAll();
}
