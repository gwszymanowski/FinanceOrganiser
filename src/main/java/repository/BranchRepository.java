package repository;

import java.util.List;

import interfaces.CrudI;
import model.Branch;

public class BranchRepository implements CrudI<Branch> {

	public boolean add(Branch object) {
		return false;
	}

	public boolean update(Branch object) {
		return false;
	}

	public boolean delete(int id) {
		return false;
	}

	public List<Branch> getAll() {
		return null;
	}

}
