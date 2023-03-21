package com.sg.cardcollection.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import com.sg.cardcollection.entities.CardCollection;
import com.sg.cardcollection.entities.CardCollection.CollectionType;

public class CollectionDaoDB implements CollectionDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public CardCollection getCollectionById(int id) {
		try {
			final String GET_COLLECTION_BY_ID = "SELECT * FROM collection WHERE id = ?";
			return jdbcTemplate.queryForObject(GET_COLLECTION_BY_ID, new CollectionMapper(), id);
		} catch(DataAccessException ex) {
			return null;
		}
	}

	@Override
	public List<CardCollection> getAllCollections() {
		final String GET_ALL_COLLECTIONS = "SELECT * FROM collection";
		return jdbcTemplate.query(GET_ALL_COLLECTIONS, new CollectionMapper());
	}

	@Override
	@Transactional
	public CardCollection addCollection(CardCollection cardCollection) {
		final String INSERT_COLLECTION = "INSERT INTO collection(collectionName, collectionType) VALUES(?,?)";
		jdbcTemplate.update(INSERT_COLLECTION,
				cardCollection.getCollectionName(),
				cardCollection.getCollectionType());
		
		int newId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
		cardCollection.setId(newId);
		return cardCollection;
	}

	@Override
	public void updateCollection(CardCollection cardCollection) {
		final String UPDATE_COLLECTION = "UPDATE collection SET collectionName = ?, collectionType = ? WHERE id = ?";
		jdbcTemplate.update(UPDATE_COLLECTION,
				cardCollection.getCollectionName(),
				cardCollection.getCollectionType(),
				cardCollection.getId());

	}

	@Override
	@Transactional
	public void deleteCollectionById(int id) {
		//delete collection, on cascade delete takes care of other collections.
		final String DELETE_COLLECTION = "DELETE FROM collection WHERE id = ?";
		jdbcTemplate.update(DELETE_COLLECTION, id);
		//Left with some cards that do not belong to any collection. Delete these cards.
		final String DELETE_LEFT_OVER_CARD = "DELETE FROM card WHERE id NOT IN (SELECT cardId FROM collection_card)";
		jdbcTemplate.update(DELETE_LEFT_OVER_CARD);
	}
	
	public static final class CollectionMapper implements RowMapper<CardCollection> {

		@Override
		public CardCollection mapRow(ResultSet rs, int rowNum) throws SQLException {
			CardCollection cardCollection = new CardCollection();
			cardCollection.setId(rs.getInt("id"));
			cardCollection.setCollectionName(rs.getString("collectionName"));
			cardCollection.setCollectionType(CollectionType.valueOf(rs.getString("collectionType")));
			return cardCollection;
			
		}
		
	}

}
