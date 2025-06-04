package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    // Hiển thị danh sách sản phẩm
    @GetMapping
    public String showList(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "products/list";
    }

    // Hiển thị form tạo mới sản phẩm
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Product());
        return "products/create";
    }

    // Xử lý tạo mới sản phẩm
    @PostMapping("/create")
    public String saveProduct(@ModelAttribute Product product, RedirectAttributes redirectAttributes) {
        try {
            productService.save(product);
            redirectAttributes.addFlashAttribute("successMessage", "Tạo sản phẩm thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi khi tạo sản phẩm: " + e.getMessage());
        }
        return "redirect:/products";
    }

    // Hiển thị form chỉnh sửa
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable int id, Model model, RedirectAttributes redirectAttributes) {
        Product product = productService.findById(id);
        if (product != null) {
            model.addAttribute("product", product);
            return "products/edit";
        }
        redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy sản phẩm!");
        return "redirect:/products";
    }

    // Xử lý cập nhật sản phẩm
    @PostMapping("/{id}/edit")
    public String updateProduct(@PathVariable("id") int id, @ModelAttribute Product product, RedirectAttributes redirectAttributes) {
        try {
            productService.update(id, product);
            redirectAttributes.addFlashAttribute("successMessage", "Cập nhật sản phẩm thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi khi cập nhật sản phẩm: " + e.getMessage());
        }
        return "redirect:/products";
    }

    // Hiển thị xác nhận xoá
    @GetMapping("/{id}/delete")
    public String showDeleteForm(@PathVariable("id") int id, Model model, RedirectAttributes redirectAttributes) {
        Product product = productService.findById(id);
        if (product != null) {
            model.addAttribute("product", product);
            return "products/delete";
        }
        redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy sản phẩm!");
        return "redirect:/products";
    }

    // Xử lý xoá sản phẩm
    @PostMapping("/{id}/delete")
    public String deleteProduct(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        try {
            productService.remove(id);
            redirectAttributes.addFlashAttribute("successMessage", "Xóa sản phẩm thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi khi xóa sản phẩm: " + e.getMessage());
        }
        return "redirect:/products";
    }

    // Hiển thị chi tiết sản phẩm
    @GetMapping("/{id}/view")
    public String viewProduct(@PathVariable int id, Model model, RedirectAttributes redirectAttributes) {
        Product product = productService.findById(id);
        if (product != null) {
            model.addAttribute("product", product);
            return "products/view";
        }
        redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy sản phẩm!");
        return "redirect:/products";
    }

    // Tìm kiếm theo tên
    @GetMapping("/search")
    public String searchProduct(@RequestParam(required = false) String name, Model model) {
        List<Product> result;
        if (name != null && !name.trim().isEmpty()) {
            result = productService.searchByName(name.trim());
        } else {
            result = productService.findAll();
        }
        model.addAttribute("products", result);
        model.addAttribute("searchValue", name);
        return "products/list";
    }
}

