package com.sg.cardcollection.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.sg.cardcollection.entities.Card;


public interface CardDao {
	Card getCardById(String id);

    List<Card> getAllCards();
    
    List<Card> getAllCardsFromCollection(int id);

    Card addCard(Card card);
    
    Card addCardToCollection(Card card, int id);

    void updateCard(Card card);

    void deleteCard(String id);
    
    void deleteCardFromCollection(String cardId, int CollectionId);

	void addToCurrentSearchItems(String searchedCard);

	List<Card> displayCurrentSearchItems();

	Card getCardFromAPIById(String cardId);
}
