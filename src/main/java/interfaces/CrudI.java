package interfaces;

import java.util.List;

public interface CrudI<T> {

	public boolean add(T object);

	public boolean update(T object);

	public boolean delete(int id);

	public List<T> getAll();

}
