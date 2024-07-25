package com.example.videogame_collection.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.videogame_collection.services.LoginService;

@Controller
public class HomeController {
	
	private final LoginService loginService;
	
	public HomeController(LoginService loginService) {
		this.loginService = loginService;
	}
	
	@GetMapping("home")
	public String homeGet() {
		return "home.html";
	}
	
	
	@PostMapping("home")
	public String loginPost(@RequestParam String username, @RequestParam String password, @RequestParam(required = false) String register, Model model) {
		
		loginService.setUsername(username);
		loginService.setPassword(password);
		
		boolean loggedIn = loginService.login(loginService.getUsername(), loginService.getPassword());
		
		if (loggedIn) {
			return "redirect:/userHome";
		}
		
		if (register != null) {
			return "redirect:/register";
		}
		
		model.addAttribute("message", "Wrong credentials or account doesn't exist.");
		
		return "home.html";
	}
	
}
