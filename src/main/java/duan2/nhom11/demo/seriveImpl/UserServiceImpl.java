package duan2.nhom11.demo.seriveImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import duan2.nhom11.demo.entity.User;
import duan2.nhom11.demo.repository.UserRepository;
import duan2.nhom11.demo.service.UserSerive;

@Service
public class UserServiceImpl implements UserSerive {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public void save(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userRepository.save(user);

	}

	@Override
	public User findByUsername(String email) {

		return userRepository.findByEmail(email);
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return userRepository.count();
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		userRepository.deleteById(id);
	}

	@Override
	public Optional<User> findById(Long id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id);
	}

	@Override
	public Optional<User> findByToken(String token) {
		// TODO Auto-generated method stub
		return userRepository.findByToken(token);
	}

	@Override
	public void saverole(User user) {
		userRepository.save(user);

	}

	@Override
	public List<User> search(String q) {
		// TODO Auto-generated method stub
		return userRepository.findByEmailContaining(q);
	}

	@Override
	public User findByEmail1(String email) {
		// TODO Auto-generated method stub
		return userRepository.findByEmail1(email);
	}

	@Override
	public void save1(User user) {
		// TODO Auto-generated method stub
		userRepository.save(user);
	}

}