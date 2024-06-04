package com.javaweb.reponsitory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.javaweb.entity.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long>{
	@Query(value = "SELECT * FROM role WHERE role_name = ?1  ", nativeQuery = true)
    Role findByName(String name);
    
}
