package br.unifesp.maritaca.business.account;

import br.unifesp.maritaca.business.util.Message;

public interface Account {
	
	public Message saveNewAccount(AccountDTO accountDTO);

}