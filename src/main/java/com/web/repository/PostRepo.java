package com.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.model.Category;
import com.web.model.Post;
import com.web.model.User;
import com.web.payload.PostDto;

public interface PostRepo extends JpaRepository<Post, Integer>{

	//List<Post> findbycategoryList(User user);
  // List<Post> findbyuserList(User user);
   List<Post> findByTitle(String title);
}
