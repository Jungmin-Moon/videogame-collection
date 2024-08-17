package com.example.videogame_collection.repositories;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.videogame_collection.model.Game;

public interface GamesRepository extends CrudRepository<Game, Long>{
	
	@Query("create table :username (gameID int NOT NULL PRIMARY KEY, gameName varchar(255) NOT NULL, "
			+ "system varchar(255) NOT NULL, status varchar(255))")
	void createUserGameList(String username);

}
