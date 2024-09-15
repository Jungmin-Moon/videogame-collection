package com.example.videogame_collection.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.videogame_collection.services.LoginManager;
import com.example.videogame_collection.services.RegistrationService;

@Controller
public class RegisterController {
	
	@Autowired
	private RegistrationService registrationService;
	
	/*
	RegisterController(RegistrationService registrationService) {
		this.registrationService = registrationService;
	} */
	
	@GetMapping("/register")
	public String register(@RequestParam(required = false) String back, @RequestParam(required = false) String login) {
		
		if (back != null) {
			return "redirect:/home";
		}
		
		if (login != null) {
			return "redirect:/login";
		}
		
		return "register.html";
	}
	
	
	
	@PostMapping("/register")
	public String registerPost(@RequestParam String username, @RequestParam String password, @RequestParam String passwordAgain, 
								Model model) {
		
		String usernameHold = username;
		String passwordHold = password;
		String passwordHold2 = passwordAgain;
		
		
		if (registrationService.passwordSame(passwordHold, passwordHold2)) {
			String hashedSaltedPass = registrationService.generateHashSaltPass(passwordHold);
			
			if (registrationService.checkUsernameExist(usernameHold)) {
				model.addAttribute("message", "Username already exists. Please use a different name.");
			} else {
				registrationService.createUser(usernameHold, hashedSaltedPass);
				return "redirect:/home";
			}
			
		} else {
			model.addAttribute("message", "Passwords do not match.");
		}
		
		
		return "register.html";
	}
}
