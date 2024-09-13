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
	public String changeGet(@PathVariable int gameID, @RequestParam(required = false) String logout, @RequestParam(required = false) String modify, Model model) {
		
		if (logout != null) {
			loginManager.setUsername(null);
		}
		
		String username = loginManager.getUserName();
		
		if (username == null) {
			return "redirect:/home";
		}
		
		if (modify != null) {
			return "redirect:/modify";
		} 
		
		game = gameService.getGame(gameID);
		
		model.addAttribute("username", username);
		model.addAttribute("games", game);
		
		return "change.html";
	}
	
	@GetMapping("/change") 
	public String changeGetNormal(@RequestParam(required = false) String logout, @RequestParam(required = false) String modify, Model model) {
		
		if (logout != null) {
			loginManager.setUsername(null);
		}
		
		String username = loginManager.getUserName();
		
		if (username == null) {
			return "redirect:/home";
		}
		
		if (modify != null) {
			return "redirect:/modify";
		}
		
		model.addAttribute("username", username);
		model.addAttribute("games", game);
		
		return "change.html";
	}
	
	
	@PostMapping("/change")
	public String changePost(@RequestParam(required = false) String logout, @RequestParam(required = false) String modify,
							@RequestParam(required = false) String gName, @RequestParam String gameSystem, @RequestParam String gameStatus, Model model) {
		
		if (logout != null) {
			loginManager.setUsername(null);
		}
		
		String username = loginManager.getUserName();
		
		if (username == null) {
			game = null;
			return "redirect:/home";
		}
		
		if (modify != null) {
			game = null;
			return "redirect:/modify";
		}
		
		
		String oldName = game.getGameName();
		String oldSystem = game.getGameSystem();
		String oldStatus = game.getGameStatus();
		
		String tempGameName = gName;
		String tempGameSystem = gameSystem;
		String tempGameStatus = gameStatus;
		
		model.addAttribute("username", username);
		
		//this feels terrible to do
		if (checkNull(tempGameName)) {
			
			//empty string no changes needed
			model.addAttribute("nameChange", "There were no changes to the name.");
			
			if (dataComparisonService.compareSystem(game.getGameSystem(), tempGameSystem)) {
				
				//no change to the system
				model.addAttribute("systemChange", "There were no changes to the system.");
				
				if (dataComparisonService.compareStatus(game.getGameStatus(), tempGameStatus)) {
					
					//no changes at all
					model.addAttribute("statusChange", "There were no changes to the status.");
					model.addAttribute("noChange", "There were no changes to be made.");
					return "change.html";
					
				} else {
					
					gameService.updateStatus(game.getid(), tempGameStatus);
					
					model.addAttribute("statusChange", "Status for game was changed from: " + oldStatus + " to " + tempGameStatus);
					return "change.html";
				}
				
			} else {
				
				//if the game systems are not the same
				gameService.updateSystem(game.getid(), tempGameSystem);
				model.addAttribute("systemChange", "The system for the game was changed from: " + oldSystem + " to " + tempGameSystem);
				
				if (dataComparisonService.compareStatus(game.getGameStatus(), tempGameStatus)) {
					
					//do something when no changes needed
					model.addAttribute("statusChange", "There were no changes to the status.");
					return "change.html";
					
				} else {
					
					gameService.updateStatus(game.getid(), tempGameStatus);
					
					model.addAttribute("statusChange", "Status for game was changed from: " + oldStatus + " to " + tempGameStatus);
					return "change.html";
				}
			}
			
		} else {
			
			gameService.updateName(game.getid(), tempGameName);
			model.addAttribute("nameChange", "Name for the game was changed from: " + oldName + " to " + tempGameName);
			
			if (dataComparisonService.compareSystem(game.getGameSystem(), tempGameSystem)) {
				
				model.addAttribute("systemChange", "There were no changes to the system.");
				
				
				if (dataComparisonService.compareStatus(game.getGameStatus(), tempGameStatus)) {
					
					//do something when no changes needed
					model.addAttribute("statusChange", "There were no changes to the status.");
					
					model.addAttribute("noChange", "There were no changes to be made.");
					
					return "change.html";
					
				} else {
					
					gameService.updateStatus(game.getid(), tempGameStatus);
					model.addAttribute("statusChange", "Status for game was changed from: " + oldStatus + " to " + tempGameStatus);
					
					return "change.html";
				}
				
			} else {
				
				//if the game systems are not the same
				gameService.updateSystem(game.getid(), tempGameSystem);
				model.addAttribute("systemChange", "The system for the game was changed from: " + oldSystem + " to " + tempGameSystem);
				
				
				if (dataComparisonService.compareStatus(game.getGameStatus(), tempGameStatus)) {
					
					//do something when no changes needed
					model.addAttribute("statusChange", "There were no changes to the status.");
					
					return "change.html";
					
				} else {
					
					gameService.updateStatus(game.getid(), tempGameStatus);
					model.addAttribute("statusChange", "Status for game was changed from: " + oldStatus + " to " + tempGameStatus);
					
					return "change.html";
				}
			}
		}
	}
	
	
	private boolean checkNull(String name) {
		return name == null;
	}

}
