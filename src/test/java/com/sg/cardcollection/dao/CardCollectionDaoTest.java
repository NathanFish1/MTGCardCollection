package com.sg.cardcollection.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sg.cardcollection.entities.CardCollection;
import com.sg.cardcollection.entities.CardCollection.CollectionType;

@SpringBootTest
public class CardCollectionDaoTest {

	@Autowired
	CollectionDao collectionDao;
	
	@Autowired
	CardDao cardDao;
	
	public CardCollectionDaoTest() {
		
	}
	
	//delete all collections
	@BeforeEach 
	public void setUp() {
		List<CardCollection> collectionList = collectionDao.getAllCollections();
		for(CardCollection item : collectionList) {
			collectionDao.deleteCollectionById(item.getId());
		}
		
	}
	
	@Test
	public void testCollectionAdded() {
		CardCollection col = new CardCollection();
		col.setCollectionName("newest");
		col.setCollectionType(CollectionType.BASIC);
		col = collectionDao.addCollection(col);
		
		List<CardCollection> list = collectionDao.getAllCollections();
		assertEquals(1, list.size());
		assertTrue(list.contains(col));
		
	}
	
	@Test
	public void testCollectionAddedandreturn() {
		CardCollection col = new CardCollection();
		col.setCollectionName("newest");
		col.setCollectionType(CollectionType.BASIC);
		col = collectionDao.addCollection(col);
		
		CardCollection fromDao = collectionDao.getCollectionById(1);
		
		assertEquals(col, fromDao);
		
	}
	
	
	
}
