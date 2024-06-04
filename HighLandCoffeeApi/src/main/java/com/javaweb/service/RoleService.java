package com.javaweb.service;

import com.javaweb.entity.Role;
import com.javaweb.exception.UserException;

public interface RoleService {
	Role createRole(Role role);
	
	Role findRoleByName(String name);

	Role findById(Long id) throws UserException;
}
