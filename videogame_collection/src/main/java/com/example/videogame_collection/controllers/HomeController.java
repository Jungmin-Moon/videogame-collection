package com.example.videogame_collection.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.videogame_collection.services.LoginService;

@Controller
public class HomeController {
	
	@Autowired
	private LoginService loginService;
	
	/*
	public HomeController(LoginService loginService) {
		this.loginService = loginService;
	} */
	
	@GetMapping("/home")
	public String homeGet(@RequestParam(required = false) String login, @RequestParam(required = false) String register) {
		
		if (login != null) {
			return "redirect:/login";
		} 
		
		if (register != null) {
			return "redirect:/register";
		}
		
		return "home.html";
	}
	
	
}
