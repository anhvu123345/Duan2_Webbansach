package duan2.nhom11.demo.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import duan2.nhom11.demo.entity.OrderDetail;

@Repository
public interface OrderDetailRepository extends CrudRepository<OrderDetail, Long>{

	@Query(value="SELECT * from order_detail od Where od.order_id = '0' AND od.user_id = :user_id  ",  nativeQuery = true)
	List<OrderDetail> findByUserIDAndOrderID(@Param("user_id") Long id);
	
	@Query(value="SELECT * from order_detail od Where od.order_id =?1 ",nativeQuery=true)
	List<OrderDetail> finByOrderID(Long id);
}
