package com.codegym.ung_dung_blog.controller;

import org.springframework.ui.Model;
import com.codegym.ung_dung_blog.model.Category;
import com.codegym.ung_dung_blog.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepo;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("categories", categoryRepo.findAll());
        return "category/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("category", new Category());
        return "category/form";
    }

    @PostMapping("/create")
    public String create(Category category, RedirectAttributes ra) {
        categoryRepo.save(category);
        ra.addFlashAttribute("success", "Đã tạo danh mục thành công");
        return "redirect:/categories";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("category", categoryRepo.findById(id).orElseThrow());
        return "category/form";
    }

    @PostMapping("/{id}/edit")
    public String update(Category category, RedirectAttributes ra) {
        categoryRepo.save(category);
        ra.addFlashAttribute("success", "Đã cập nhật danh mục");
        return "redirect:/categories";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        categoryRepo.deleteById(id);
        ra.addFlashAttribute("success", "Đã xoá danh mục");
        return "redirect:/categories";
    }
}
