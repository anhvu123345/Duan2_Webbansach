package duan2.nhom11.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import duan2.nhom11.demo.entity.Order;

@Repository
public interface OrderReposiory extends  CrudRepository<Order, Long> {

	@Query(value="SELECT *  FROM orders o WHERE o.user_id = ?",  nativeQuery = true)
	Iterable<Order> findByUserId(Long id);
}
