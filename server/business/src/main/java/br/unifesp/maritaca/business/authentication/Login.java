package br.unifesp.maritaca.business.authentication;

import br.unifesp.maritaca.business.account.AccountDTO;

public interface Login {
	
	public AccountDTO doLogin(AccountDTO accountDTO);

	public AccountDTO doLoginOpenId(AccountDTO accountDTO);
}