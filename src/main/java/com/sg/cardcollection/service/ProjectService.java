package com.sg.cardcollection.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sg.cardcollection.dao.CardDao;
import com.sg.cardcollection.dao.CardDaoDB;
import com.sg.cardcollection.dao.CollectionDao;
import com.sg.cardcollection.dao.CollectionDaoDB;
import com.sg.cardcollection.entities.Card;
import com.sg.cardcollection.entities.CardCollection;

@Service
public class ProjectService {
	
	@Autowired
	CardDao cardDaoDB;
	@Autowired
	CollectionDao collectionDaoDB;
	
	public ProjectService() {		
	}
	
	@Autowired
	public ProjectService(CardDaoDB cardDB, CollectionDaoDB collectionDB) {
		this.cardDaoDB = cardDB;
		this.collectionDaoDB = collectionDB;
	}
	
	public List<CardCollection> displayAllCardCollections() {
		List<CardCollection> allCardCollections = collectionDaoDB.getAllCollections();
		return allCardCollections;
	}

	public CardCollection getCollectionById(int id) {
		return collectionDaoDB.getCollectionById(id);
	}
	 
	public List<Card> getAllCardsFromCollectionId(int id) {
		return cardDaoDB.getAllCardsFromCollection(id);
	}

	public void addCollection(CardCollection cc) {
		collectionDaoDB.addCollection(cc);
		
	}
	
	

}
