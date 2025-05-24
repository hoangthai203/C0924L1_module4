package com.codegym.bai2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CalculatorController {

    @GetMapping("/")
    public String showForm() {
        return "index";
    }

    @PostMapping("/calculate")
    public String calculate(
            @RequestParam("num1") double num1,
            @RequestParam("num2") double num2,
            @RequestParam("operator") String operator,
            Model model
    ) {
        double result = 0;
        String message = "";

        switch (operator) {
            case "+":
                result = num1 + num2;
                message = "Result Addition";
                break;
            case "-":
                result = num1 - num2;
                message = "Result Subtraction";
                break;
            case "*":
                result = num1 * num2;
                message = "Result Multiplication";
                break;
            case "/":
                if (num2 != 0) {
                    result = num1 / num2;
                    message = "Result Division";
                } else {
                    message = "Cannot divide by zero";
                }
                break;
            default:
                message = "Invalid operator";
        }

        model.addAttribute("message", message);
        model.addAttribute("result", result);
        return "index";
    }
}

