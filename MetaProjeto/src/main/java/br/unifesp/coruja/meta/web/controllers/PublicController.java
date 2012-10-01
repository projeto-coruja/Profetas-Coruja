package br.unifesp.coruja.meta.web.controllers;

import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
 
@Controller
public class PublicController {
 
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String printWelcome(ModelMap model, Principal principal ) {
 
		return "public";
 
	}
 
}