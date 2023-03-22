package com.sg.cardcollection.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sg.cardcollection.entities.Card;

@Repository
public class CardDaoDB implements CardDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	@Override
	public Card getCardById(String id) {
		try {
			final String GET_CARD_BY_ID = "SELECT * FROM card WHERE id = ?";
			return jdbcTemplate.queryForObject(GET_CARD_BY_ID, new CardMapper(), id);
		} catch(DataAccessException ex) {
			return null;
		}
	}

	@Override
	public List<Card> getAllCards() {
		final String GET_ALL_CARDS = "SELECT * FROM card";
		return jdbcTemplate.query(GET_ALL_CARDS, new CardMapper());
	}

	@Override
	public List<Card> getAllCardsFromCollection(int id) {
		final String GET_ALL_CARDS_IN_COLLECTION = "SELECT c.id, c.cardName, c.layout, c.image_uri, c.mana_cost, c.type_line, c.colors, c.keywords, c.cardSet, c.rarity "
				+ "FROM card c "
				+ "JOIN collection_card cc "
				+ "ON cc.cardId = c.id "
				+ "WHERE collectionId = ?";
		return jdbcTemplate.query(GET_ALL_CARDS_IN_COLLECTION, new CardMapper(), id);
	}

	@Override
	public Card addCard(Card card) {
		final String INSERT_CARD = "INSERT INTO card "
				+ "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(INSERT_CARD, card.getId(),
				card.getName(),
				card.getLayout(),
				card.getImageUri(),
				card.getManaCost(),
				card.getCardType(),
				String.join(",", card.getColors()),
				String.join(",", card.getKeywords()),
				card.getCardSet(),
				card.getRarity());
		return card;
	}

	@Override
	public Card addCardToCollection(Card card, int collectionId) {
		final String INSERT_CARD_IN_COLLECTION = "INSERT INTO collection_card"
				+ "VALUES(?,?)";
		jdbcTemplate.update(INSERT_CARD_IN_COLLECTION,
				collectionId,
				card.getId());
		return card;
	}

	@Override
	public void updateCard(Card card) {
		// TODO Auto-generated method stub

	}

	//Rarely use as all cards stored in collections so basically only use one below
	@Override
	public void deleteCard(String id) {
		final String DELETE_CARD = "DELETE FROM card WHERE id = ?";
		jdbcTemplate.update(DELETE_CARD, id);

	}

	@Override
	public void deleteCardFromCollection(String cardId, int collectionId) {
		//delete card from collection
		final String DELETE_CARD_FROM_COLLECTION = "DELETE FROM collection_card WHERE collectionId = ? and cardId = ?";
		jdbcTemplate.update(DELETE_CARD_FROM_COLLECTION,
				collectionId,
				cardId);
		//delete card data from card table if card is not used in any collections
		final String DELETE_CARD_THAT_DOESNT_BELONG = "DELETE FROM card "
				+ "WHERE id NOT IN (SELECT cardId FROM collection_card)";
		jdbcTemplate.update(DELETE_CARD_THAT_DOESNT_BELONG);
	}
	
	public static final class CardMapper implements RowMapper<Card> {

		@Override
		public Card mapRow(ResultSet rs, int rowNum) throws SQLException {
			Card card = new Card();
			card.setId(rs.getString("id"));
			card.setName(rs.getString("cardName"));
			card.setLayout(rs.getString("layout"));
			card.setImageUri(rs.getString("image_uri"));
			card.setManaCost(rs.getString("mana_cost"));
			card.setCardType(rs.getString("type_line"));
			card.setColors(rs.getString("colors"));
			card.setKeywords(rs.getString("keywords"));
			card.setCardSet(rs.getString("cardSet"));
			card.setRarity(rs.getString("rarity"));
			return card;
		}
		
	}

}
