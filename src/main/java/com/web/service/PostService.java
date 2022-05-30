package com.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.web.model.Category;
import com.web.model.Post;
import com.web.model.User;
import com.web.payload.PostDto;
import com.web.payload.PostResponse;

public interface PostService {

	//create 
	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	//
	PostDto updatePost(PostDto postDto,Integer postId);
	//delete
	void deletePost(Integer postId);
	
	//get
	PostDto getPostById(Integer postId);
	//get all
	PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy);
	
	List<PostDto> postByCategory(Integer categoryId);
	List<PostDto> postByUser(Integer userId);
	List<PostDto> searchPost(String keyword);
}
