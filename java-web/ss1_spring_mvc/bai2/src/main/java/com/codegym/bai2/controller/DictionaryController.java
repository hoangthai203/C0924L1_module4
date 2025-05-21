package com.codegym.bai2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class DictionaryController {

    private static final Map<String, String> dictionary = new HashMap<>();

    static {
        dictionary.put("hello", "xin chào");
        dictionary.put("book", "quyển sách");
        dictionary.put("computer", "máy tính");
        dictionary.put("dog", "con chó");
        dictionary.put("cat", "con mèo");
    }

    @GetMapping("/")
    public String showForm() {
        return "index";
    }

    @PostMapping("/translate")
    public String translate(@RequestParam("word") String word, Model model) {
        String result = dictionary.get(word.toLowerCase());
        model.addAttribute("word", word);
        model.addAttribute("result", result);
        return "index";
    }
}

