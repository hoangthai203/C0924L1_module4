package com.codegym.ung_dung_blog.controller;

import com.codegym.ung_dung_blog.model.Blog;
import com.codegym.ung_dung_blog.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blogs")
public class BlogRestController {

    @Autowired
    private IBlogService blogService;

    //Xem danh sách tất cả blog (có hỗ trợ phân trang)
    @GetMapping
    public List<Blog> getAllBlogs(@PageableDefault(size = 10) Pageable pageable) {
        return blogService.getAll(pageable).getContent();
    }

    //Xem danh sách blog theo category
    @GetMapping("/category/{categoryId}")
    public List<Blog> getBlogsByCategory(@PathVariable Long categoryId, @PageableDefault(size = 10) Pageable pageable) {
        return blogService.findByCategory(categoryId, pageable).getContent();
    }

    //Xem chi tiết blog theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Blog> getBlogById(@PathVariable Long id) {
        return blogService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
