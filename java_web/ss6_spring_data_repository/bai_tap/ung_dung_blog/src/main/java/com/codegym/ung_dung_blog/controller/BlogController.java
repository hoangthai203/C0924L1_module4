package com.codegym.ung_dung_blog.controller;

import com.codegym.ung_dung_blog.model.Blog;
import com.codegym.ung_dung_blog.repository.CategoryRepository;
import com.codegym.ung_dung_blog.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    private IBlogService blogService;

    @Autowired
    private CategoryRepository categoryRepository;

    // Danh sách blog với tìm kiếm và phân trang
    @GetMapping
    public String list(@RequestParam(defaultValue = "") String keyword,
                       @RequestParam(defaultValue = "0") int page,
                       Model model) {
        Pageable pageable = PageRequest.of(page, 5, Sort.by("createdAt").descending());
        Page<Blog> blogs = keyword.isEmpty() ?
                blogService.getAll(pageable) :
                blogService.search(keyword, pageable);

        model.addAttribute("blogs", blogs);
        model.addAttribute("keyword", keyword);
        model.addAttribute("categories", categoryRepository.findAll());
        return "blog/list";
    }

    // Form tạo mới
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("blog", new Blog());
        return "blog/create";
    }

    @PostMapping("/create")
    public String createBlog(@ModelAttribute Blog blog, RedirectAttributes ra) {
        blogService.save(blog);
        ra.addFlashAttribute("success", "Tạo bài viết thành công!");
        return "redirect:/blogs";
    }

    // Xem chi tiết
    @GetMapping("/{id}")
    public String viewBlog(@PathVariable Long id, Model model) {
        Optional<Blog> blog = blogService.findById(id);
        if (blog.isPresent()) {
            model.addAttribute("blog", blog.get());
            return "blog/view";
        } else {
            return "redirect:/blogs";
        }
    }

    // Sửa blog
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Blog> blog = blogService.findById(id);
        if (blog.isPresent()) {
            model.addAttribute("blog", blog.get());
            return "blog/edit";
        } else {
            return "redirect:/blogs";
        }
    }

    @PostMapping("/{id}/edit")
    public String updateBlog(@PathVariable Long id, @ModelAttribute Blog blog, RedirectAttributes ra) {
        // SỬA LỖI: Lấy blog cũ để preserve createdAt và updatedAt
        Optional<Blog> existingBlogOpt = blogService.findById(id);
        if (existingBlogOpt.isPresent()) {
            Blog existingBlog = existingBlogOpt.get();

            // Cập nhật các field
            existingBlog.setTitle(blog.getTitle());
            existingBlog.setContent(blog.getContent());
            existingBlog.setAuthor(blog.getAuthor());

            // Giữ nguyên createdAt, updatedAt sẽ được tự động set bởi @PreUpdate
            blogService.save(existingBlog);
            ra.addFlashAttribute("success", "Cập nhật bài viết thành công!");
        } else {
            ra.addFlashAttribute("error", "Không tìm thấy bài viết!");
        }

        return "redirect:/blogs";
    }

    // Xoá blog
    @GetMapping("/{id}/delete")
    public String deleteBlog(@PathVariable Long id, RedirectAttributes ra) {
        blogService.deleteById(id);
        ra.addFlashAttribute("success", "Xoá bài viết thành công!");
        return "redirect:/blogs";
    }
}