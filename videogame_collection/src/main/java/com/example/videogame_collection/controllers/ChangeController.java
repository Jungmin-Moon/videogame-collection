package com.example.videogame_collection.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.videogame_collection.services.GameService;
import com.example.videogame_collection.services.LoginManager;

@Controller
public class ChangeController {
	@Autowired
	private LoginManager loginManager;
	
	@Autowired
	private GameService gameService;
	
	
	@GetMapping("/change/{gameName}/{gameID}")
	public String changeGet(@PathVariable String gameName, @PathVariable int gameID, @RequestParam(required = false) String logout, @RequestParam(required = false) String back, Model model) {
		
		if (logout != null) {
			loginManager.setUsername(null);
		}
		
		String username = loginManager.getUserName();
		
		if (username == null) {
			return "redirect:/home";
		}
		
		if (back != null) {
			return "redirect:/modify";
		} 
		
		var game = gameService.getGame(gameID);
		
		
	
		model.addAttribute("username", username);
		model.addAttribute("games", game);
		
		
		return "change.html";
	}
	
	
	@PostMapping("/change/{gameName}/{gameID}")
	public String changePost(@PathVariable String gameName, @PathVariable int gameID, @RequestParam(required = false) String logout, @RequestParam(required = false) String back,
							@RequestParam String gName, @RequestParam String gameSystem, @RequestParam String gameStatus) {
		
		if (logout != null) {
			loginManager.setUsername(null);
		}
		
		String username = loginManager.getUserName();
		
		if (username == null) {
			return "redirect:/home";
		}
		
		if (back != null) {
			return "redirect:modify";
		}
		
		var game = gameService.getGame(gameID);
		
		String tempGameName = gName;
		String tempGameSystem = gameSystem;
		String tempGameStatus = gameStatus;
		
		//need a filler method to compare which aspects need to be updated
		
		return "redirect:/profile";
	}
}
