package com.web.service;

import java.util.List;

import com.web.model.Category;
import com.web.payload.CategoryDto;

public interface CategoryService {
//create 
	CategoryDto createCategogy(CategoryDto categoryDto);
	
	//update
	CategoryDto updateCategogy(CategoryDto categoryDto,Integer categoryId);
	//delete
	void deleteCategogy(Integer categoryId);
//get By Id
	CategoryDto getByIdCategogy(Integer categoryId);
	
//list All
	List<CategoryDto> getAllCategory();
	
}
