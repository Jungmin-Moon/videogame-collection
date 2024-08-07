package com.example.videogame_collection.services;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import com.example.videogame_collection.repositories.AccountRepository;

public class RegistrationService {
	
	PasswordManager passwordManager;
	AccountRepository accountRepository;
	
	RegistrationService(PasswordManager passwordManager, AccountRepository accountRepository) {
		this.passwordManager = passwordManager;
		this.accountRepository = accountRepository;
	}
	
	/*
	 * we need to add both password that is hashed and salted and username to the account table
	 */
	
	public String generateHashSaltPass(String password) {
		String hashedSaltedPass = "";
		try {
			hashedSaltedPass = passwordManager.genPasswordHash(password);
			return hashedSaltedPass;
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		} 
		
		return hashedSaltedPass;
	}
	
	public boolean passwordSame(String password, String passwordCopy) {
		return password.equals(passwordCopy);
	}
	
	public boolean checkUsernameExist(String username) {
		String userAlreadyExists = accountRepository.getUsername(username);
		
		if (userAlreadyExists == null) 
			return false;
		
		return userAlreadyExists.equals(username);
	}
	
	
	public void createUser(String username, String password) {
		accountRepository.addUser(username, password);
	}
	
}
