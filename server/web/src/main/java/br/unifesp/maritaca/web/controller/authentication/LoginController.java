package br.unifesp.maritaca.web.controller.authentication;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.expressme.openid.Association;
import org.expressme.openid.Authentication;
import org.expressme.openid.Endpoint;
import org.expressme.openid.OpenIdManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.unifesp.maritaca.business.UserDTO;
import br.unifesp.maritaca.business.account.AccountDTO;
import br.unifesp.maritaca.business.authentication.Login;
import br.unifesp.maritaca.web.controller.AbstractController;

/**
 * 
 * @author Tiago Barabasz
 * @author Jimmy Valverde S&aacute;nchez
 *
 */
@Controller
public class LoginController extends AbstractController {

	private static final long serialVersionUID = 1L;
	private static final String USER = "user";
	
	private OpenIdManager manager;
	private static final String ATTR_OP = "op";
	private static final String ATTR_MAC     	 = "openid_mac";
    private static final String ATTR_ALIAS   	 = "openid_alias";
	private static final String ATTR_ENDPOINT	 = "openid.op_endpoint";
	private static final String URL			 = "http://localhost:8080/maritaca";
	
	@Autowired Login login;

	@ModelAttribute(USER)
	public AccountDTO init() {
		return new AccountDTO();
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLogin() {
        return "login";
    }
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
    public String doLogin(@Valid @ModelAttribute(USER) AccountDTO user, BindingResult result, Errors errors, 
    		Model model, HttpServletRequest request, HttpServletResponse response) {
		
		//TODO: result.hasErrors() ...
		AccountDTO accountDTO = login.doLogin(user);
		if(accountDTO != null) {
			UserDTO maritacaUser = new UserDTO(user.getEmail());
			setCurrentUser(request, maritacaUser);
			return "redirect:/forms.html";
		}
		else {
			errors.rejectValue("password", "login_failed");
			return "login";
		}
	}
	
	/* OpenId */
	@RequestMapping(value = "/login-openid", method = RequestMethod.GET)
	public String showLoginOpenId(@RequestParam(ATTR_OP) String op, 
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		if(op != null) {
			System.out.println("op");
			manager = new OpenIdManager();
			manager.setRealm(URL);
			manager.setReturnTo(URL+"/home-openid.html");
			if(("Google").equals(op) || "Yahoo".equals(op)) {
				Endpoint endpoint = manager.lookupEndpoint(op);
				Association association = manager.lookupAssociation(endpoint);
				request.getSession().setAttribute(ATTR_MAC, association.getRawMacKey());
				request.getSession().setAttribute(ATTR_ALIAS, endpoint.getAlias());
				String url = manager.getAuthenticationUrl(endpoint, association);
				response.sendRedirect(url);
			}
			else {
				//TODO: Log? anything?
				System.out.println("return");
			}
		}
        return "login";
	}
	
	@RequestMapping(value="/home-openid", method = RequestMethod.GET)
	public String showHomeOpenId(/*@RequestParam("openid.response_nonce") String nonce, */Model model, 
			HttpServletRequest request, HttpServletResponse response) {
		
		String endPoint = request.getParameter(ATTR_ENDPOINT);
		if(endPoint != null) {
			byte[] mac_key = (byte[]) request.getSession().getAttribute(ATTR_MAC);
			String alias = (String) request.getSession().getAttribute(ATTR_ALIAS);
			Authentication authentication = manager.getAuthentication(request, mac_key, alias);
			if(authentication.getFirstname() != null && authentication.getEmail() != null) {
				//Save user or update
				//if save is ok -> home
				//else -> login
				
				AccountDTO accountDTO = new AccountDTO();
				accountDTO.setFirstName(authentication.getFirstname());
				accountDTO.setLastName(authentication.getLastname());
				accountDTO.setEmail(authentication.getEmail());
				
				accountDTO = login.doLoginOpenId(accountDTO);
				if(accountDTO.getKey() != null) {
					UserDTO maritacaUser = new UserDTO(accountDTO.getEmail());
					setCurrentUser(request, maritacaUser);
					return "redirect:/forms.html";
				}
				else {
					return "redirect:/login.html";
				}
			}
			else {				
				return "redirect:/login.html";
			}
		}
		else {
			return "redirect:/login.html";
		}
	}
}