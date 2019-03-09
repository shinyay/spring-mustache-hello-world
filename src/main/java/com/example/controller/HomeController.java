package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
class HomeController {
	@GetMapping
	public String home(Model model) {
		model.addAttribute("title", "Mustache Application");
		return "index";
	}
}
