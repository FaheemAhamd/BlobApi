package com.web.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.web.config.AppConstant;
import com.web.model.Post;
import com.web.payload.PostDto;
import com.web.payload.PostResponse;
import com.web.payload.UserDto;
import com.web.service.Impl.FileServiceImpl;
import com.web.service.Impl.PostServiceImpl;


@RestController
@RequestMapping("/api/")
public class PostController {

	@Autowired
	private PostServiceImpl serviceImpl;

	@Autowired
	private FileServiceImpl fileServiceImpl;
	@Value("${project.image}")
	private String path;

	// create
	@PostMapping("/user/{userId}/category/{categoryId}/posts")

	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable("userId") Integer userId,
			@PathVariable("categoryId") Integer categoryId) {
		PostDto newPost = this.serviceImpl.createPost(postDto, userId, categoryId);

		return new ResponseEntity<PostDto>(newPost, HttpStatus.ACCEPTED);

	}

	// Get mapping
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> listAllPost(
			@RequestParam(value = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstant.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstant.SORT_BY, required = false) String sortBy

	) {
		PostResponse allPost = this.serviceImpl.getAllPost(pageNumber, pageSize, sortBy);
		return new ResponseEntity<PostResponse>(allPost, HttpStatus.OK);

	}

	// Get by keywords
	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDto>> findByTitle(@PathVariable("keywords") String keywords

	) {
		List<PostDto> result = this.serviceImpl.searchPost(keywords);
		return new ResponseEntity<List<PostDto>>(result, HttpStatus.OK);

	}
	//update post
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updateUser(@RequestBody PostDto postDto,@PathVariable("postId") Integer postId){
		PostDto updatePostDto=this.serviceImpl.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatePostDto,HttpStatus.OK);
	}
	
//upload image
	@PostMapping("/posts/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadImage(@RequestParam("image") MultipartFile image,
			@PathVariable Integer postId) throws IOException {
		PostDto postDto = this.serviceImpl.getPostById(postId);
		String fileName = this.fileServiceImpl.uploadImage(path, image);
		postDto.setImageName(fileName);
		PostDto updatePost = this.serviceImpl.updatePost(postDto, postId);
      return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
	}
}
