package com.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.model.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
