package com.example.videogame_collection.services;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import com.example.videogame_collection.repositories.AccountRepository;

@Component
@Service
@RequestScope
public class LoginService {

	private LoginManager loginManager;
	private AccountRepository accountRepository;
	private PasswordManager passwordManager;
	
	private String username;
	private String password;
	
	public LoginService(LoginManager loginManager, AccountRepository accountRepository, PasswordManager passwordManager) {
		this.loginManager = loginManager;
		this.accountRepository = accountRepository;
		this.passwordManager = passwordManager;
	}
	
	
	public boolean login(String username, String password) {
		
		boolean loginResult = false;

		String userExist = accountRepository.getUsername(username);
		
		if (userExist != null) {
			
			String storedPass = accountRepository.getPassword(username);
			
			try {
				
				if (passwordManager.validatePassword(password, storedPass)) {
					loginResult = true;
					loginManager.setUsername(username);
				}
				
			} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
				e.printStackTrace();
			}
			
		} else {
			
			loginResult = false;
			
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
