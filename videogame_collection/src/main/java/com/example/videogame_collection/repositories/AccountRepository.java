package com.example.videogame_collection.repositories;

import org.springframework.data.jdbc.repository.query.Query;

public interface AccountRepository {

	@Query("SELECT username FROM account where username = :username")
	String getUserId(String username);
	
}
