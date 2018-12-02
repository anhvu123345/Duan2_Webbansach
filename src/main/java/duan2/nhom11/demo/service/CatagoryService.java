package duan2.nhom11.demo.service;

import java.util.Optional;

import duan2.nhom11.demo.entity.Catagory;


public interface CatagoryService {
	void save(Catagory catagory);

	Iterable<Catagory> findAll();

	Long count();

	Optional<Catagory> findById(Long id);

	void delete(Long id);

	boolean existsByCatagoryName(String name);
}