package duan2.nhom11.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import duan2.nhom11.demo.entity.Catagory;
import duan2.nhom11.demo.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	@Query(value="SELECT u.product_id FROM product u WHERE u.product_id = ?1",  nativeQuery = true)
	Long findByIdProduct(Long id);
	List<Product> findByBookNameContaining(String q);
}
