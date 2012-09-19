package br.unifesp.maritaca.web.controller.general;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.unifesp.maritaca.business.account.Account;
import br.unifesp.maritaca.business.account.AccountDTO;

@Controller
public class IndexController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired Account account;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
    public String showIndex() {
		
		System.out.println("index");		
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setFirstName("Jimmy");
		accountDTO.setLastName("ValSan");
		accountDTO.setEmail("sirghost@gmail.com");
		accountDTO.setPassword("123");		
		account.saveNewAccount(accountDTO);
        
		return "index";
    }
}