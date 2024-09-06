package com.example.videogame_collection.services;

import org.springframework.stereotype.Service;

@Service
public class DataComparisonService {

	
	public boolean compareName(String originalName, String newName) {
		return originalName.equalsIgnoreCase(newName);
	}
	
	public boolean compareSystem(String originalSystem, String newSystem) {
		return originalSystem.equalsIgnoreCase(newSystem);
	}
	
	public boolean compareStatus(String originalStatus, String newStatus) {
		return originalStatus.equalsIgnoreCase(newStatus);
	}
}
