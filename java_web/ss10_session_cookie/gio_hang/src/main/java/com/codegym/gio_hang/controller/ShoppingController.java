package com.codegym.gio_hang.controller;

import com.codegym.gio_hang.model.Cart;
import com.codegym.gio_hang.model.Product;
import com.codegym.gio_hang.service.IProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ShoppingController {

    @Autowired
    private IProductService productService;

    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable Long id, HttpSession session) {
        Product product = productService.findById(id);
        if (product == null) {
            return "view/error_404";
        }
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
        }
        cart.add(product);
        session.setAttribute("cart", cart);
        return "redirect:/cart";
    }

    @GetMapping("/cart")
    public String viewCart(HttpSession session, Model model) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
        }
        model.addAttribute("cart", cart);
        session.setAttribute("cart", cart);
        return "view/cart";
    }

    @PostMapping("/update")
    public String updateQuantity(@RequestParam Long id, @RequestParam int quantity, HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null) {
            if (quantity > 0) {
                cart.update(id, quantity);
            } else {
                cart.remove(id);
            }
            session.setAttribute("cart", cart);
        }
        return "redirect:/cart";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id, HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null) {
            cart.remove(id);
            session.setAttribute("cart", cart);
        }
        return "redirect:/cart";
    }

    @GetMapping("/checkout")
    public String checkout(HttpSession session, Model model) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null || cart.getItems().isEmpty()) {
            return "redirect:/cart";
        }
        model.addAttribute("total", cart.getTotal());
        return "view/checkout";
    }
}
