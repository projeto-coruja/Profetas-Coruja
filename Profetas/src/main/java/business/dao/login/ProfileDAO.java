package business.dao.login;

import java.util.List;

import persistence.EntityManager;
import persistence.model.Profile;
import persistence.model.exceptions.EntityPersistenceException;
import persistence.util.DataAccessLayerException;
import business.exceptions.login.NoDefaultProfileException;
import business.exceptions.login.UnreachableDataBaseException;

/**
 * DAO dos perfils de usuário.
 * 
 */
public class ProfileDAO {

    private EntityManager manager;

    public ProfileDAO(EntityManager manager) {
	this.manager = manager;
    }

    /**
     * Busca o profile padrão.
     * 
     * @return Perfil padrão.
     * @throws UnreachableDataBaseException
     * @throws NoDefaultProfileException
     */
    public Profile getDefaultProfile() throws UnreachableDataBaseException, NoDefaultProfileException {
	List<Profile> resultSet = null;
	try {
	    resultSet = manager.find("FROM Profile WHERE isDefault = TRUE");
	    if (resultSet.isEmpty()) {
		throw new NoDefaultProfileException();
	    } else {
		return resultSet.get(0);
	    }
	} catch (DataAccessLayerException e) {
	    e.printStackTrace();
	    throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
	}
    }

    /**
     * Cria um novo perfil de usuário baseado no perfil padrão.<br>
     * Se o perfil padrão não existe, o perfil novo não conterá nenhuma
     * permissão.
     * 
     * @param profile
     *            - Nome do perfil.
     * @throws UnreachableDataBaseException
     */
    public void createProfile(String profile) throws UnreachableDataBaseException {
	String[] defPermissions;
	try {
	    defPermissions = getDefaultProfile().getPermissions();
	} catch (NoDefaultProfileException e) {
	    defPermissions = new String[0];
	}

	try {
	    new Profile(profile, defPermissions, false).save(manager);
	} catch (DataAccessLayerException e) {
	    e.printStackTrace();
	    throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
	} catch (EntityPersistenceException e) {
	    e.getCause().printStackTrace();
	}
    }

    /**
     * Busca um perfil a partir do nome.
     * 
     * @param profile
     *            - Nome do perfil
     * @return
     * @throws UnreachableDataBaseException
     */
    public Profile findProfileByName(String profile) throws UnreachableDataBaseException {
	try {
	    return manager.findByNaturalId(Profile.class, profile);
	} catch (DataAccessLayerException e) {
	    e.printStackTrace();
	    throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
	}
    }

    /**
     * Pega todos os perfils presente no banco de dados.
     * 
     * @return Lista de DTOs contendo todos os perfils.
     * @throws UnreachableDataBaseException
     */
    public List<Profile> getAllProfiles() throws UnreachableDataBaseException {
	try {
	    return manager.find("from ProfileMO order by profile");
	} catch (DataAccessLayerException e) {
	    e.printStackTrace();
	    throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
	}
    }

}
