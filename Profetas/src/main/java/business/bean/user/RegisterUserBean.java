package business.bean.user;

import persistence.EntityManager;
import persistence.model.Profile;
import persistence.model.UserAccount;
import persistence.model.exceptions.EntityPersistenceException;
import business.bean.util.EJBUtility;
import business.bean.util.Regex;
import business.bean.util.SendMail;
import business.dao.login.ProfileDAO;
import business.dao.login.UserDAO;
import business.exceptions.MailNotConfiguredException;
import business.exceptions.login.DuplicateUserException;
import business.exceptions.login.IncorrectLoginInformationException;
import business.exceptions.login.NoDefaultProfileException;
import business.exceptions.login.ProfileNotFoundException;
import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.login.UserNotFoundException;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

public class RegisterUserBean {

    private UserDAO userDAO;
    private ProfileDAO profileDAO;
    private EntityManager manager;

    private final String emailPattern = "([A-Za-z0-9])([_.-]?[A-Za-z0-9])*@([A-Za-z0-9]+)(\\.[A-Za-z0-9]+)+";
    private final Regex emailChecker = new Regex(emailPattern);

    public RegisterUserBean() {
	manager = new EntityManager();
	userDAO = new UserDAO(manager);
	profileDAO = new ProfileDAO(manager);
    }

    /**
     * Adiciona um usuário novo
     * 
     * @param email
     *            - Email do usuário
     * @param password
     *            - Senha
     * @param name
     *            - Nome
     * @param profile
     *            - Profile
     * @throws IncorrectLoginInformationException
     *             - Erro caso e email não bata com a expressão regular
     * @throws DuplicateUserException
     *             - Erro caso o email já tenha sido cadastrado antes
     * @throws ProfileNotFoundException
     * @throws NoDefaultProfileException
     * @throws UnreachableDataBaseException
     * @throws EntityPersistenceException
     */
    public void addUser(String email, String password, String name, String profile) throws IncorrectLoginInformationException, DuplicateUserException, NoDefaultProfileException, UnreachableDataBaseException, ProfileNotFoundException, EntityPersistenceException {
	checkNotEmpty(email, password, name);
	Profile p = profileDAO.findProfileByName(profile);
	if (p == null) {
	    p = profileDAO.getDefaultProfile();
	}
	addUser(new UserAccount(name, p, email, EJBUtility.getHash(password), null, null));
    }

    /**
     * Gera uma nova senha aleatória para um usuário
     * 
     * @param email
     *            - Email
     * @return Nova senha.
     * @throws UnreachableDataBaseException
     * @throws UserNotFoundException
     * @throws MailNotConfiguredException
     * @throws EntityPersistenceException
     */
    public String recuperarSenha(String email) throws UnreachableDataBaseException, UserNotFoundException, EntityPersistenceException, MailNotConfiguredException {
	checkNotEmpty(email);
	UserAccount user = userDAO.findUserByEmail(email);
	if (user == null) {
	    throw new UserNotFoundException();
	}

	String newPassword = EJBUtility.genRandomString(6);
	SendMail mail = new SendMail();
	user.setGeneratedToken(null);
	user.setTokenDate(null);
	user.setPassword(EJBUtility.getHash(newPassword, "MD5"));
	user.update(manager);
	try {
	    mail.send("Não Responder", user.getEmail(), "Sua nova senha do Grão Pará!",
		    "Aqui está a sua nova senha do Grão Pará.\n\n" + newPassword);
	} catch (RuntimeException e) {
	    e.printStackTrace();
	}
	return newPassword;
    }

    /**
     * Atualiza informações de um determinado usuário.
     * 
     * @param email
     *            - String contendo o email atual do usuário
     * @param newEmail
     *            - String contendo e email novo. Mandar <b>null</b> caso não
     *            queira fazer modificações.
     * @param newPassword
     *            - String contendo a nova senha. Mandar <b>null</b> caso não
     *            queira fazer modificações.
     * @throws UnreachableDataBaseException
     * @throws DuplicateUserException
     * @throws EntityPersistenceException
     * @throws IncorrectLoginInformationException
     */
    public void changeUserInformation(String email, String newEmail, String newPassword) throws UnreachableDataBaseException, DuplicateUserException, IncorrectLoginInformationException, EntityPersistenceException {
	checkNotEmpty(email, newEmail, newPassword);
	UserAccount user = userDAO.findUserByEmail(email);
	UserAccount check = userDAO.findUserByEmail(newEmail);
	if (check != null) {
	    throw new DuplicateUserException("Já existe esse email cadastrado!");
	}
	user.setEmail(newEmail);
	user.setPassword(EJBUtility.getHash(newPassword));
	changeUserInformation(user);
    }

    /**
     * Adiciona um novo usuário.
     * 
     * @param user
     *            - Usuário construído.
     * @throws UnreachableDataBaseException
     *             - Exceção do hibernate.
     * @throws IncorrectLoginInformationException
     *             - Exceção para email inválido.
     * @throws DuplicateUserException
     *             - Exceção para usuário duplicado (emails identicos).
     * @throws NoDefaultProfileException
     * @throws EntityPersistenceException
     */
    public void addUser(UserAccount user) throws UnreachableDataBaseException, IncorrectLoginInformationException, DuplicateUserException, NoDefaultProfileException, EntityPersistenceException {
	if (!emailChecker.check(user.getEmail())) {
	    throw new IncorrectLoginInformationException("Email inválido");
	}
	if (userDAO.findUserByEmail(user.getEmail()) != null) {
	    throw new DuplicateUserException();
	}
	user.save(manager);
    }

    /**
     * Atualiza informações de um determinado usuário.
     * 
     * @param user
     *            - Usuário construído.
     * @throws UnreachableDataBaseException
     *             - Exceção do hibernate.
     * @throws IncorrectLoginInformationException
     *             - Exceção para email inválido.
     * @throws DuplicateUserException
     *             - Exceção para usuário duplicado (emails identicos).
     * @throws EntityPersistenceException
     */
    public void changeUserInformation(UserAccount user) throws IncorrectLoginInformationException, UnreachableDataBaseException, DuplicateUserException, EntityPersistenceException {
	if (!emailChecker.check(user.getEmail())) {
	    throw new IncorrectLoginInformationException("Email inválido");
	}
	if (userDAO.findUserByEmail(user.getEmail()) != null) {
	    throw new DuplicateUserException();
	}
	user.update(manager);
    }

    private static void checkNotEmpty(String... strings) {
	for (String s : strings)
	    Preconditions.checkArgument(!Strings.isNullOrEmpty(s));
    }
}
