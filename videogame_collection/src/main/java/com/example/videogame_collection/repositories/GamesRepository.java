package com.example.videogame_collection.repositories;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.videogame_collection.model.Game;

public interface GamesRepository extends CrudRepository<Game, Long>{
	
	@Modifying
	@Query("INSERT INTO games (id, gameName, gameSystem, gameStatus, addedByUser) "
			+ "VALUES (DEFAULT, :gameName, :gameSystem, :gameStatus, :username)")
	void addGame(String gameName, String gameSystem, String gameStatus, String username);

}
