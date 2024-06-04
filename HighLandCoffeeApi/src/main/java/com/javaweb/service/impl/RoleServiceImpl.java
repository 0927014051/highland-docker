package com.javaweb.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.google.cloud.storage.Acl.User;
import com.javaweb.entity.Role;
import com.javaweb.exception.UserException;
import com.javaweb.reponsitory.RoleRepo;
import com.javaweb.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	
	private RoleRepo roleRepo;
	
	
	
	public RoleServiceImpl(RoleRepo roleRepo) {
		this.roleRepo = roleRepo;
	}



	@Override 
	public Role createRole(Role role) {
		return roleRepo.save(role);
	}
	
	@Override
	public Role findRoleByName(String name) {
		return roleRepo.findByName(name);
	}

	@Override
	public Role findById(Long id) throws UserException{
		Optional<Role> role = roleRepo.findById(id);
		if(role.isPresent()){
			return role.get();
		}
		throw new UserException("not found role wiht id " + id );
	}
}
