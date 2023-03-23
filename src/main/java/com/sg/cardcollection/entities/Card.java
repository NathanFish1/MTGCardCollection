package com.sg.cardcollection.entities;

import java.sql.Array;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Card {

	private String id;
	private String name;
	private String layout;
	private Map<String, String> image_uris;
	private String imageUri;
	private String mana_cost;// entered as {3}{W}{W}
	private String type_line;
	private String[] colors;
	private String[] keywords;
	private String set;
	private String rarity;
	
	
	public Card(String id, String name, String layout, String imageUri,
			String mana_cost, String type_line, String[] colors, String[] keywords, String set,
			String rarity) {
		super();
		this.id = id;
		this.name = name;
		this.layout = layout;
		this.imageUri = imageUri;
		this.mana_cost = mana_cost;
		this.type_line = type_line;
		this.colors = colors;
		this.keywords = keywords;
		this.set = set;
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


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getLayout() {
		return layout;
	}


	public void setLayout(String layout) {
		this.layout = layout;
	}
	
	public Map<String, String> getImage_uris() {
		return image_uris;
	}
	
	
	public void setImage_uris(Map<String, String> image_uris) {
		this.image_uris = image_uris;
	}
	
	public void setImage_uris(String image_uris) {
		Map<String, String> newMap = new HashMap<>();
		String[] pairs = image_uris.split(",");
		for (int i=0;i<pairs.length;i++) {
		    String pair = pairs[i];
		    String[] keyValue = pair.split(":");
		    newMap.put(keyValue[0], keyValue[1]);
		}
	}

	public String getImageUri() {
		return imageUri;
	}


	public void setImageUri(String imageUri) {
		this.imageUri = imageUri;
	}
	
	public void setImageUri(Map<String, String> image_uris) {
		this.imageUri = image_uris.get("normal");
	}


	public String getManaCost() {
		return mana_cost;
	}
	
	public void setManaCost(String mana_cost) {
		this.mana_cost = mana_cost;
	}
	
	public String getCardType() {
		return type_line;
	}


	public void setCardType(String type_line) {
		this.type_line = type_line;
	}


	public String[] getColors() {
		return colors;
	}


	public void setColors(String[] colors) {
		this.colors = colors;
	}
	
	public void setColors(String colors) {
		if(colors != null) {
			this.colors = colors.split(",");
		} else {
			this.colors = null;
		}
		
	}


	public String[] getKeywords() {
		return keywords;
	}


	public void setKeywords(String[] keywords) {
		this.keywords = keywords;
	}
	
	public void setKeywords(String keywords) {
		if(keywords != null) {
			this.keywords = keywords.split(",");
		} else {
			this.keywords = null;
		}
		
		
	}


	public String getCardSet() {
		return set;
	}


	public void setCardSet(String set) {
		this.set = set;
	}


	public String getRarity() {
		return rarity;
	}


	public void setRarity(String rarity) {
		this.rarity = rarity;
	}


	@Override
	public String toString() {
		return "Card [id=" + id + ", name=" + name + ", layout=" + layout + ", image_uris=" + image_uris + ", imageUri="
				+ imageUri + ", mana_cost=" + mana_cost + ", type_line=" + type_line + ", colors="
				+ Arrays.toString(colors) + ", keywords=" + Arrays.toString(keywords) + ", set=" + set + ", rarity="
				+ rarity + "]";
	}
	
	
	
	
}
