package com.codegym.ung_dung_blog.service;

import com.codegym.ung_dung_blog.model.Blog;
import com.codegym.ung_dung_blog.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService implements IBlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public List<Blog> findAll() {
        return blogRepository.findAll();
    }

    @Override
    public Optional<Blog> findById(Long id) {
        return blogRepository.findById(id);
    }

    @Override
    public Blog save(Blog blog) {
        return blogRepository.save(blog);
    }

    @Override
    public void deleteById(Long id) {
        blogRepository.deleteById(id);
    }

    @Override
    public Page<Blog> getAll(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    public Page<Blog> search(String keyword, Pageable pageable) {
        return blogRepository.searchByKeyword(keyword, pageable);
    }

    @Override
    public Page<Blog> findByCategory(Long categoryId, Pageable pageable) {
        return blogRepository.findByCategoryId(categoryId, pageable);
    }
}