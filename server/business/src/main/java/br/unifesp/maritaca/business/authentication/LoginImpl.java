package br.unifesp.maritaca.business.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unifesp.maritaca.business.account.AccountDTO;
import br.unifesp.maritaca.dataaccess.dao.UserDAO;
import br.unifesp.maritaca.dataaccess.domain.UserVO;

@Service("login")
public class LoginImpl implements Login {
	
	@Autowired
	private UserDAO userDAO;

	@Override
	public AccountDTO doLogin(AccountDTO loginDTO) {
		AccountDTO accountDTO = findUserByEmail(loginDTO.getEmail());
		if(loginSuccessful(loginDTO, accountDTO)) {
			return accountDTO;
		} else {
			return null;
		}
	}
	
	private boolean loginSuccessful(AccountDTO loginDTO, AccountDTO accountDTO) {
		//TODO: "" ?
		return (accountDTO != null && loginDTO.getPassword().equals(accountDTO.getPassword()));
	}
	
	public AccountDTO findUserByEmail(String email) {
		UserVO userVO = userDAO.findUserByEmail(email);
		if(userVO != null) {
			AccountDTO accountDTO = new AccountDTO();
			accountDTO.setEmail(userVO.getEmail());
			accountDTO.setPassword(userVO.getPassword());
			return accountDTO;
		}
		else
			return null;
	}

	@Override
	public AccountDTO doLoginOpenId(AccountDTO accountDTO) {
		UserVO userVO = userDAO.findUserByEmail(accountDTO.getEmail());
		if(userVO != null) {
			accountDTO.setKey(""+userVO.getId());
		}
		else {
			userVO = new UserVO(
					accountDTO.getFirstName(), 
					accountDTO.getLastName(), 
					accountDTO.getEmail(),
					accountDTO.getPassword());
			userDAO.saveUser(userVO);
			if(userVO.getId() != null) {
				accountDTO.setKey(""+userVO.getId());
			}
		}
		return accountDTO;
	}
}