package duan2.nhom11.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import duan2.nhom11.demo.entity.Catagory;

@Repository
public interface CatagoryRepository extends JpaRepository<Catagory, Long>{
	boolean existsByCatagoryName(String name);
}