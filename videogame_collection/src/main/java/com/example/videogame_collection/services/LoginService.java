package com.example.videogame_collection.services;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class LoginService {

	private final LoginManager loginManager;
	
	private String username;
	private String password;
	
	public LoginService(LoginManager loginManager) {
		this.loginManager = loginManager;
	}
	
	
	public boolean login(String username, String password) {
		String serviceUsername = this.getUsername();
		String servicePassword = this.getPassword();
		
		boolean loginResult = false;
		
		
		
		return loginResult;
	}
	
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
