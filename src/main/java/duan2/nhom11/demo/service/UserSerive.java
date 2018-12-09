package duan2.nhom11.demo.service;

import java.security.Principal;
import java.util.List;
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

    void saverole(User user);

    List<User> search(String q);
    
    User findByEmail1(String email);
    
    void save1(User user);
}
