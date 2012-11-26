package web.controllers;

import general.UtilityClass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import persistence.dto.User;
import web.beans.RegisterFormBean;
import web.beans.RegisterFormValidator;
import web.services.UserManagementService;

@Controller
public class RegisterController {
	
	@Autowired
	private UserManagementService reg;
	
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public String register(ModelMap model) {
 
		return "register";
 
	}
	
	@RequestMapping(value="/createAccount", method = RequestMethod.POST)
	public String createAccount(@ModelAttribute RegisterFormBean newAccount, BindingResult result, ModelMap model) {
		
		RegisterFormValidator validator = new RegisterFormValidator();
		validator.validate(newAccount, result);
		
		if(result.hasErrors()) {
			for(FieldError e : result.getFieldErrors()) {
				model.addAttribute("error_" + e.getField(), e.getDefaultMessage());
			}
			
			return "register";
		}
		else {
			User newUser = new User(newAccount.getNickname(), newAccount.getUsername(), newAccount.getPassword(), reg.getGroup("users"), true, UtilityClass.getNow());
			reg.addNewUser(newUser, result);
			if(result.hasErrors()) {
				for(FieldError e : result.getFieldErrors()) {
					model.addAttribute("object.persist" + e.getField(), e.getDefaultMessage());
				}
				
				return "register";
			}
		}
		
		model.addAttribute("success", "Cadastro bem-sucedido!");
		return "register";
 
	}

}
