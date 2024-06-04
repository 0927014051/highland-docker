package com.javaweb.reponsitory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.javaweb.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User,Long>{
	@Query(value = "SELECT * FROM user WHERE username = ?1  ", nativeQuery = true)
    User findByUsername(String username);
	}
