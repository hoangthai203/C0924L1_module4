package com.codegym.thu_vien_muon_sach.controller;

import com.codegym.thu_vien_muon_sach.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "list";
    }

    @GetMapping("/{id}/borrow")
    public String showBorrowForm(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.findById(id));
        return "borrow";
    }

    @PostMapping("/{id}/borrow")
    public String borrow(@PathVariable Long id, RedirectAttributes ra) {
        String code = bookService.borrowBook(id);
        ra.addFlashAttribute("message", "Borrow code: " + code);
        return "redirect:/books";
    }

    @GetMapping("/return")
    public String returnForm() {
        return "return";
    }

    @PostMapping("/return")
    public String returnBook(@RequestParam String code, RedirectAttributes ra) {
        bookService.returnBook(code);
        ra.addFlashAttribute("message", "Returned successfully.");
        return "redirect:/books";
    }
}

