package duan2.nhom11.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import duan2.nhom11.demo.entity.ImageProduct;

@Repository
public interface ImageProductRepository  extends JpaRepository<ImageProduct, Long> {
	
	@Query(value="SELECT * FROM image_product u WHERE u.product_product_id = ?1",  nativeQuery = true)
	Optional<ImageProduct> findByProduct(Long id);
	
	@Query(value="SELECT u.image_id FROM image_product u WHERE u.product_product_id= ?1",  nativeQuery = true)
	String findByIdProduct(Long id);

	@Query(value="SELECT * FROM image_product u WHERE u.product_product_id = ?1",  nativeQuery = true)
	Iterable<ImageProduct> findByProductid(Long id);
}
