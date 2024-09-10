package com.example.videogame_collection.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.videogame_collection.dto.GameDTO;
import com.example.videogame_collection.services.DataComparisonService;
import com.example.videogame_collection.services.GameService;
import com.example.videogame_collection.services.LoginManager;

@Controller
public class ChangeController {
	@Autowired
	private LoginManager loginManager;
	
	@Autowired
	private GameService gameService;
	
	@Autowired
	private DataComparisonService dataComparisonService;
	
	private GameDTO game;
	
	
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
		
		game = gameService.getGame(gameID);
		
		
	
		model.addAttribute("username", username);
		model.addAttribute("games", game);
		
		return "change.html";
	}
	
	
	@PostMapping("/change")
	public String changePost(@RequestParam(required = false) String logout, @RequestParam(required = false) String back,
							@RequestParam String gName, @RequestParam String gameSystem, @RequestParam String gameStatus, Model model) {
		
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
		
		
		String tempGameName = gName;
		String tempGameSystem = gameSystem;
		String tempGameStatus = gameStatus;
		
		//this feels terrible to do
		if (checkEmpty(tempGameName)) {
			
			if (dataComparisonService.compareSystem(game.getGameSystem(), tempGameSystem)) {
				
				if (dataComparisonService.compareStatus(game.getGameStatus(), tempGameStatus)) {
					
					return "change.html";
					
				} else {
					
					gameService.updateStatus(game.getid(), tempGameStatus);
					return "change.html";
				}
				
			} else {
				
				//if the game systems are not the same
				gameService.updateSystem(game.getid(), tempGameSystem);
				
				if (dataComparisonService.compareStatus(game.getGameStatus(), tempGameStatus)) {
					
					//do something when no changes needed
					return "change.html";
					
				} else {
					
					gameService.updateStatus(game.getid(), tempGameStatus);
					return "change.html";
				}
			}
			
		} else {
			
			gameService.updateName(game.getid(), tempGameName);
			
			if (dataComparisonService.compareSystem(game.getGameSystem(), tempGameSystem)) {
				
				if (dataComparisonService.compareStatus(game.getGameStatus(), tempGameStatus)) {
					
					//do something when no changes needed
					return "change.html";
					
				} else {
					
					gameService.updateStatus(game.getid(), tempGameStatus);
					return "change.html";
				}
				
			} else {
				
				//if the game systems are not the same
				gameService.updateSystem(game.getid(), tempGameSystem);
				
				if (dataComparisonService.compareStatus(game.getGameStatus(), tempGameStatus)) {
					
					//do something when no changes needed
					return "change.html";
					
				} else {
					
					gameService.updateStatus(game.getid(), tempGameStatus);
					return "change.html";
				}
			}
		}
		
		
		
		//return "redirect:/profile";
	}
	
	
	private boolean checkEmpty(String name) {
		return name.isEmpty();
	}

}
