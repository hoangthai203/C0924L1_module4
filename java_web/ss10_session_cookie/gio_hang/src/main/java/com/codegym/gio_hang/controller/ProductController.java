package com.codegym.gio_hang.controller;

import com.codegym.gio_hang.model.Product;
import com.codegym.gio_hang.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping("/shop")
    public String showShop(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "view/shop";
    }

    @GetMapping("/product/{id}")
    public String showDetail(@PathVariable Long id, Model model) {
        Product product = productService.findById(id);
        if (product == null) {
            return "view/error_404";
        }
        model.addAttribute("product", product);
        return "view/product_detail";
    }
}
