package com.javaweb.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.javaweb.entity.Category;
import com.javaweb.entity.Category_Size;
import com.javaweb.entity.Size;
import com.javaweb.reponsitory.CategoryRepo;
import com.javaweb.reponsitory.SizeCategoryRepo;
import com.javaweb.reponsitory.SizeRepo;
import com.javaweb.request.AddCategorySizeRequest;
import com.javaweb.service.SizeService;

@Service
public class SizeServiceImpl implements SizeService{

	private SizeRepo sizeRepo;
	private SizeCategoryRepo sizeCategoryRepo;
	private CategoryRepo categoryRepo;

	public SizeServiceImpl(SizeRepo sizeRepo, SizeCategoryRepo sizeCategoryRepo, CategoryRepo categoryRepo) {
		super();
		this.sizeRepo = sizeRepo;
		this.sizeCategoryRepo = sizeCategoryRepo;
		this.categoryRepo = categoryRepo;
	}
	
	@Override
	public Size createSize(AddCategorySizeRequest size,Long staff_id) {
		Size createSize = new Size();
		createSize.setCreated_at(LocalDateTime.now());
		createSize.setCreated_by(staff_id);
		createSize.setSize_name(size.getSize_name());
		createSize.setUpdate_by(staff_id);
		createSize.setUpdated_at(LocalDateTime.now());
		List<Long> getCateogryName = new ArrayList<>();
		Size savedSize = sizeRepo.save(createSize);
		if(savedSize != null) {
			
			for(Category item : size.getCategory()) {
				Category findId = categoryRepo.findCategoryByName(item.getCategory_name());
				getCateogryName.add(findId.getCategory_id());
			}
			for(Long item : getCateogryName) {
				System.err.println("id " + item);
				Category_Size createCategorySize = new Category_Size();
				createCategorySize.setCategory_id(item);
				createCategorySize.setPercent(size.getPercent());
				createCategorySize.setSize_id(savedSize.getSize_id());
				sizeCategoryRepo.save(createCategorySize);
			}
			
		}
		return savedSize;
	}
	
	@Override
	public List<Size> getAllSize(){
		return sizeRepo.findAll();
	}

	@Override
	public Size findSizeByName(String size_name) {
		return sizeRepo.findSizeByName(size_name);
	}
	
}
