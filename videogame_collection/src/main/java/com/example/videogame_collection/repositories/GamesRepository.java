package com.example.videogame_collection.repositories;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

import com.example.videogame_collection.model.Game;

public interface GamesRepository extends ListCrudRepository<Game, Long>{
	
	@Modifying
	@Query("INSERT INTO games (id, game_Name, game_System, game_Status, added_By_User) "
			+ "VALUES (DEFAULT, :name, :system, :status, :username)")
	void addGame(String name, String system, String status, String username);

	
	@Query("SELECT * FROM games where added_By_User = :username") 
	List<Game> findGamesByUser(String username);
}
