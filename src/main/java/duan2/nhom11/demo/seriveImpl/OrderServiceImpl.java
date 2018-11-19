package duan2.nhom11.demo.seriveImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import duan2.nhom11.demo.entity.Order;
import duan2.nhom11.demo.repository.OrderReposiory;
import duan2.nhom11.demo.service.OrderSerive;

@Service
public class OrderServiceImpl implements OrderSerive {

	@Autowired
	private OrderReposiory orderReposiory;

	@Override
	public void save(Order order) {
		orderReposiory.save(order);

	}

	@Override
	public Iterable<Order> findAll() {
		// TODO Auto-generated method stub
		return orderReposiory.findAll();
	}

	@Override
	public Long count() {

		return orderReposiory.count();
	}

	@Override
	public Optional<Order> findById(Long id) {
		// TODO Auto-generated method stub
		return orderReposiory.findById(id);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		orderReposiory.deleteById(id);

	}

}
