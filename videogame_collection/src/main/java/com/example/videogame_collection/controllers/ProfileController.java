package com.example.videogame_collection.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.videogame_collection.services.LoginManager;

@Controller
public class ProfileController {

	
	private final LoginManager loginManager;
	
	ProfileController(LoginManager loginManager) {
		this.loginManager= loginManager;	
	}
	
	
	@GetMapping("/profile")
	public String profile(@RequestParam(required = false) String logout, Model model) {
		
		if (logout != null) {
			loginManager.setUsername(null);
		}
		
		
		String username = loginManager.getUserName();
		
		if (username == null) {
			return "redirect:/home";
		}
		
		
		model.addAttribute("username", username);
		return "profile.html";
	}
}
