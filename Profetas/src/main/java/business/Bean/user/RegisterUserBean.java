package business.Bean.user;

import persistence.dto.UserAccount;
import persistence.exceptions.UpdateEntityException;
import business.Bean.util.EJBUtility;
import business.Bean.util.Regex;
import business.DAO.login.UserDAO;
import business.exceptions.MailNotConfiguredException;
import business.exceptions.login.DuplicateUserException;
import business.exceptions.login.IncorrectLoginInformationException;
import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.login.UserNotFoundException;

public class RegisterUserBean {

	private UserDAO userDAO;
	
	private final String emailPattern = "([A-Za-z0-9])([_.-]?[A-Za-z0-9])*@([A-Za-z0-9]+)(\\.[A-Za-z0-9]+)+";
	private final Regex emailChecker = new Regex(emailPattern);
	
	public RegisterUserBean() {
		userDAO = new UserDAO();
	}
	
	/**
	 * Adiciona um usuário novo
	 * @param email - Email do usuário
	 * @param password - Senha
	 * @param name - Nome
	 * @throws IncorrectLoginInformationException - Erro caso e email não bata com a expressão regular
	 * @throws DuplicateUserException - Erro caso o email já tenha sido cadastrado antes
	 */
	public void addUser(String email, String password, String name) throws IncorrectLoginInformationException, DuplicateUserException{
		try {
			if(!emailChecker.check(email))	throw new IncorrectLoginInformationException("Email inválido");	
			UserAccount check;
			try {
				check = userDAO.findUserByEmail(email);
				if(check != null)	throw new DuplicateUserException();
			} catch (UserNotFoundException e) {
				userDAO.addUser(email, name, EJBUtility.getHash(password, "MD5"), null);
			}
		} catch (UnreachableDataBaseException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Gera uma nova senha aleatória para um usuário
	 * @param email - Email
	 * @return Nova senha.
	 * @throws UnreachableDataBaseException
	 * @throws UserNotFoundException
	 * @throws IllegalArgumentException
	 * @throws UpdateEntityException
	 * @throws MailNotConfiguredException
	 */
	public String recuperarSenha(String email) throws UnreachableDataBaseException, UserNotFoundException, IllegalArgumentException, UpdateEntityException, MailNotConfiguredException {
		UserAccount user = userDAO.findUserByEmail(email);
		String newPassword = EJBUtility.genRandomString(6);
		//SendMail mail = new SendMail();
		user.setGeneratedToken(null);
		user.setTokenDate(null);
		user.setPassword(EJBUtility.getHash(newPassword, "MD5"));
		userDAO.updateUser(user);
		try{
			//mail.send("Não Responder",user.getEmail(), "Sua nova senha do Grão Pará!", "Aqui está a sua nova senha do Grão Pará.\n\n"+newPassword);
		}catch(RuntimeException e){
			e.printStackTrace();
		}
		return newPassword;
	}
	
	/**
	 * Atualiza informações de um determinado usuário.
	 * @param email - String contendo o email atual do usuário
	 * @param newEmail - String contendo e email novo. Mandar <b>null</b> caso não queira fazer modificações.
	 * @param newPassword - String contendo a nova senha.  Mandar <b>null</b> caso não queira fazer modificações.
	 * @throws UserNotFoundException
	 * @throws UnreachableDataBaseException
	 * @throws IllegalArgumentException
	 * @throws UpdateEntityException
	 */
	public void changeUserInformation(String email, String newEmail, String newPassword) throws UserNotFoundException, UnreachableDataBaseException, IllegalArgumentException, UpdateEntityException{
		UserAccount user = userDAO.findUserByEmail(email);
		if(isInit(newEmail))	user.setEmail(newEmail);
		if(isInit(newPassword))	user.setPassword(EJBUtility.getHash(newPassword));
		userDAO.updateUser(user);
	}

	private boolean isInit(String s){
		return s != null && !s.isEmpty();
	}
}
