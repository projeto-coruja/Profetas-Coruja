package business.bean.user;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import persistence.EntityManager;
import persistence.model.UserAccount;
import persistence.model.exceptions.EntityPersistenceException;
import business.bean.util.EJBUtility;
import business.dao.login.UserDAO;
import business.exceptions.DisallowedOperationException;
import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.login.UserNotFoundException;

/**
 * Classe de autenticação de usuário.
 */
public class AuthBean {

    private UserDAO userDAO;
    private EntityManager manager;

    /**
     * Atributo de sessão: userMail
     */
    public static final String sessionUserMail = "userMail";
    /**
     * Atributo de sessão: userName
     */
    public static final String sessionUserName = "userName";
    /**
     * Atributo de sessão: userPermissions
     */
    public static final String sessionUserPermissions = "userPermissions";
    /**
     * Atributo de sessão: userAccessToken
     */
    public static final String sessionUserAccessToken = "userAccessToken";
    /**
     * Atributo de sessão
     */
    public static final String sessionUserAccessTokenCreatedTime = "userAccessTokenCreatedTime";
    /**
     * Atributo de sessão
     */
    public static final String sessionUserAccessTokenLifeTime = "userAccessTokenLifeTime";
    /**
     * Atributo de sessão: userLoginSuccessfull
     */
    public static final String sessionUserLoginSuccessfull = "userLoginSuccessfull";

    /**
     * Flag para habilitar hash de senha
     */
    public static final boolean HashedPwd = true;
    /**
     * Flag para desabilitar hash de senha
     */
    public static final boolean NonHashedPwd = false;

    public AuthBean() {
	manager = new EntityManager();
	userDAO = new UserDAO(manager);
    }

    /**
     * Autentica um usuário
     * 
     * @param email
     *            - Email do usuário.
     * @param password
     *            - Senha do usuário.
     * @param session
     *            - Sessão do browser.
     * @param hashed
     *            - Flag indicando se o hash está ativado ou não.
     * @throws UnreachableDataBaseException
     * @throws UserNotFoundException
     * @throws UpdateEntityException
     * @throws IllegalArgumentException
     * @throws EntityPersistenceException
     */
    public void authenticate(String email, String password, HttpSession session, boolean hashed) throws UnreachableDataBaseException, UserNotFoundException, IllegalArgumentException, EntityPersistenceException {
	UserAccount check = userDAO.findUserByEmail(email);
	if (check == null) {
	    // Se nenhum usuário for encontrado, setar na sessão que o login não
	    // foi sucedido.
	    session.setAttribute("userLoginSucefull", false);
	    return;
	}

	String hashedPassword = null;
	if (hashed == HashedPwd) {
	    // Faz hash da senha
	    hashedPassword = EJBUtility.getHash(password, "MD5");
	} else {
	    hashedPassword = password;
	}

	if (check.getPassword().equals(hashedPassword)) {
	    TokenValidityChecker tvc = TokenValidityChecker.INSTANCE;
	    // Gera um token.
	    String sessionToken = EJBUtility.genRandomToken("LOG");
	    // Grava o token no banco de dados.
	    GregorianCalendar tokenDate = new GregorianCalendar();
	    check.setGeneratedToken(sessionToken);
	    check.setTokenDate(tokenDate);
	    check.update(manager);
	    // Define os atributos de sessão.
	    session.setAttribute(sessionUserMail, check.getEmail());
	    session.setAttribute(sessionUserName, check.getName());
	    session.setAttribute(sessionUserPermissions, check.getProfile().getPermissions());
	    session.setAttribute(sessionUserAccessToken, sessionToken);
	    session.setAttribute(sessionUserAccessTokenCreatedTime,
		    new GregorianCalendar().getTimeInMillis());
	    session.setAttribute(sessionUserAccessTokenLifeTime, tvc.getExpireTime());
	    session.setAttribute(sessionUserLoginSuccessfull, true);

	} else {
	    throw new UserNotFoundException("Senha errada");
	}
    }

    /**
     * Faz o logOut do usuário
     * 
     * @param session
     *            - Sessão do usuário
     * @throws UserNotFoundException
     * @throws UnreachableDataBaseException
     * @throws EntityPersistenceException
     */
    public void logOut(HttpSession session) throws UserNotFoundException, UnreachableDataBaseException, EntityPersistenceException {
	UserAccount user;
	if (isLoggedIn(session)) {
	    // Pega os atributos da sessão.
	    String userMail = (String) session.getAttribute("userMail");
	    String userToken = (String) session.getAttribute("userAccessToken");
	    user = userDAO.findUserByEmail(userMail); // Busca o usuário no
						      // banco de dados
	    session.invalidate();
	    // Comparação dos tokens
	    if (user.getGeneratedToken() != null && user.getGeneratedToken().equals(userToken)) {
		try {
		    user.setGeneratedToken(null); // Invalida o token de sessão.
		    user.setTokenDate(null); //
		    user.update(manager);
		    ; // Atualiza o usuário no banco.
		} catch (IllegalArgumentException e) {
		    e.printStackTrace();
		}
	    }
	}
    }

    /**
     * Verifica se o usuário está logado.
     * 
     * @param session
     *            - HttpSession
     * @return <b>TRUE</b> se o usuário está logado, <b>FALSE</b> caso
     *         contrário.
     */
    public boolean isLoggedIn(HttpSession session) {
	return (session.getAttribute("userLoginSuccessfull") != null && (Boolean) session
		.getAttribute("userLoginSuccessfull"));
    }

    /**
     * Verifica se o token da sessão é válido.
     * 
     * @param session
     *            - Sessão do usuário.
     * @return <b>true</b> se o token da sessão é válido.<br>
     *         <b>false</b> caso contrário.
     * @throws UnreachableDataBaseException
     */
    public boolean validateToken(HttpSession session) throws UnreachableDataBaseException, DisallowedOperationException {
	UserAccount user;
	// Pega os atributos da sessão.
	String userMail = (String) session.getAttribute("userMail");
	String userToken = (String) session.getAttribute("userAccessToken");
	if (userMail == null || userToken == null) {
	    return false;
	}
	// Busca o usuário no banco de dados
	user = userDAO.findUserByEmail(userMail);
	if (user == null) {
	    session.invalidate();
	    return false;
	}
	// Comparação dos tokens
	if (user.getGeneratedToken() == null || !user.getGeneratedToken().equals(userToken)) {
	    session.invalidate(); // Força o logout.
	    // Se os tokens divergirem, negar a ação.
	    throw new DisallowedOperationException("Token Inválido");
	}

	return true;
    }

    /**
     * Método para criar um token de recuperação de senha.
     * 
     * @param email
     *            - Email do usuário
     * @return Token para autenticação.
     * @throws UserNotFoundException
     * @throws UnreachableDataBaseException
     * @throws EntityPersistenceException
     */
    public String createRecoveryToken(String email) throws UserNotFoundException, UnreachableDataBaseException, EntityPersistenceException {
	// Gera um token.
	String sessionToken = EJBUtility.genRandomToken("REC");
	// Busca o usuário no banco de dados.
	UserAccount user = userDAO.findUserByEmail(email);
	GregorianCalendar tokenDate = new GregorianCalendar();
	user.setGeneratedToken(sessionToken);
	user.setTokenDate(tokenDate);
	user.update(manager);
	return sessionToken;
    }

    /**
     * Verifica se o usuário tem permissão para realizar uma operação.
     * 
     * @param requiredPermission
     *            - Flag de permissão requerido para a operação.
     * @param session
     *            - Sessão do usuário.
     * @param tokenRequired
     *            - Flag indicando se a operação requer autenticação por token.
     * @return <b>true</b> se o usuário está liberado para realizar a operação.<br>
     *         <b>false</b> caso contrário.
     * @throws UserNotFoundException
     * @throws UnreachableDataBaseException
     */
    public boolean allowedOperation(Permissions requiredPermission, HttpSession session,
	    boolean tokenRequired) throws UnreachableDataBaseException, DisallowedOperationException {
	if (session.getAttribute("userLoginSuccessfull") != null
		&& !(Boolean) session.getAttribute("userLoginSuccessfull")) {
	    return false; // Verifica se o usuário está logado
	}
	if (tokenRequired) { // Verifica se a ação requer autenticação por token
	    if (!validateToken(session)) {
		throw new DisallowedOperationException("Operação não permitido");
	    }
	}

	for (String s : (String[]) session.getAttribute("userPermissions")) {
	    if (s.equals(requiredPermission.name())) {
		return true; // Verifica se o usuário tem permissão para
	    }
	}
	// executar uma derteminada ação.
	throw new DisallowedOperationException("Opeção não permitido");
    }

    /**
     * Lista todas as permissões disponível.
     * 
     * @return <code>List&lt;String&gt;</code> contendo todas as permissões do
     *         enum <code>Permissions</code>.
     */
    public List<String> listAllPermission() {
	List<String> permissionList = new ArrayList<String>();
	Permissions[] plist = Permissions.values();
	for (Permissions p : plist) {
	    permissionList.add(p.name());
	}
	return permissionList;
    }
}
