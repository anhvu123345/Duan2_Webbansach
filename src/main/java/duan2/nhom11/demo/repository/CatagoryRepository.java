package duan2.nhom11.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import duan2.nhom11.demo.entity.Catagory;
import duan2.nhom11.demo.entity.User;

@Repository
public interface CatagoryRepository extends JpaRepository<Catagory, Long>{
	boolean existsByCatagoryName(String name);
	List<Catagory> findByCatagoryNameContaining(String q);
	@Query(value="SELECT u.catagory_id FROM catagory u WHERE u.catagory_id = ?1",  nativeQuery = true)
	String findByIdCategory(Long id);
}
