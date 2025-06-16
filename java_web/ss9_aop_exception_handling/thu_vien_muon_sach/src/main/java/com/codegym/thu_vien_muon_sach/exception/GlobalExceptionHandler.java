package com.codegym.thu_vien_muon_sach.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookNotAvailableException.class)
    public String handleBookError(Model model, BookNotAvailableException e) {
        model.addAttribute("error", e.getMessage());
        return "error";
    }

    @ExceptionHandler(InvalidCodeException.class)
    public String handleCodeError(Model model, InvalidCodeException e) {
        model.addAttribute("error", e.getMessage());
        return "error";
    }
}

