package com.codegym.ung_dung_blog.repository;

import com.codegym.ung_dung_blog.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {}
