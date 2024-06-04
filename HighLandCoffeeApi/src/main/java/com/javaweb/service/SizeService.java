package com.javaweb.service;

import java.util.List;

import com.javaweb.entity.Size;
import com.javaweb.request.AddCategorySizeRequest;

public interface SizeService {

	public Size createSize(AddCategorySizeRequest size,Long staff_id);
	
	public List<Size> getAllSize();

	public Size findSizeByName(String size_name);

}
