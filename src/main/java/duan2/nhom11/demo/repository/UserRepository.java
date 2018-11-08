package duan2.nhom11.demo.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import duan2.nhom11.demo.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	User findByEmail(String email);

	 Optional<User> findByToken(String token);
}
