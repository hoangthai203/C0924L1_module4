package com.codegym.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CurrencyConverterController {

    @GetMapping("/")
    public String showForm() {
        return "index";
    }

    @PostMapping("/convert")
    public String convert(@RequestParam("rate") double rate,
                          @RequestParam("usd") double usd,
                          Model model) {
        double result = rate * usd;
        model.addAttribute("result", result);
        model.addAttribute("usd", usd);
        model.addAttribute("rate", rate);
        return "index";
    }
}

