package duan2.nhom11.demo.service;

import java.util.List;
import java.util.Optional;

import duan2.nhom11.demo.entity.Catagory;


public interface CatagoryService {
	void save(Catagory catagory);

	Iterable<Catagory> findAll();

	Long count();

	Optional<Catagory> findById(Long id);

	void delete(Long id);


	boolean existsByCatagoryName(String name);
	
	List<Catagory> search(String q);
	
	String findByCatagory(Long id);
}