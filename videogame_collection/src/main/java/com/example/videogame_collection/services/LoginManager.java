package com.example.videogame_collection.services;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
@SessionScope
public class LoginManager {

	//used to set the session for the user
	private String username;
	
	public String getUserName() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
}
