package br.unifesp.profetas.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.unifesp.profetas.business.account.ManagementAccount;
import br.unifesp.profetas.business.account.UserDTO;
import br.unifesp.profetas.business.common.MessageDTO;
import br.unifesp.profetas.business.common.OrderType;
import br.unifesp.profetas.business.common.WrapperGrid;
import br.unifesp.profetas.util.ProfetasConstants;

@Controller
public class UserAccountController {
		
	@Autowired ManagementAccount account;
	
	private static final String MODEL		= "user";
	private static final String TILES_DEF	= "account";
	private static final String TILES_DEF_USER_PROFILE= "account_profile";
	private static final String TILES_DEF_REC_PASS_1	= "forgot_pass";
	private static final String TILES_DEF_REC_PASS_2	= "update_pass";
	
	@ModelAttribute(MODEL)
	public UserDTO init() {
		return new UserDTO();
	}
	
	/*** account ***/
	@RequestMapping(value = "/account", method = RequestMethod.GET)
    public String showView() {
        return TILES_DEF;
    }
	
	@RequestMapping(value = "/account/save", method = RequestMethod.POST, 
			headers = {"content-type=application/json"})
    public @ResponseBody MessageDTO createAccount(@RequestBody UserDTO user){
		return account.saveUser(user);
	}

	/*** account-profile ***/
	@RequestMapping(value = "/account-profile", method = RequestMethod.GET)
    public String showViewUserProfile(SecurityContextHolderAwareRequestWrapper request, ModelMap model,
    		@RequestParam(value = "id", required = false) Long id) {
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
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/account-profile/list", method = RequestMethod.GET)
	public @ResponseBody WrapperGrid userList(HttpServletRequest request,
			@RequestParam(value = "orderBy", required = true) String strOrderBy,
			@RequestParam(value = "orderType", required = true) String strOrderType,
			@RequestParam(value = "page", required = true) Integer page, 
			@RequestParam(value = "searchBy", required = false) String search) {
		
		OrderType orderType = OrderType.getOrderType(strOrderType);
		return account.getUserList(strOrderBy, orderType, page, ProfetasConstants.ITEMS_PER_PAGE);
	}
	
	/* Recovery pass */
	@RequestMapping(value = "/forgot-pass", method = RequestMethod.GET)
    public String showRecoveryPassStepOne() {
        return TILES_DEF_REC_PASS_1;
    }
	@RequestMapping(value = "/forgot-pass", method = RequestMethod.POST, 
			headers = {"content-type=application/json"})
    public @ResponseBody MessageDTO recoveryPassStepOne(@RequestBody UserDTO user){
		return account.recoveryPassStepOne(user.getEmail());
	}
	
	@RequestMapping(value = "/update-pass", method = RequestMethod.GET)
    public String showRecoveryPassStepTwo(Model model, 
    		@RequestParam(value = "user", required = true) String email, 
    		@RequestParam(value = "code", required = true) String code) {
		//http://localhost:8080/profetas/update-pass.html?user=admin@profetas.com&code=ABC
		UserDTO userDTO = new UserDTO();
		userDTO.setEmail(email);
		userDTO.setActivationCode(code);
		model.addAttribute(MODEL, userDTO);
		
        return TILES_DEF_REC_PASS_2;
    }
	@RequestMapping(value = "/update-pass", method = RequestMethod.POST, 
			headers = {"content-type=application/json"})
    public @ResponseBody MessageDTO recoveryPassStepTwo(@RequestBody UserDTO user){
		return account.recoveryPassStepTwo(user);
	}
}