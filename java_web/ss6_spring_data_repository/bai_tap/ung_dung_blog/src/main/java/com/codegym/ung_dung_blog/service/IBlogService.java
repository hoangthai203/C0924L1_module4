package com.codegym.ung_dung_blog.service;

import com.codegym.ung_dung_blog.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IBlogService {
    List<Blog> findAll();
    Optional<Blog> findById(Long id);
    Blog save(Blog blog);
    void deleteById(Long id);
    Page<Blog> getAll(Pageable pageable);
    Page<Blog> search(String keyword, Pageable pageable);
    Page<Blog> findByCategory(Long categoryId, Pageable pageable);
}
