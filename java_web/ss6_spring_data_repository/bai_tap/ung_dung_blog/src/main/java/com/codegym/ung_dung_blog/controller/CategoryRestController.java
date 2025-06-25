package com.codegym.ung_dung_blog.controller;

import com.codegym.ung_dung_blog.model.Category;
import com.codegym.ung_dung_blog.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryRestController {

    @Autowired
    private CategoryRepository categoryRepository;

    // Xem danh sách tất cả category
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
