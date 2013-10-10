package business.bean.user;

import java.util.List;

import persistence.EntityManager;
import persistence.model.UserAccount;
import business.dao.login.UserDAO;
import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.login.UserNotFoundException;

public class SearchUserBean {

    private EntityManager manager;
    private UserDAO user;

    public SearchUserBean() {
	manager = new EntityManager();
	user = new UserDAO(manager);
    }

    public List<UserAccount> listUsers() throws UnreachableDataBaseException, UserNotFoundException {
	return user.listAllUsers();
    }

    public UserAccount findUser(String email) throws UnreachableDataBaseException, UserNotFoundException {
	return user.findUserByEmail(email);
    }

}
