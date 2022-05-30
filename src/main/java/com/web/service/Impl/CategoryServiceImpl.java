package com.web.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.exception.ResourceNotFoundException;
import com.web.model.Category;
import com.web.payload.CategoryDto;
import com.web.repository.CategoryRepo;
import com.web.service.CategoryService;

import net.bytebuddy.asm.Advice.This;
@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo repo;
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public CategoryDto createCategogy(CategoryDto categoryDto) {
		Category category=modelMapper.map(categoryDto, Category.class);
		Category addedCategory=this.repo.save(category);
		return this.modelMapper.map(addedCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategogy(CategoryDto categoryDto, Integer categoryId) {
		Category category=this.repo.findById(categoryId)
				.orElseThrow(() ->new ResourceNotFoundException("Category", "categoryId", categoryId));
				
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		Category upadatedCategory=this.repo.save(category);
		return this.modelMapper.map(upadatedCategory, CategoryDto.class);
	}

	@Override
	public void deleteCategogy(Integer categoryId) {
Category category=this.repo.findById(categoryId)
.orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
this.repo.delete(category);
		
	}

	@Override
	public CategoryDto getByIdCategogy(Integer categoryId) {
		Category category=this.repo.findById(categoryId)
	.orElseThrow(()->new ResourceNotFoundException("category", "categoryId", categoryId));
		return this.modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> categories=this.repo.findAll();
		List<CategoryDto> categoryDtos= categories.stream().map(
	(category)->this.modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
		return categoryDtos;
	}

}
