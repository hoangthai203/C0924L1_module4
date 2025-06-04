package com.codegym.quan_ly_san_pham.controller;

import com.codegym.quan_ly_san_pham.model.Product;
import com.codegym.quan_ly_san_pham.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        return "list";
    }

    // Hiển thị form tạo mới sản phẩm
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Product());
        return "create";
    }

    // Xử lý tạo mới sản phẩm
    @PostMapping("/create")
    public String saveProduct(@ModelAttribute Product product) {
        productService.save(product);
        return "redirect:/products";
    }

    // Hiển thị form chỉnh sửa
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable int id, Model model) {
        Product product = productService.findById(id);
        if (product != null) {
            model.addAttribute("product", product);
            return "edit";
        }
        return "redirect:/products";
    }

    // Xử lý cập nhật sản phẩm
    @PostMapping("/{id}/edit")
    public String updateProduct(@PathVariable int id, @ModelAttribute Product product) {
        productService.update(id, product);
        return "redirect:/products";
    }

    // Hiển thị xác nhận xoá
    @GetMapping("/{id}/delete")
    public String showDeleteForm(@PathVariable int id, Model model) {
        Product product = productService.findById(id);
        if (product != null) {
            model.addAttribute("product", product);
            return "delete";
        }
        return "redirect:/products";
    }

    // Xử lý xoá sản phẩm
    @PostMapping("/{id}/delete")
    public String deleteProduct(@PathVariable int id) {
        productService.remove(id);
        return "redirect:/products";
    }

    // Hiển thị chi tiết sản phẩm
    @GetMapping("/{id}/view")
    public String viewProduct(@PathVariable int id, Model model) {
        Product product = productService.findById(id);
        if (product != null) {
            model.addAttribute("product", product);
            return "view";
        }
        return "redirect:/products";
    }

    // Tìm kiếm theo tên
    @GetMapping("/search")
    public String searchProduct(@RequestParam String name, Model model) {
        List<Product> result = productService.searchByName(name);
        model.addAttribute("products", result);
        model.addAttribute("searchValue", name);
        return "list";
    }
}

