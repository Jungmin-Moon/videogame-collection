package com.example.videogame_collection.repositories;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Component;

import com.example.videogame_collection.model.Game;

//@Component
public interface GamesRepository extends ListCrudRepository<Game, Integer>{
	
	@Modifying
	@Query("INSERT INTO games (id, game_Name, game_System, game_Status, added_By_User) "
			+ "VALUES (DEFAULT, :name, :system, :status, :username)")
	void addGame(String name, String system, String status, String username);

	
	@Query("SELECT * FROM games where added_By_User = :username") 
	List<Game> findGamesByUser(String username);
	
	@Query("SELECT id FROM games where game_Name = :name")
	int getId(String name);
	
	@Query("SELECT * FROM games where id = :id")
	Game getGame(int id);
	
	//unneeded
	/*
	@Modifying
	@Query("UPDATE games SET game_Name = :name, game_System = :system, game_Status = :status WHERE id = :id")
	void updateAll(int id, String name, String system, String status); */
	
	@Modifying
	@Query("UPDATE games SET game_Name = :name WHERE id = :id")
	void updateName(int id, String name);
	
	@Modifying
	@Query("UPDATE games SET game_System = :system WHERE id = :id")
	void updateSystem(int id, String system);
	
	@Modifying
	@Query("UPDATE games SET game_Status = :status WHERE id = :id")
	void updateStatus(int id, String status);
}
