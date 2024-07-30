package com.example.videogame_collection.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {
	
	
	@GetMapping("/register")
	public String register() {
		return "register.html";
	}
	
	
	
	@PostMapping("/register")
	public String registerPost(@RequestParam String username, @RequestParam String password, @RequestParam String passwordAgain, 
								Model model) {
		
		
		
		
		
		return "register.html";
	}
}
