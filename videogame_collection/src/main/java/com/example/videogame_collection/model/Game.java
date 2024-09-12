package com.example.videogame_collection.model;


import org.springframework.data.annotation.Id;

public class Game {
	@Id
	private int id;
	
	private String gameName;

	private String gameSystem;
	private String gameStatus;
	private String addedByUser;
	
	public int getid() {
		return id;
	}
	public void setid(int id) {
		this.id = id;
	}
	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	public String getGameSystem() {
		return gameSystem;
	}
	public void setGameSystem(String gameSystem) {
		this.gameSystem = gameSystem;
	}
	public String getGameStatus() {
		return gameStatus;
	}
	public void setGameStatus(String gameStatus) {
		this.gameStatus = gameStatus;
	}
	
	public String getAddedByUser() {
		return addedByUser;
	}
	
	public void setAddedByUser(String addedByUser) {
		this.addedByUser = addedByUser;
	}
	
	
	
}
