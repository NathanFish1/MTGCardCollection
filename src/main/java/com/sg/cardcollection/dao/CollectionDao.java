package com.sg.cardcollection.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.sg.cardcollection.entities.CardCollection;


public interface CollectionDao {

	CardCollection getCollectionById(int id);

    List<CardCollection> getAllCollections();

    CardCollection addCollection(CardCollection cardCollection);

    void updateCollection(CardCollection cardCollection);

    void deleteCollectionById(int id);
}
