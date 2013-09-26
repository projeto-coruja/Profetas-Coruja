package business.Bean.user;

import java.util.List;

import persistence.model.EntityModel;
import persistence.model.UserAccount;
import business.DAO.login.UserDAO;
import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.login.UserNotFoundException;

public class SearchUserBean {

	UserDAO user;

	public SearchUserBean() {
		user = new UserDAO();
	}

	public List<EntityModel> listUsers() throws UnreachableDataBaseException, UserNotFoundException{
		return user.listAllUsers();
	}

	public UserAccount findUser(String email) throws UnreachableDataBaseException, UserNotFoundException{
		return user.findUserByEmail(email);
	}
	
}
