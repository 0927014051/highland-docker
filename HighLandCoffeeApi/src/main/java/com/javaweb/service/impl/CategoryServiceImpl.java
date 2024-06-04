package com.javaweb.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.javaweb.entity.Category;
import com.javaweb.exception.UserException;
import com.javaweb.reponsitory.CategoryRepo;
import com.javaweb.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	private CategoryRepo categoryRepo;

	public CategoryServiceImpl(CategoryRepo categoryRepo) {
		super();
		this.categoryRepo = categoryRepo;
	}

	@Override
	public Category createCategory(Category category) {
		return categoryRepo.save(category);
	}

	@Override
	public Category findById(Long id) throws UserException{
		Optional<Category> category = categoryRepo.findById(id);
		if(category.isPresent()){
			return category.get();
		}
		throw new UserException("Cart not found with id " + category);
	}

	@Override
	public Category updateCategory(Long id,Long staff_id,Category category) throws UserException {
		Category update = findById(id);
		if(category.getCategory_name().equals(update.getCategory_name())){
			return null;
		}
		update.setCategory_name(category.getCategory_name());
		update.setUpdated_at(LocalDateTime.now());
		update.setUpdated_by(staff_id);
		update.setStatus(category.getStatus());
		return categoryRepo.save(update);
	}

	@Override
	public List<Category> findAll() {
		return categoryRepo.findAll();
	}

	@Override
	public Category deleteCategory(Long id, Long staff_id) throws UserException {
		Category category = findById(id);
		category.setStatus("Unactive");
		category.setUpdated_by(staff_id);
		category.setUpdated_at(LocalDateTime.now());
		return categoryRepo.save(category);
	}

	@Override
	public Category findCategoryByName(String name) {
		// TODO Auto-generated method stub
		return categoryRepo.findCategoryByName(name);
	}

}
