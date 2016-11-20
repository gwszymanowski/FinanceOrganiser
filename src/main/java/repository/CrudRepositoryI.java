package repository;

import java.util.List;

public interface CrudRepositoryI<T> {
	public void add(T object);

	public void update(T object);

	public void delete(int id);

	public List<T> getAll();
}
