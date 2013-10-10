package business.bean.user;

import persistence.EntityManager;
import persistence.model.Profile;
import persistence.model.exceptions.EntityPersistenceException;
import business.bean.util.Regex;
import business.dao.login.ProfileDAO;
import business.exceptions.login.IncorrectProfileInformationException;
import business.exceptions.login.NoDefaultProfileException;
import business.exceptions.login.UnreachableDataBaseException;

/**
 * Classe para gerenciamento de usuários.
 */
public class AdminBean {

    private EntityManager manager;
    private ProfileDAO profileDAO;

    // Regex para o nome de profiles
    private final String profileNamePattern = "([a-z0-9]){3,}";
    private final Regex profileNameChecker = new Regex(profileNamePattern);

    public AdminBean() {
	manager = new EntityManager();
	profileDAO = new ProfileDAO(manager);
    }

    /**
     * Adiciona um novo perfil.
     * 
     * @param profile
     *            - Profile construído
     * @param permissions
     *            - String[] contendo lista de permissões.
     * @throws UnreachableDataBaseException
     *             - Exceção do hibernate
     * @throws IncorrectProfileInformationException
     *             - Exceção ocorre quando um nome inválido é atribuido ao
     *             perfil ou se o perfil com tal nome já existe
     * @throws EntityPersistenceException
     *             - Exceção ocorre quando existe erro na cama de persistência
     */
    public void addProfile(String profile, String[] permissions) throws UnreachableDataBaseException, IncorrectProfileInformationException, EntityPersistenceException {
	if (!profileNameChecker.check(profile)) {
	    throw new IncorrectProfileInformationException("Nome inválido");
	}
	if (profileDAO.findProfileByName(profile) == null) {
	    throw new IncorrectProfileInformationException("Nome de profile já existe.");
	}

	new Profile(profile, permissions, false).save(manager);
    }

    /**
     * Seleciona um novo profile padrão
     * 
     * @param profile
     *            - Nome do novo profile padrão
     * @throws UnreachableDataBaseException
     * @throws NoDefaultProfileException
     * @throws EntityPersistenceException
     */
    public void selectDefaultProfile(Profile newDefault) throws UnreachableDataBaseException, NoDefaultProfileException, EntityPersistenceException {
	Profile actualDefault;

	actualDefault = profileDAO.getDefaultProfile();

	actualDefault.setDefault(false);
	newDefault.setDefault(true);

	actualDefault.update(manager);
	newDefault.update(manager);
    }

}
