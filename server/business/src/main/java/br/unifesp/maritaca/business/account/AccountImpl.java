package br.unifesp.maritaca.business.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unifesp.maritaca.business.util.Message;
import br.unifesp.maritaca.dataaccess.dao.UserDAO;
import br.unifesp.maritaca.dataaccess.domain.UserVO;

/**
 * 
 * @author Tiago Barabasz
 *
 */
@Service("account")
public class AccountImpl implements Account {
	
	@Autowired private UserDAO userDAO;
	
	
	
	
	/**
	 * Checks if the email from the user is already registered
	 * in the database. If the email belongs to the email from the logged
	 * user, this function also returns false.
	 * @param currentUser 
	 * @return true if the email is already taken, false otherwise.
	 */
	public boolean registeredEmail(String email) {
		//return getAccountEditorDAO().findUserByEmail(email) != null;
		return false;
	}

	public Message saveNewAccount(AccountDTO accountDTO) {
		Message message = new Message();
		UserVO userVO = new UserVO(
				accountDTO.getFirstName(), 
				accountDTO.getLastName(), 
				accountDTO.getEmail(),
				accountDTO.getPassword());
		userDAO.saveUser(userVO);
		if(userVO.getId() != null) {
			message.setSuccess(true);
			message.setMessage("saved");
		}
		else {
			message.setSuccess(false);
			message.setMessage("error");
		}
		
		List<UserVO> users = userDAO.findAll();
        for(UserVO u : users) {
            System.out.println("u: " + u.getEmail() + u.getPassword());
        }
		return message;
	}
}