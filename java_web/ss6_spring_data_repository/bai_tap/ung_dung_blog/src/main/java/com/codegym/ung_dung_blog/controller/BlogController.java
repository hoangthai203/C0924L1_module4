package com.codegym.ung_dung_blog.controller;

import com.codegym.ung_dung_blog.model.Blog;
import com.codegym.ung_dung_blog.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    private IBlogService blogService;

    //Danh sách blog
    @GetMapping
    public String listBlogs(Model model) {
        List<Blog> blogs = blogService.findAll();

        // Tạo danh sách định dạng ngày dưới dạng chuỗi
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        List<Map<String, Object>> blogDtos = blogs.stream().map(blog -> {
            Map<String, Object> dto = new HashMap<>();
            dto.put("id", blog.getId());
            dto.put("title", blog.getTitle());
            dto.put("author", blog.getAuthor());
            dto.put("content", blog.getContent());

            // SỬA LỖI: Kiểm tra null trước khi format
            if (blog.getCreatedAt() != null) {
                dto.put("createdAt", blog.getCreatedAt().format(formatter));
            } else {
                dto.put("createdAt", "Chưa xác định");
            }

            return dto;
        }).toList();

        model.addAttribute("blogs", blogDtos);
        return "blog/list";
    }

    //Form tạo mới
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

    //Xem chi tiết
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

    //Sửa blog
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
        // SỬA LỖI: Lấy blog cũ để preserve createdAt
        Optional<Blog> existingBlogOpt = blogService.findById(id);
        if (existingBlogOpt.isPresent()) {
            Blog existingBlog = existingBlogOpt.get();

            // Cập nhật các field
            existingBlog.setTitle(blog.getTitle());
            existingBlog.setContent(blog.getContent());
            existingBlog.setAuthor(blog.getAuthor());

            // Giữ nguyên createdAt, chỉ cập nhật updatedAt
            // (updatedAt sẽ được tự động set bởi @PreUpdate)

            blogService.save(existingBlog);
            ra.addFlashAttribute("success", "Cập nhật bài viết thành công!");
        } else {
            ra.addFlashAttribute("error", "Không tìm thấy bài viết!");
        }

        return "redirect:/blogs";
    }

    //Xoá blog
    @GetMapping("/{id}/delete")
    public String deleteBlog(@PathVariable Long id, RedirectAttributes ra) {
        blogService.deleteById(id);
        ra.addFlashAttribute("success", "Xoá bài viết thành công!");
        return "redirect:/blogs";
    }
}