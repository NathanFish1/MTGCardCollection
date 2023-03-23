package com.sg.cardcollection.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sg.cardcollection.entities.Card;
import com.sg.cardcollection.entities.CardCollection;
import com.sg.cardcollection.entities.CardCollection.CollectionType;
import com.sg.cardcollection.service.ProjectService;


@Controller
public class HomeController {
	
	@Autowired
	ProjectService service;
	
	@GetMapping("home")
    public String displayHome(Model model) {
		model.addAttribute("cardcollections", service.displayAllCardCollections());
        return "home";
    }
	
	@GetMapping("view")
	public String viewCollection(HttpServletRequest request, Model model) {
		int id = Integer.parseInt(request.getParameter("id"));
		CardCollection cardCollection = service.getCollectionById(id);
		model.addAttribute("collectionName", service.getCollectionById(id).getCollectionName());
		model.addAttribute("allCards", service.getAllCardsFromCollectionId(id));
		return "view";
	}
	
	@PostMapping("addCollection")
	public String addCollection(HttpServletRequest request) {
		String name = request.getParameter("collectionName");
		CollectionType type = CollectionType.valueOf(request.getParameter("collectionType"));
		CardCollection cc = new CardCollection();
		cc.setCollectionName(name);
		cc.setCollectionType(type);
		service.addCollection(cc);
		return "redirect:/home";
	}
	
	@GetMapping("deleteCollection")
	public String deleteCollection(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		service.deleteCollectionById(id);
		return "redirect:/home";
	}
	
	@GetMapping("/search")
	public String searchCards(@RequestParam("cardName") String cardName, Model model) {
		service.addToCurrentSearchItems(cardName);
		List<CardCollection> listCollections = service.getAllCardCollections();
	    List<Card> cards = service.displayCurrentSearchItems();
	    model.addAttribute("cards", cards);
	    model.addAttribute("listOfCollections", listCollections);
	    return "searchCards";
	}
	
	@PostMapping("searchCards")
	public String searchCards(HttpServletRequest request, HttpServletResponse response) {
		String cardId = request.getParameter("cardId");
		String collectionName = request.getParameter("collectionName");
		int colId = service.getCollectionIdByName(collectionName);
		Card card = service.getCardFromAPIById(cardId);
		card.setImageUri(card.getImage_uris());
		if(collectionName != "") {
			service.addCard(card);
			int collectionId = service.getCollectionIdByName(collectionName);
			service.addCardToCollection(card, collectionId);
			return "redirect:/home";
		} else {
			String previousPageUrl = request.getHeader("referer");
	
		    // Redirect the user to the previous page URL
		    try {
		        response.sendRedirect(previousPageUrl);
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}
		

	    // Return null to indicate that the response has been handled
	    return null;
		
		
		
	}
	//display all collections, have an add, view, remove option for this page. Add at the bottom, view and delete with each collection,
	//Click on view to bring up collection html with data based on that card data.
}
