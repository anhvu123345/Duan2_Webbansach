package duan2.nhom11.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import duan2.nhom11.demo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    User findByEmail(String email);
    Optional<User> findByToken(String token);
    List<User> findByEmailContaining(String q);
}
