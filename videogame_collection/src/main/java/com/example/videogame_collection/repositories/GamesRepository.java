package com.example.videogame_collection.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.videogame_collection.model.Game;

public interface GamesRepository extends CrudRepository<Game, Long>{
	
	

}
