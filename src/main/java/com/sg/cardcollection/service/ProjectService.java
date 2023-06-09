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

	public void deleteCollectionById(int id) {
		collectionDaoDB.deleteCollectionById(id);
		
	}

	public void addToCurrentSearchItems(String searchedCard) {
		cardDaoDB.addToCurrentSearchItems(searchedCard);
		
	}

	public List<Card> displayCurrentSearchItems() {
		return cardDaoDB.displayCurrentSearchItems();
	}

	public List<CardCollection> getAllCardCollections() {
		return collectionDaoDB.getAllCollections();
	}

	public void addCard(Card card) {
		cardDaoDB.addCard(card);
		
	}

	public int getCollectionIdByName(String collectionName) {
		List<CardCollection> list = collectionDaoDB.getAllCollections();
		for (CardCollection col : list) {
			if(col.getCollectionName().equals(collectionName)) {
				return col.getId();
			}
		}
		return 0;
	}

	public void addCardToCollection(Card card, int collectionId) {
		cardDaoDB.addCardToCollection(card, collectionId);
		
	}

	public Card getCardFromAPIById(String cardId) {
		return cardDaoDB.getCardFromAPIById(cardId);
	}
	
	

}
