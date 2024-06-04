package com.javaweb.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.javaweb.entity.Cart;

@Repository
public interface CartRepo extends JpaRepository<Cart, Long>{
	@Query(value =  "SELECT * FROM cart WHERE customer_id = ?1",nativeQuery =  true)
	Cart findCartBCustomerId(Long customer_id);

}
