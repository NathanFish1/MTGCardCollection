package com.sg.cardcollection.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {
	
	@GetMapping("home")
    public String displayStudents() {
        return "home";
    }
	
	//display all collections, have an add, view, remove option for this page. Add at the bottom, view and delete with each collection,
	//Click on view to bring up collection html with data based on that card data.
}
