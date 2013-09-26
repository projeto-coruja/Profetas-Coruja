package business.Bean.user;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import persistence.exceptions.UpdateEntityException;
import persistence.model.UserAccount;
import business.Bean.util.EJBUtility;
import business.DAO.login.UserDAO;
import business.exceptions.DisallowedOperationException;
import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.login.UserNotFoundException;

/**
 * Classe de autenticação de usuário.
 */
public class AuthBean {

	private UserDAO loginDAO;
	
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
		loginDAO = new UserDAO();
	}
	
	/**
	 * Autentica um usuário
	 * @param email - Email do usuário.
	 * @param password - Senha do usuário.
	 * @param session - Sessão do browser.
	 * @param hashed - Flag indicando se o hash está ativado ou não.
	 * @throws UnreachableDataBaseException
	 * @throws UserNotFoundException
	 * @throws UpdateEntityException 
	 * @throws IllegalArgumentException 
	 */
	public void authenticate(String email, String password, HttpSession session, boolean hashed) throws UnreachableDataBaseException, UserNotFoundException, IllegalArgumentException, UpdateEntityException {

		UserAccount check = loginDAO.findUserByEmail(email);	// Busca o usuário no banco de dados.
		if(check == null){	// Se nenhum usuário for encontrado, setar na sessão que o login não foi sucedido.
			session.setAttribute("userLoginSucefull", false);
			return;
		}
		
		String hashedPassword = null;
		if(hashed == HashedPwd)
			hashedPassword = EJBUtility.getHash(password, "MD5");	// Hashea a senha fornecido para a comparação.
		else
			hashedPassword = password;

		if (check.getPassword().equals(hashedPassword)){	// Verifica se a senha fornecido é a mesma da cadastrada.
			TokenValidityChecker tvc = TokenValidityChecker.getInstance();
			// Gera um token.
			String sessionToken = EJBUtility.genRandomToken("LOG");
			// Grava o token no banco de dados.
			GregorianCalendar tokenDate = new GregorianCalendar();
			check.setGeneratedToken(sessionToken);
			check.setTokenDate(tokenDate);
			loginDAO.updateUser(check);
			// Define os atributos de sessão.
			session.setAttribute(sessionUserMail, check.getEmail());
			session.setAttribute(sessionUserName, check.getName());
			session.setAttribute(sessionUserPermissions, check.getProfile().getPermissions());
			session.setAttribute(sessionUserAccessToken, sessionToken);
			session.setAttribute(sessionUserAccessTokenCreatedTime, new GregorianCalendar().getTimeInMillis());
			session.setAttribute(sessionUserAccessTokenLifeTime, tvc.getExpireTime());
			session.setAttribute(sessionUserLoginSuccessfull, true);
			
		}
		else	throw new UserNotFoundException("Senha errada");
	}
	
	/**
	 * Faz o logOut do usuário
	 * @param session - Sessão do usuário
	 * @throws UserNotFoundException
	 * @throws UnreachableDataBaseException
	 */
	public void logOut(HttpSession session) throws UserNotFoundException, UnreachableDataBaseException{
		UserAccount user;
		if(isLoggedIn(session)){
			// Pega os atributos da sessão.
			String userMail = (String) session.getAttribute("userMail");
			String userToken = (String) session.getAttribute("userAccessToken");
			user = loginDAO.findUserByEmail(userMail); // Busca o usuário no banco de dados
			session.invalidate();
			if(user.getGeneratedToken() != null && user.getGeneratedToken().equals(userToken)){ // Comparação dos tokens
				try {
					user.setGeneratedToken(null);	// Invalida o token de sessão.
					user.setTokenDate(null);		// 
					loginDAO.updateUser(user);		// Atualiza o usuário no banco.
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (UpdateEntityException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Verifica se o usuário está logado.
	 * @param session - HttpSession
	 * @return <b>TRUE</b> se o usuário está logado, <b>FALSE</b> caso contrário.
	 */
	public boolean isLoggedIn(HttpSession session){
		return (session.getAttribute("userLoginSuccessfull") != null && (Boolean) session.getAttribute("userLoginSuccessfull"));
	}
	
	/**
	 * Verifica se o token da sessão é válido.
	 * @param session - Sessão do usuário.
	 * @return <b>true</b> se o token da sessão é válido.<br><b>false</b> caso contrário.
	 * @throws UnreachableDataBaseException
	 */
	public boolean validateToken(HttpSession session) throws UnreachableDataBaseException, DisallowedOperationException{
		UserAccount user;
		// Pega os atributos da sessão.
		String userMail = (String) session.getAttribute("userMail");
		String userToken = (String) session.getAttribute("userAccessToken");
		if(userMail == null || userToken == null)	return false;
		try {
			user = loginDAO.findUserByEmail(userMail); // Busca o usuário no banco de dados
			if(user.getGeneratedToken() == null || !user.getGeneratedToken().equals(userToken)){ // Comparação dos tokens
				session.invalidate();	// Força o logout.
				throw new DisallowedOperationException("Token Inválido"); // Se os tokens divergirem, negar a ação.
			}
		} catch (UserNotFoundException e) {
			session.invalidate();	// Força o logout.
			return false;
		}
		return true;
	}
	
	/**
	 * Método para criar um token de recuperação de senha.
	 * @param email - Email do usuário
	 * @return Token para autenticação.
	 * @throws UserNotFoundException
	 * @throws UnreachableDataBaseException
	 */
	public String createRecoveryToken(String email) throws UserNotFoundException, UnreachableDataBaseException{
		// Gera um token.
		String sessionToken = EJBUtility.genRandomToken("REC");
		// Busca o usuário no banco de dados.
		UserAccount user = loginDAO.findUserByEmail(email);
		GregorianCalendar tokenDate = new GregorianCalendar();
		user.setGeneratedToken(sessionToken);
		user.setTokenDate(tokenDate);
		try {
			loginDAO.updateUser(user);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (UpdateEntityException e) {
			e.printStackTrace();
		}
		return sessionToken;
	}
	
	/**
	 * Verifica se o usuário tem permissão para realizar uma operação.
	 * @param requiredPermission - Flag de permissão requerido para a operação.
	 * @param session - Sessão do usuário.
	 * @param tokenRequired - Flag indicando se a operação requer autenticação por token. 
	 * @return	<b>true</b> se o usuário está liberado para realizar a operação.<br><b>false</b> caso contrário.
	 * @throws UserNotFoundException
	 * @throws UnreachableDataBaseException
	 */
	public boolean allowedOperation(Permissions requiredPermission, HttpSession session, boolean tokenRequired) throws UnreachableDataBaseException, DisallowedOperationException {
		if(session.getAttribute("userLoginSuccessfull") != null && !(Boolean) session.getAttribute("userLoginSuccessfull")) return false;	// Verifica se o usuário está logado
		if(tokenRequired){	// Verifica se a ação requer autenticação por token
			if(!validateToken(session))	throw new DisallowedOperationException("Operação não permitido");			
		}
		
		for(String s : (String[])session.getAttribute("userPermissions"))
			if(s.equals(requiredPermission.name()))	return true;	// Verifica se o usuário tem permissão para executar uma derteminada ação.
		throw new DisallowedOperationException("Opeção não permitido");
	}
	
	/**
	 * Lista todas as permissões disponível.
	 * @return <code>List&lt;String&gt;</code> contendo todas as permissões do enum <code>Permissions</code>.
	 */
	public List<String> listAllPermission(){
		List<String> permissionList = new ArrayList<String>();
		Permissions[] plist = Permissions.values();
		for(Permissions p : plist){
			permissionList.add(p.name());
		}
		return permissionList;
	}
}
