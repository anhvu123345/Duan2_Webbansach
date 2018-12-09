package duan2.nhom11.demo.seriveImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import duan2.nhom11.demo.entity.OrderDetail;
import duan2.nhom11.demo.repository.OrderDetailRepository;
import duan2.nhom11.demo.service.OrderDetailSerive;

@Service
public class OrderDetailSeriveImpl implements OrderDetailSerive{

	@Autowired
	private OrderDetailRepository orderDetailRepository;
	@Override 
	public void save(OrderDetail orderDetail) {
		// TODO Auto-generated method stub
		orderDetailRepository.save(orderDetail);
	}

	@Override
	public Iterable<OrderDetail> findAll() {
		// TODO Auto-generated method stub
		return orderDetailRepository.findAll();
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return orderDetailRepository.count();
	}

	@Override
	public Optional<OrderDetail> findById(Long id) {
		// TODO Auto-generated method stub
		return orderDetailRepository.findById(id);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		orderDetailRepository.deleteById(id);
	}

	@Override
	public List<OrderDetail> findByUserIDAndOrderID(Long id) {
		// TODO Auto-generated method stub
		return orderDetailRepository.findByUserIDAndOrderID(id);
	}

	@Override
	public List<OrderDetail> finByOrderID(Long id) {
		// TODO Auto-generated method stub
		return orderDetailRepository.finByOrderID(id);
	}

}
