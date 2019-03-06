package com.example.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class LayoutAdvice {

    @ModelAttribute
    public void defaultLayout(Model model) {
        model.addAttribute("title", "Demo Application");
    }
}
