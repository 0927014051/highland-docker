package com.javaweb.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.javaweb.entity.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long>{
	@Query(value = "SELECT * FROM Customer WHERE user_id = ?1  ", nativeQuery = true)
    Customer findCustomerByUserid(Long userId);
	
	@Query(value = "SELECT * FROM Customer WHERE email = ?1", nativeQuery = true)
	Customer findCustomerByEmail(String email);
}
