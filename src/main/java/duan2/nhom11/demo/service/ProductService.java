package duan2.nhom11.demo.service;

import java.util.Optional;

import duan2.nhom11.demo.entity.Product;

public interface ProductService {
	void save(Product product);

	Iterable<Product> findAll();

	Long count();

	Optional<Product> findById(Long id);

	void delete(Long id);
	
	
	
}
 