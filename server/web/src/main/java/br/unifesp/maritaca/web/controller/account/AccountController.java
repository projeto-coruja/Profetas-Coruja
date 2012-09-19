package br.unifesp.maritaca.web.controller.account;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.unifesp.maritaca.business.account.Account;
import br.unifesp.maritaca.business.account.AccountValidator;
import br.unifesp.maritaca.business.account.AccountDTO;
import br.unifesp.maritaca.business.util.Message;
import br.unifesp.maritaca.web.controller.AbstractController;

@Controller
public class AccountController extends AbstractController {
	
	private static final long serialVersionUID = 1L;
	private static final String USER = "user";
	@Autowired Account account;
	@Autowired AccountValidator accountValidator;
	
	@ModelAttribute(USER)
	public AccountDTO init() {
		return new AccountDTO();
	}

	@RequestMapping(value = "/create-account", method = RequestMethod.GET)
    public String showView() {
        return "create_account";
    }
	
	@RequestMapping(value = "/create-account", method = RequestMethod.POST)
    public String saveAccount(@Valid @ModelAttribute(USER) AccountDTO user, BindingResult result, Model model) {
		accountValidator.validate(user, result);
		if(result.hasErrors()) {
			return "create_account";
		}
		Message message = account.saveNewAccount(user);
		model.addAttribute(message.getSuccess()?MSG_SUCCESS:MSG_ERROR, message.getMessage());
		System.out.println(message.getSuccess() + " " + message.getMessage());
		return "create_account";
	}
}