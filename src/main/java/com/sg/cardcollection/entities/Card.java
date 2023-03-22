package com.sg.cardcollection.entities;

import java.sql.Array;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Card {

	private String id;
	private String cardName;
	private LocalDate releaseDate;
	private String layout;
	private String imageUri;
	private List<String> manaCost;// entered as {3}{W}{W}
	private String cardType;
	private List<String> colors;
	private List<String> keywords;
	private String cardSet;
	private String rarity;
	
	
	public Card(String id, String cardName, LocalDate releaseDate, String layout, String imageUri,
			List<String> manaCost, String cardType, List<String> colors, List<String> keywords, String cardSet,
			String rarity) {
		super();
		this.id = id;
		this.cardName = cardName;
		this.releaseDate = releaseDate;
		this.layout = layout;
		this.imageUri = imageUri;
		this.manaCost = manaCost;
		this.cardType = cardType;
		this.colors = colors;
		this.keywords = keywords;
		this.cardSet = cardSet;
		this.rarity = rarity;
	}


	public Card() {
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getCardName() {
		return cardName;
	}


	public void setCardName(String cardName) {
		this.cardName = cardName;
	}


	public LocalDate getReleaseDate() {
		return releaseDate;
	}


	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}


	public String getLayout() {
		return layout;
	}


	public void setLayout(String layout) {
		this.layout = layout;
	}


	public String getImageUri() {
		return imageUri;
	}


	public void setImageUri(String imageUri) {
		this.imageUri = imageUri;
	}


	public List<String> getManaCost() {
		return manaCost;
	}
	
	public String getManaCostAsString() {
		String formattedMana = "";
		for(String mana : this.manaCost) {
			formattedMana = formattedMana + "{"+ mana + "}";
		}
		return formattedMana;
	}


	public void setManaCost(List<String> manaCost) {
		this.manaCost = manaCost;
	}
	
	public void setManaCost(String manaCost) {
		if(manaCost != null) {
			String[] allMana = manaCost.split("\\{|\\}");
			this.manaCost = new ArrayList<String>();
	
			for (String mana : allMana) {
			    if (!mana.isEmpty()) {
			        this.manaCost.add(mana);
			    }
			}
		} else {
			this.manaCost = null;
		}
	}


	public String getCardType() {
		return cardType;
	}


	public void setCardType(String cardType) {
		this.cardType = cardType;
	}


	public List<String> getColors() {
		return colors;
	}


	public void setColors(List<String> colors) {
		this.colors = colors;
	}
	
	public void setColors(String colors) {
		if(colors != null) {
			String[] allColors = colors.split(",");
			this.colors = new ArrayList<String>();
	
			for (String color : allColors) {
			    this.colors.add(color);
			}
		} else {
			this.colors = null;
		}
	}


	public List<String> getKeywords() {
		return keywords;
	}


	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}
	
	public void setKeywords(String keywords) {
		if(keywords != null) {
			String[] allKeywords = keywords.split(",");
			this.keywords = new ArrayList<String>();
	
			for (String keyword : allKeywords) {
			    this.keywords.add(keyword);
			}
		} else {
			this.keywords = null;
		}
		
	}


	public String getCardSet() {
		return cardSet;
	}


	public void setCardSet(String cardSet) {
		this.cardSet = cardSet;
	}


	public String getRarity() {
		return rarity;
	}


	public void setRarity(String rarity) {
		this.rarity = rarity;
	}
	
	
	
	
}
