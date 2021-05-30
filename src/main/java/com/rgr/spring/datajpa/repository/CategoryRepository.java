package com.rgr.spring.datajpa.repository;

import com.rgr.spring.datajpa.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	List<Category> findByTitleContaining(String title);
}
