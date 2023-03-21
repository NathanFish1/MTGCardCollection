package com.sg.cardcollection.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CardCollectionDaoTest {

	@Autowired
	CollectionDao collectionDao;
	
	@Autowired
	CardDao cardDao;
	
	public CardCollectionDaoTest() {
		
	}
	
	
}
