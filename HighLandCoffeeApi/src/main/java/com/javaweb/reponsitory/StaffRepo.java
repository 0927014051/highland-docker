package com.javaweb.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.javaweb.entity.Staff;

@Repository
public interface StaffRepo extends JpaRepository<Staff, Long>{
	
	@Query(value = "SELECT * FROM Staff WHERE user_id = ?1  ", nativeQuery = true)
	Staff findStaffByUserId(Long user_id);

}
