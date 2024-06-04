package com.javaweb.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javaweb.entity.Bill;

@Repository
public interface BillRepo extends JpaRepository<Bill, Long>{
	

}
