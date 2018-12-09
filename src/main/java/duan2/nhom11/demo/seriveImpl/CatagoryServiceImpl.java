package duan2.nhom11.demo.seriveImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import duan2.nhom11.demo.entity.Catagory;
import duan2.nhom11.demo.repository.CatagoryRepository;
import duan2.nhom11.demo.service.CatagoryService;

@Service
public class CatagoryServiceImpl implements CatagoryService {
	@Autowired
	private CatagoryRepository catagoryRepository;

	@Override
	public void save(Catagory catagory) {
		// TODO Auto-generated method stub
		catagoryRepository.save(catagory);

	}

	@Override
	public Iterable<Catagory> findAll() {
		// TODO Auto-generated method stub
		return catagoryRepository.findAll();
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return catagoryRepository.count();
	}

	@Override
	public Optional<Catagory> findById(Long id) {
		// TODO Auto-generated method stub
		return catagoryRepository.findById(id);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		catagoryRepository.deleteById(id);

	}
	
	@Override
	public boolean existsByCatagoryName(String name) {
		return catagoryRepository.existsByCatagoryName(name);
		
	}
	@Override
	public List<Catagory> search(String q) {
	    // TODO Auto-generated method stub
	    return catagoryRepository.findByCatagoryNameContaining(q);
	}

	@Override
	public String findByCatagory(Long id) {
		// TODO Auto-generated method stub
		return catagoryRepository.findByIdCategory(id);
	}
}