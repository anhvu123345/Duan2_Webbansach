package duan2.nhom11.demo.service;

import java.util.Optional;

import duan2.nhom11.demo.entity.Order;

public interface OrderSerive {
	void save(Order order);

	Iterable<Order> findAll();

	Long count();

	Optional<Order> findById(Long id);

	void delete(Long id);
	
	Iterable<Order> findByUserId(Long id);
}

