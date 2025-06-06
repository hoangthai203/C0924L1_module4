package com.codegym.ung_dung_blog.repository;

import com.codegym.ung_dung_blog.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Long> {
}

