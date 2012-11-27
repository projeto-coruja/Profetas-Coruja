package web.controllers;

import java.util.HashMap;

import general.UtilityClass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	
	@Autowired
	private PasswordEncoder encoder;
	
	@ModelAttribute("newAccount")
	public RegisterFormBean getRegisterFormBean() {
		return new RegisterFormBean();
	}
	
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public String register(ModelMap model) {
		return "register";
	}
	
	@RequestMapping(value="/createAccount", method = RequestMethod.POST)
	public String createAccount(@ModelAttribute RegisterFormBean newAccount, BindingResult result, ModelMap model) {
		
		HashMap<String, String> form_errors;
		RegisterFormValidator validator = new RegisterFormValidator();
		validator.validate(newAccount, result);
		
		if(result.hasErrors()) {
			model.addAttribute("failure", "Cadastro mal-sucedido!");
			form_errors = new HashMap<String, String>();
			for(FieldError e : result.getFieldErrors()) {
				form_errors.put("error_" + e.getField(), e.getDefaultMessage());
			}
			model.addAttribute("form_errors", form_errors);
			
			return "register";
		}
	
		User newUser = new User(newAccount.getNickname(), newAccount.getUsername(), encoder.encode(newAccount.getPassword()), reg.getGroup("users"), true, UtilityClass.getNow());
		String save_result = reg.addNewUser(newUser);
		if(!save_result.isEmpty()) {
			form_errors = new HashMap<String, String>();
			model.addAttribute("failure", "Cadastro mal-sucedido!");
			form_errors.put("error_persistence", save_result);
			model.addAttribute("form_errors", form_errors);
			return "register";
		}
	
		model.addAttribute("success", "Cadastro bem-sucedido!");
		return "register";
 
	}

}
