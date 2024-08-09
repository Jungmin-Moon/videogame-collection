package com.example.videogame_collection.repositories;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.videogame_collection.model.Account;

public interface AccountRepository extends CrudRepository<Account, Long>{

	@Query("SELECT username FROM users where username = :username")
	String getUsername(String username);
	
	@Query("SELECT password FROM users where username = :username")
	String getPassword(String username);
	
	@Modifying
	@Query("INSERT INTO users (userID, username, password) VALUES (DEFAULT, :username, :password)")
	void addUser(String username, String password);
	
	
}
