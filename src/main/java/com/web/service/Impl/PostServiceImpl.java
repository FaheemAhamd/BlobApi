package com.web.service.Impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.web.exception.ResourceNotFoundException;
import com.web.model.Category;
import com.web.model.Post;
import com.web.model.User;
import com.web.payload.PostDto;
import com.web.payload.PostResponse;
import com.web.repository.CategoryRepo;
import com.web.repository.PostRepo;
import com.web.repository.UserRepo;
import com.web.service.PostService;
import net.bytebuddy.asm.Advice.This;
@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo repo;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private UserRepo userRepo;
	
	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		User user=this.userRepo.findById(userId)
		.orElseThrow(()-> new ResourceNotFoundException("user", "userId", userId));
		Category category=this.categoryRepo.findById(categoryId)
	   .orElseThrow(()->new ResourceNotFoundException("category", "categoryId", categoryId));
		Post post=this.mapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		Post newPost=this.repo.save(post);
		return this.mapper.map(newPost, PostDto.class);
	}
	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post=this.repo.findById(postId)
			.orElseThrow(()->new ResourceNotFoundException("post", "postId", postId));
	  post.setTitle(postDto.getTitle());
	  post.setContent(postDto.getContent());
	  Post upadatePost=this.repo.save(post);
		return this.mapper.map(upadatePost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		// TODO Auto-generated method stub

	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post=this.repo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post", "postId", postId));
		
		return this.mapper.map(post, PostDto.class);
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy) {
		Pageable p=PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());
		Page<Post> pageList=this.repo.findAll(p);
		List<Post> getList=pageList.getContent();
		List<PostDto> postDtos= getList.stream().map((post)->this.mapper.map(post,PostDto.class)).collect(Collectors.toList());
		PostResponse postResponse=new PostResponse();
		postResponse.setContent(postDtos);
		
		postResponse.setPageNumber(pageList.getNumber());
		postResponse.setPageSize(pageList.getSize());
        postResponse.setTotalElements(pageList.getTotalElements());
        postResponse.setLastPage(pageList.isLast());
		return postResponse;
	}

	@Override
	public List<PostDto> postByCategory(Integer categoryId) {
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category", "categoryId", categoryId));
		
		return null;
	}

	@Override
	public List<PostDto> postByUser(Integer userId) {
		//List<Post>
		
		
		return null;
	}

	@Override
	public List<PostDto> searchPost(String keyword) {
		List<Post> posts= this.repo.findByTitle(keyword);
		List<PostDto> postDtos= posts.stream().map((post)->this.mapper.map(posts, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	

	
}
