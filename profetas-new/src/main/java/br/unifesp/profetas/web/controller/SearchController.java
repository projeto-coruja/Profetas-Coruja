package br.unifesp.profetas.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SearchController {
	
	private static final String MODEL		= "search";
	private static final String TILES_DEF	= "search";
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
    public String showView() {
        return TILES_DEF;
    }

}