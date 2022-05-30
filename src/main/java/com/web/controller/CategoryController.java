package com.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.model.Category;
import com.web.payload.ApiResponse;
import com.web.payload.CategoryDto;
import com.web.payload.UserDto;
import com.web.service.Impl.CategoryServiceImpl;

@RestController
@RequestMapping("/category/api")
public class CategoryController {
	@Autowired
	private CategoryServiceImpl serviceImpl;

	//create 
	@PostMapping("/")
	public ResponseEntity<CategoryDto>  createCategory(@Valid @RequestBody CategoryDto categoryDto) {
		
		CategoryDto category= this.serviceImpl.createCategogy(categoryDto);
		 return new ResponseEntity<CategoryDto>(category,HttpStatus.CREATED);
	}
	//update 
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable("categoryId") Integer categoryId){
		CategoryDto categoryDto2=this.serviceImpl.updateCategogy(categoryDto, categoryId);
		return new ResponseEntity<CategoryDto>(categoryDto2,HttpStatus.OK);
	}
	
	//delete
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("categoryId") Integer categoryId){
		this.serviceImpl.deleteCategogy(categoryId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Delete Category successfully", true), HttpStatus.OK);
		
	}
	
	//get
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getById(@PathVariable("categoryId")Integer categoryId){
		CategoryDto categoryDto=this.serviceImpl.getByIdCategogy(categoryId);
		return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.OK);
		
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> listCategories(){
	List<CategoryDto> categories=this.serviceImpl.getAllCategory();
		return  ResponseEntity.ok(categories);	
				}
}
