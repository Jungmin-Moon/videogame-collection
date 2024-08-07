package com.example.videogame_collection.repositories;

import org.springframework.data.jdbc.repository.query.Query;

public interface AccountRepository {

	@Query("SELECT username FROM account where username = :username")
	String getUsername(String username);
	
	@Query("SELECT password FROM account where username = :username")
	String getPassword(String username);
	
	@Query("INSERT INTO users (userId, username, password) VALUES (DEFAULT, :username, :password")
	void addUser(String username, String password);
	
	
}
