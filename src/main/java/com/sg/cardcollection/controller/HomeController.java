package com.sg.cardcollection.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
	    List<Card> cards = service.displayCurrentSearchItems();
	    model.addAttribute("cards", cards);
	    return "searchCards";
	}
	
	//display all collections, have an add, view, remove option for this page. Add at the bottom, view and delete with each collection,
	//Click on view to bring up collection html with data based on that card data.
}
