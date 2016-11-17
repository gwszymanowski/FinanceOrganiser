package service;

import java.util.List;

import interfaces.CrudI;
import model.Branch;
import repository.BranchRepository;

public class BranchService implements CrudI<Branch> {

	private BranchRepository repo;
	
	public BranchService() {
		this.repo = new BranchRepository();
	}
	
	public boolean add(Branch object) {
		return repo.add(object);
	}

	public boolean update(Branch object) {
		return repo.update(object);
	}

	public boolean delete(int id) {
		return repo.delete(id);
	}

	public List<Branch> getAll() {
		return repo.getAll();
	}

}
