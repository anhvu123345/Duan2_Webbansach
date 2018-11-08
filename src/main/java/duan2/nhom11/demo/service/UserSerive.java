package duan2.nhom11.demo.service;

import java.util.Optional;

import duan2.nhom11.demo.entity.User;

public interface UserSerive {
	void save(User user);

	User findByUsername(String email);

	Iterable<User> findAll();

	Long count();

	Optional<User> findById(Long id);

	void delete(Long id);
	
	 Optional<User> findByToken(String token);
}
