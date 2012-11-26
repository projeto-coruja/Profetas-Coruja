package web.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import web.beans.RegisterFormBean;
import web.beans.RegisterFormValidator;

@Controller
public class RegisterController {
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new RegisterFormValidator());
    }
	
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public String register(ModelMap model) {
 
		return "register";
 
	}
	
	@RequestMapping(value="/createAccount", method = RequestMethod.POST)
	public String createAccount(@Valid @ModelAttribute RegisterFormBean newUser, BindingResult result, ModelMap model) {
		
		if(result.hasErrors()) {
			for(FieldError e : result.getFieldErrors()) {
				model.addAttribute(e.getField() + "_error", e.getDefaultMessage());
			}
		}
		
		return "login";
 
	}

}
