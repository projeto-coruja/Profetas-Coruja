package br.unifesp.profetas.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.unifesp.profetas.business.account.ManagementAccount;
import br.unifesp.profetas.business.account.dto.UserDTO;
import br.unifesp.profetas.business.common.MessageDTO;

@Controller
public class RegisterUserController {
		
	@Autowired ManagementAccount account;
	
	private static final String USER		= "user";
	private static final String TILES_DEF	= "register_user";
	
	@ModelAttribute(USER)
	public UserDTO init() {
		return new UserDTO();
	}
	
	@RequestMapping(value = "/register-user", method = RequestMethod.GET)
    public String showView(HttpServletRequest request) {
        return TILES_DEF;
    }
	
	@RequestMapping(value = "/register-user-json", method = RequestMethod.POST, 
			headers = {"content-type=application/json"})
    public @ResponseBody MessageDTO createAccount(HttpServletRequest request, @RequestBody UserDTO user){
		return account.saveUser(user);
	}
}