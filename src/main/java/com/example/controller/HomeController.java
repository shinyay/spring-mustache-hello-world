package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/")
class HomeController {
	@GetMapping
	public String index(Model model) {
		model.addAttribute("title", "Mustache Application");

		model.addAttribute("tags", new ArrayList<>(Arrays.asList("spring", "springboot", "mustache")));

		List itemList = IntStream.range(0,9).mapToObj(i -> getItem(i)).collect(Collectors.toList());
		model.addAttribute("itemList", itemList);

		return "index";
	}
	private Item getItem(int i){
		return new Item(String.valueOf(i),"Item"+i, "Sample Description "+i, 100+i);
	}

	private class Item{
		private String code;

		public String getCode() {
			return code;
		}

		public String getName() {
			return name;
		}

		public String getDescription() {
			return description;
		}

		public int getNumber() {
			return number;
		}

		private String name;
		private String description;
		private int number;

		Item(String code, String name, String description, int number) {
			this.code = code;
			this.name = name;
			this.description = description;
			this.number = number;
		}
	}
}
