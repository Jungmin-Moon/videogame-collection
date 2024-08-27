package com.example.videogame_collection.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.videogame_collection.services.GameService;
import com.example.videogame_collection.services.LoginManager;

@Controller
public class ChangeController {
	@Autowired
	private LoginManager loginManager;
	
	@Autowired
	private GameService gameService;
	
	
	@GetMapping("/change/{gameName}")
	@ResponseBody
	public String changeGet(@PathVariable String gameName, @RequestParam(required = false) String logout, @RequestParam(required = false) String back, Model model) {
		/*
		if (logout != null) {
			loginManager.setUsername(null);
		}
		
		String username = loginManager.getUserName();
		
		if (username == null) {
			return "redirect:/home";
		}
		
		if (back != null) {
			return "redirect:/profile";
		} */
		
		System.out.println(gameName);
		
		return "change/{test}";
	}
}
