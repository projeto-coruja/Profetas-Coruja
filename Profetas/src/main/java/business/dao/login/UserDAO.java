package business.dao.login;

import java.util.List;

import persistence.EntityManager;
import persistence.model.Profile;
import persistence.model.UserAccount;
import persistence.util.DataAccessLayerException;
import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.login.UserNotFoundException;

/**
 * DAO das contas de usuários.
 * 
 */
public class UserDAO {

    private EntityManager manager;

    public UserDAO(EntityManager manager) {
	this.manager = manager;
    }

    /**
     * Busca um usuário a partir do email.
     * 
     * @param email
     *            - email do usuário.
     * @return Conta do usuário.
     * @throws UnreachableDataBaseException
     * @throws UserNotFoundException
     */
    public UserAccount findUserByEmail(String email) throws UnreachableDataBaseException {
	try {
	    return manager.findByNaturalId(UserAccount.class, email);
	} catch (DataAccessLayerException e) {
	    e.printStackTrace();
	    throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
	}
    }

    /**
     * Pega todos os usários presente no banco de dados.
     * 
     * @return Lista de DTOs contendo todos os usuários.
     * @throws UnreachableDataBaseException
     */
    public List<UserAccount> listAllUsers() throws UnreachableDataBaseException {
	try {
	    return manager.find("from UserAccount order by name");
	} catch (DataAccessLayerException e) {
	    e.printStackTrace();
	    throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
	}
    }

    /**
     * Lista todos os usuários de um determinado perfil.
     * 
     * @param profileName
     *            - Nome do perfil.
     * @return Lista de DTOs contendo todos os usuários de um determinado
     *         perfil.
     * @throws UnreachableDataBaseException
     */
    public List<UserAccount> listUsersByProfile(Profile profile) throws UnreachableDataBaseException {
	try {
	    return manager.find("from UserAccount where profile = '" + profile.getProfile()
		    + "' order by name");
	} catch (DataAccessLayerException e) {
	    e.printStackTrace();
	    throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
	}
    }

    public List<UserAccount> listUsersWithExpiredTokens(String threshold) throws UnreachableDataBaseException {
	try {
	    return manager.find("FROM UserAccount WHERE tokenDate <= '" + threshold + "'");
	} catch (DataAccessLayerException e) {
	    e.printStackTrace();
	    throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
	}
    }

}
