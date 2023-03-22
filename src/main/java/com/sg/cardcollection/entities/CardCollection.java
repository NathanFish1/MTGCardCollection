package com.sg.cardcollection.entities;


public class CardCollection {
	
	public enum CollectionType{//Indicate either a full collection of cards, or a collection with a game mode restriction
		BASIC,
		COMMANDER,
		STANDARD;
	}

	private int id;
	private String collectionName;
	private CollectionType collectionType; //Maybe enum
	
	public CardCollection() {
		
	}
	
	public CardCollection(int id, String collectionName, CollectionType collectionType) {
		super();
		this.id = id;
		this.collectionName = collectionName;
		this.collectionType = collectionType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCollectionName() {
		return collectionName;
	}

	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}

	public CollectionType getCollectionType() {
		return collectionType;
	}

	public void setCollectionType(CollectionType collectionType) {
		this.collectionType = collectionType;
	}
	
	
	
}
