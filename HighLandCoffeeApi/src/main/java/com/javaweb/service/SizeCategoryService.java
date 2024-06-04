package com.javaweb.service;

import java.util.List;

import com.javaweb.entity.Category_Size;
import com.javaweb.request.AddCategorySizeRequest;

public interface SizeCategoryService {
	
	List<Category_Size> getAllCategorySize();

	Category_Size findCategory_SizeBySizeId(Long id, Long category_id);

	List<Category_Size> findCategory_SizeByCategoryId(Long category_id);

}
