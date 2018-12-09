package duan2.nhom11.demo.service;


import java.util.List;
import java.util.Optional;

import duan2.nhom11.demo.entity.OrderDetail;

public interface OrderDetailSerive {

	void save(OrderDetail  orderDetail);

	Iterable<OrderDetail> findAll();

	Long count();

	Optional<OrderDetail> findById(Long id);

	void delete(Long id);
	
	List<OrderDetail> findByUserIDAndOrderID(Long id);
	
	List<OrderDetail> finByOrderID(Long id);
}
