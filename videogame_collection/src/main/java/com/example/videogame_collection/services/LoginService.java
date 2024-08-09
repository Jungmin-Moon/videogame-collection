package com.example.videogame_collection.services;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import com.example.videogame_collection.repositories.AccountRepository;

@Component
@RequestScope
public class LoginService {

	private final LoginManager loginManager;
	private final AccountRepository accountRepository;
	private final PasswordManager passwordManager;
	
	private String username;
	private String password;
	
	public LoginService(LoginManager loginManager, AccountRepository accountRepository, PasswordManager passwordManager) {
		this.loginManager = loginManager;
		this.accountRepository = accountRepository;
		this.passwordManager = passwordManager;
	}
	
	
	public boolean login(String username, String password) {
		
		boolean loginResult = false;
		
		String storedPass = accountRepository.getPassword(username);
		
		try {
			if (passwordManager.validatePassword(password, storedPass)) {
				String userExist = accountRepository.getUsername(username);
				
				if (userExist != null) {
					loginResult = true;
					loginManager.setUsername(username);
				}
			}
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
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
