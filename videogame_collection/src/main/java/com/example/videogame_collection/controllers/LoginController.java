package com.example.videogame_collection.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.videogame_collection.services.LoginService;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	/*
	LoginController(LoginService loginService) {
		this.loginService = loginService;
	} */
	
	@GetMapping("/login")
	public String login() {
		return "login.html";
	}
	
	
	
	@PostMapping("/login")
	public String loginPost(@RequestParam String username, @RequestParam String password, Model model) {
		loginService.setUsername(username);
		loginService.setPassword(password);
		
		boolean logged = loginService.login(username, password);
		
		if (logged) {
			return "redirect:/profile";
		}
		
		model.addAttribute("message", "Incorrect credentials");
		
		return "login.html";
	}
}
