package duan2.nhom11.demo.service;


import duan2.nhom11.demo.entity.OrderDetail;

public interface OrderDetailSerive {

	void save(OrderDetail  orderDetail);

	Iterable<OrderDetail> findAll();

	Long count();

	OrderDetail findById(Long id);

	void delete(Long id);
}
