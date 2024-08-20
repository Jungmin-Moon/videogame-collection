package com.example.videogame_collection.dto;

public class GameDTO {
	private long id;
	private String gameName;
	private String gameSystem;
	private String gameStatus;
	private String addedByUser;
	
	
	public GameDTO(long id, String gameName, String gameSystem, String gameStatus, String addedByUser) {
		this.id = id;
		this.gameName = gameName;
		this.gameSystem = gameSystem;
		this.gameStatus = gameStatus;
		this.addedByUser = addedByUser;
	}

	public long getid() {
		return id;
	}

	public void setid(long id) {
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
