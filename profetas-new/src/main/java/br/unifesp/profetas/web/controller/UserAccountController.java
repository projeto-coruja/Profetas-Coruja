package br.unifesp.profetas.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.unifesp.profetas.business.account.ManagementAccount;
import br.unifesp.profetas.business.account.dto.UserDTO;
import br.unifesp.profetas.business.common.MessageDTO;
import br.unifesp.profetas.business.common.OrderType;
import br.unifesp.profetas.business.common.WrapperGrid;
import br.unifesp.profetas.business.local.LocalDTO;
import br.unifesp.profetas.util.ProfetasConstants;

@Controller
public class UserAccountController {
		
	@Autowired ManagementAccount account;
	
	private static final String MODEL		= "user";
	private static final String TILES_DEF	= "account";
	private static final String TILES_DEF_LIST_USERS	= "list_users";
	private static final String TILES_DEF_USER_PROFILE= "user_profile";
	
	@ModelAttribute(MODEL)
	public UserDTO init() {
		return new UserDTO();
	}
	
	@RequestMapping(value = "/account", method = RequestMethod.GET)
    public String showView(SecurityContextHolderAwareRequestWrapper request, ModelMap model,
    		@RequestParam(value = "id", required = true) Long id) {
		if(request.isUserInRole(ProfetasConstants.ROLE_NAME_ADMIN)){
			if(id != null){
				UserDTO uDTO = account.getUserAccoutById(id);
				if(uDTO != null) {
					model.addAttribute(MODEL, uDTO);
				} else {
					return "redirect:/account.html";
				}
			}			
		}
        return TILES_DEF;
    }
	
	@RequestMapping(value = "/account/save", method = RequestMethod.POST, 
			headers = {"content-type=application/json"})
    public @ResponseBody MessageDTO createAccount(SecurityContextHolderAwareRequestWrapper request, @RequestBody UserDTO user){
		if(request.isUserInRole(ProfetasConstants.ROLE_NAME_ADMIN)){
			return account.saveUserWithProfile(user);
		} else {
			return account.saveUser(user);
		}
	}
	
	@RequestMapping(value = "/account-profile", method = RequestMethod.GET)
    public String showViewUserProfile(SecurityContextHolderAwareRequestWrapper request, ModelMap model,
    		@RequestParam(value = "id", required = true) Long id) {
		if(request.isUserInRole(ProfetasConstants.ROLE_NAME_ADMIN)){
			if(id != null){
				UserDTO uDTO = account.getUserAccoutProfileById(id);
				if(uDTO != null) {
					model.addAttribute(MODEL, uDTO);
				} else {
					return "redirect:/account.html";
				}
			}			
		}
        return TILES_DEF_USER_PROFILE;
    }
	@RequestMapping(value = "/account-profile/save", method = RequestMethod.POST, 
			headers = {"content-type=application/json"})
    public @ResponseBody MessageDTO updateUserProfile(SecurityContextHolderAwareRequestWrapper request, @RequestBody UserDTO user){
		return account.updateUserProfile(user);
	}
	@RequestMapping(value = "list-users", method = RequestMethod.GET)
    public String showViewListUsers(HttpServletRequest request) {
        return TILES_DEF_LIST_USERS;
    }
	@RequestMapping(value = "/account/list", method = RequestMethod.GET)
	public @ResponseBody WrapperGrid userList(HttpServletRequest request,
			@RequestParam(value = "orderBy", required = true) String strOrderBy,
			@RequestParam(value = "orderType", required = true) String strOrderType,
			@RequestParam(value = "page", required = true) Integer page, 
			@RequestParam(value = "searchBy", required = false) String search) {
		
		OrderType orderType = OrderType.getOrderType(strOrderType);
		return account.getUserList(strOrderBy, orderType, page, ProfetasConstants.ITEMS_PER_PAGE);
	}
}