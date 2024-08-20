package com.example.videogame_collection.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.videogame_collection.dto.GameDTO;
import com.example.videogame_collection.model.Game;
import com.example.videogame_collection.repositories.GamesRepository;

@Service
public class GameService {

		@Autowired
		private GamesRepository gamesRepository;
		
		public List<GameDTO> getGames(String username) {
			List<Game> temp = gamesRepository.findGamesByUser(username);
			List<GameDTO> games = new ArrayList<>();
			
			for (int i = 0; i < temp.size(); i++) {
				games.add(convertToDTO(temp.get(i)));
			}
			
			return games;
		}
		
		public void addGame(String name, String System, String status, String username) {
			gamesRepository.addGame(name, System, status, username);
		}
		
		
		public GameDTO convertToDTO(Game g) {
			return new GameDTO(g.getid(), g.getGameName(), g.getGameSystem(), g.getGameStatus(), g.getAddedByUser());
		}
}
