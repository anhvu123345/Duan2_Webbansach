package duan2.nhom11.demo.seriveImpl;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import duan2.nhom11.demo.entity.ImageProduct;
import duan2.nhom11.demo.entity.User;
import duan2.nhom11.demo.repository.ImageProductRepository;
import duan2.nhom11.demo.service.ImageProductService;

@Service
public class ImageProductServiceImpl  implements ImageProductService{
	
	@Autowired
	private ImageProductRepository imageProductRepository;

	@Override
	public Iterable<ImageProduct> findAll() {
		// TODO Auto-generated method stub
		return imageProductRepository.findAll();
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return imageProductRepository.count();
	}

	@Override
	public Optional<ImageProduct> findById(Long id) {
		// TODO Auto-generated method stub
		return imageProductRepository.findById(id);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		imageProductRepository.deleteById(id);

	}

	@Override
	public Optional<ImageProduct> findByProduct(Long id) {
		// TODO Auto-generated method stub
		return imageProductRepository.findByProduct(id);
	}

	@Override
	public String findByIdProduct(Long id) {
		// TODO Auto-generated method stub
		return imageProductRepository.findByIdProduct(id);
	}

	@Override
	public void saveAndFlush(ImageProduct imageProduct) {
		// TODO Auto-generated method stub
		imageProductRepository.save(imageProduct);
	}

	@Override
	public Iterable<ImageProduct> findByProductid(Long id) {
		// TODO Auto-generated method stub
		return imageProductRepository.findByProductid(id);
	}

}
