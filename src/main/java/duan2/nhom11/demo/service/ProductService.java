package duan2.nhom11.demo.service;

import java.util.List;
import java.util.Optional;

import duan2.nhom11.demo.entity.Product;

public interface ProductService {
	//lưu sản phẩm
	void save(Product product);

	// lấy ra danh sách sản phẩm
	Iterable<Product> findAll();
	
	//đếm số lượng sản phẩm
	Long count();
	
	// tìm sản phẩm theo id
	Optional<Product> findById(Long id);

	// xóa sản phẩm theo id
	void delete(Long id);
	
	// tìm sản phẩm theo id
	Long findByIdProduct(Long id);
	
	// tìm sản phẩm theo một chuỗi
	List<Product> search(String q);
	
	// tìm sản phẩm theo mã danh mục
	Iterable<Product> findByIdCategory(Long id);
	
}
