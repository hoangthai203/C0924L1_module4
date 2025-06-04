package com.codegym.bai_tap1.controller;

import com.codegym.bai_tap1.model.EmailConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
public class EmailController {

    @GetMapping("/")
    public String showForm(Model model) {
        EmailConfig config = new EmailConfig("English", 25, true, "Thor\nKing, Asgard");
        model.addAttribute("emailConfig", config);

        model.addAttribute("languages", Arrays.asList("English", "Vietnamese", "Japanese", "Chinese"));
        model.addAttribute("pageSizes", Arrays.asList(5, 10, 15, 25, 50, 100));

        return "index";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("emailConfig") EmailConfig config, Model model) {
        model.addAttribute("emailConfig", config);
        return "result";
    }
}
