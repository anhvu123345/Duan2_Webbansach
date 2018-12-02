package duan2.nhom11.demo.service;

import java.util.List;
import java.util.Optional;

import duan2.nhom11.demo.entity.ImageProduct;
import duan2.nhom11.demo.entity.User;;

public interface ImageProductService {
	void saveAndFlush(ImageProduct imageProduct);

	Iterable<ImageProduct> findAll();

	Long count();

	Optional<ImageProduct> findById(Long id);

	void delete(Long id);
	
	Optional<ImageProduct> findByProduct(Long id);
	
	String findByIdProduct(Long id);
	
	Iterable<ImageProduct> findByProductid(Long id);
	
}
