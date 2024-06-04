package com.javaweb.service;

import java.util.List;

import com.javaweb.entity.Category;
import com.javaweb.exception.UserException;

public interface CategoryService {

	List<Category> findAll();
	
	 Category createCategory(Category category);

	 Category findById(Long id) throws UserException;

	 Category updateCategory(Long id,Long staff_id,Category category) throws UserException;

	 Category deleteCategory(Long id, Long staff_id) throws UserException;

	 Category findCategoryByName(String name);

}
