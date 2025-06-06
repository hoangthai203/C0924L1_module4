package com.codegym.ung_dung_blog.service;

import com.codegym.ung_dung_blog.model.Blog;

import java.util.List;
import java.util.Optional;

public interface IBlogService {
    List<Blog> findAll();
    Optional<Blog> findById(Long id);
    Blog save(Blog blog);
    void deleteById(Long id);
}
