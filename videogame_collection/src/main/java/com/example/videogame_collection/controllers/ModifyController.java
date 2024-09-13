package com.example.videogame_collection.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.videogame_collection.services.GameService;
import com.example.videogame_collection.services.LoginManager;

@Controller
public class ModifyController {

	@Autowired
	private LoginManager loginManager;
	
	@Autowired
	private GameService gameService;
	
	
	@GetMapping("/modify")
	public String modifyGet(@RequestParam(required = false) String logout, @RequestParam(required = false) String profile, Model model) {
		
		if (logout != null) {
			loginManager.setUsername(null);
		}
		
		String username = loginManager.getUserName();
		
		if (username == null) {
			return "redirect:/home";
		}
		
		if (profile != null) {
			return "redirect:/profile";
		}
		
		var gamesList = gameService.getGames(username);
		
		model.addAttribute("username", username);
		model.addAttribute("games", gamesList); 
		
		return "modify.html";
	}
	
	
	@PostMapping("/modify") 
	public String modifyPost(@RequestParam(required = false) String logout, @RequestParam(required = false) String profile, 
							@RequestParam String gameName, Model model) {
		if (logout != null) {
			loginManager.setUsername(null);
		}
		
		String username = loginManager.getUserName();
		
		if (username == null) {
			return "redirect:/home";
		}
		
		if (profile != null) {
			return "redirect:/profile";
		}
		
		int gameId = gameService.getId(gameName);
		
		StringBuffer sB = new StringBuffer("redirect:/change");
		String gameNameWithUnderscore = gameName.replaceAll("\\W", "_");
		return sB.append("/").append(gameNameWithUnderscore).append("/").append(gameId).toString();
	}

}
