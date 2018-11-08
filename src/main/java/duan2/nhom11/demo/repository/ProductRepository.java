package duan2.nhom11.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import duan2.nhom11.demo.entity.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>{
  
}
