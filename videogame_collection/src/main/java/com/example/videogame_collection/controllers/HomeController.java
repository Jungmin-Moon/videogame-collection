package com.example.videogame_collection.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
	
	
	@GetMapping("home")
	public String homeGet() {
		return "home.html";
	}
	
	
	@PostMapping("home")
	public String loginPost(@RequestParam String username, @RequestParam String password, Model model) {
		
	}
}
