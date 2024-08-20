package com.example.videogame_collection.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.videogame_collection.dto.GameDTO;
import com.example.videogame_collection.services.GameService;
import com.example.videogame_collection.services.LoginManager;

@Controller
public class ProfileController {

	@Autowired
	private LoginManager loginManager;
	
	@Autowired
	private GameService gameService;
	
	@GetMapping("/profile")
	//@RequestMapping(value = "/profile", method = {RequestMethod.PUT, RequestMethod.GET})
	public String profile(@RequestParam(required = false) String logout, Model model) {
		
		if (logout != null) {
			loginManager.setUsername(null);
		}
		
		
		String username = loginManager.getUserName();
		
		if (username == null) {
			return "redirect:/home";
		}
		
		var games = gameService.getGames(username);
		
		System.out.println(games.get(1).getGameName());
		
		model.addAttribute("username", username);
		model.addAttribute("games", games); 
		
		return "profile";
	}
	
	@PostMapping("/profile")
	public String addGames(@RequestParam String gameName, @RequestParam String gameSystem, @RequestParam String gameStatus, 
						@RequestParam(required = false) String logout, Model model) {
		
		gameService.addGame(gameName, gameSystem, gameStatus, loginManager.getUserName());
		
		List<GameDTO> games = gameService.getGames(loginManager.getUserName());
		
		model.addAttribute("username", loginManager.getUserName());
		model.addAttribute("games", games);
		
		return "profile";
	}
}
