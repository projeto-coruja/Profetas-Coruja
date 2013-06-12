package business.Bean.user;

import java.util.List;

import persistence.dto.DTO;
import persistence.dto.Profile;
import persistence.dto.UserAccount;
import persistence.exceptions.UpdateEntityException;
import business.Bean.util.EJBUtility;
import business.Bean.util.Regex;
import business.DAO.login.ProfileDAO;
import business.DAO.login.UserDAO;
import business.exceptions.login.DuplicateUserException;
import business.exceptions.login.IncorrectLoginInformationException;
import business.exceptions.login.IncorrectProfileInformationException;
import business.exceptions.login.NoDefaultProfileException;
import business.exceptions.login.ProfileNotFoundException;
import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.login.UserNotFoundException;

/**
 * Classe para gerenciamento de usuários. 
 */
public class AdminBean {

	private UserDAO userDAO;
	private ProfileDAO profileDAO;
	
	// Regex para o nome de profiles
	private final String profileNamePattern = "([a-z0-9]){3,}";
	private final Regex profileNameChecker = new Regex(profileNamePattern);
	// Regex para emails
	private final String emailPattern = "([A-Za-z0-9])([_.-]?[A-Za-z0-9])*@([A-Za-z0-9]+)(\\.[A-Za-z0-9]+)+";
	private final Regex emailChecker = new Regex(emailPattern);

	public AdminBean() {
		userDAO = new UserDAO();
		profileDAO = new ProfileDAO();
	}

	/**
	 * Adiciona um novo perfil.
	 * @param profile - String contendo o nome do perfil
	 * @param permissions - String[] contendo lista de permissões.
	 * @throws UnreachableDataBaseException - Exceção do hibernate
	 * @throws IncorrectProfileInformationException - Exceção occore quando um nome inválido é atribuido ao perfil ou se o perfil com tal nome já existe
	 */
	public void addProfile(String profile, String[] permissions) 
			throws UnreachableDataBaseException, IncorrectProfileInformationException {
		try {
			if(!profileNameChecker.check(profile))	throw new IncorrectProfileInformationException("Nome inválido");
			Profile check = profileDAO.findProfileByName(profile);
			if(check.getProfile().equals(profile))	throw new IncorrectProfileInformationException("Nome de profile já existe.");
		} catch(ProfileNotFoundException e) {
			profileDAO.createProfile(profile, permissions);
		} catch (UnreachableDataBaseException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * Seleciona um novo profile padrão
	 * @param profile - Nome do novo profile padrão
	 * @throws ProfileNotFoundException
	 * @throws UnreachableDataBaseException
	 * @throws NoDefaultProfileException 
	 */
	public void selectDefaultProfile(String profile) throws ProfileNotFoundException, UnreachableDataBaseException, NoDefaultProfileException{
		Profile actualDefault;
		Profile newDefault;
		
		actualDefault = profileDAO.getDefaultProfile();
		newDefault = profileDAO.findProfileByName(profile);
		
		actualDefault.setDefault(false);
		newDefault.setDefault(true);
		
		profileDAO.updateProfile(actualDefault.getProfile(), actualDefault.getPermissions(), actualDefault.isDefault());
		profileDAO.updateProfile(newDefault.getProfile(), newDefault.getPermissions(), newDefault.isDefault());
	}
	
	/**
	 * Adiciona um novo usuário.
	 * @param email - String contendo o email do usuário.
	 * @param name - String contendo o nome do usuário.
	 * @param password - String contendo a senha do usuário.
	 * @param profile - String contendo o nome do perfil do usuário.
	 * @throws UnreachableDataBaseException - Exceção do hibernate.
	 * @throws IncorrectLoginInformationException - Exceção para email inválido.
	 * @throws DuplicateUserException - Exceção para usuário duplicado (emails identicos).
	 * @throws ProfileNotFoundException 
	 * @throws NoDefaultProfileException 
	 */
	public void addUser(String email, String name, String password, String profile) throws UnreachableDataBaseException, IncorrectLoginInformationException, DuplicateUserException, ProfileNotFoundException, NoDefaultProfileException {
		try {
			if(!emailChecker.check(email))	
				throw new IncorrectLoginInformationException("Email inválido");	
			
			UserAccount check;
			
			try {
				check = userDAO.findUserByEmail(email);
				
				if(check != null)	
					throw new DuplicateUserException();
				
			} catch (UserNotFoundException e) {
				Profile p_dto = null;
				try {
					p_dto = profileDAO.findProfileByName(profile);
				} catch (ProfileNotFoundException f) {
					e.printStackTrace();
				}
				userDAO.addUser(email, name, EJBUtility.getHash(password), p_dto);
			}
		} catch (UnreachableDataBaseException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Atualiza informações de um determinado usuário.
	 * @param email - String contendo o email atual do usuário
	 * @param newEmail - String contendo e email novo. Mandar <b>null</b> caso não queira fazer modificações.
	 * @param newPassword - String contendo a nova senha.  Mandar <b>null</b> caso não queira fazer modificações.
	 * @param newProfile - String contendo o novo profile. Mandar <b>null</b> caso não queira fazer modificações.
	 * @throws UserNotFoundException
	 * @throws UnreachableDataBaseException
	 * @throws ProfileNotFoundException
	 * @throws IllegalArgumentException
	 * @throws UpdateEntityException
	 */
	public void changeUserInformation(String email, String newEmail, String newPassword, String newProfile) throws UserNotFoundException, UnreachableDataBaseException, ProfileNotFoundException, IllegalArgumentException, UpdateEntityException{
		UserAccount user = userDAO.findUserByEmail(email);
		if(isInit(newEmail))	user.setEmail(newEmail);
		if(isInit(newPassword))	user.setPassword(EJBUtility.getHash(newPassword));
		if(isInit(newProfile)){
			Profile p = profileDAO.findProfileByName(newProfile);
			user.setProfile(p);
		}
		userDAO.updateUser(user);
	}

	/**
	 * Troca o perfil de um determinado usuário.
	 * @param email - String contendo o email do usuário
	 * @param novo_perfil - String contendo o nome do novo perfil.
	 * @throws IncorrectProfileInformationException
	 * @throws UnreachableDataBaseException
	 * @throws UserNotFoundException
	 * @throws ProfileNotFoundException
	 * @throws IllegalArgumentException
	 * @throws UpdateEntityException
	 */
	public void changeUserProfile(String email, String novo_perfil) 
			throws IncorrectProfileInformationException, UnreachableDataBaseException, UserNotFoundException, 
				ProfileNotFoundException, IllegalArgumentException, UpdateEntityException {
		userDAO.changeUserProfile(email, profileDAO.findProfileByName(novo_perfil));
	}

	/**
	 * Remove um determinado usuário.
	 * @param email - String contendo o email do usuário a ser removido.
	 * @throws UnreachableDataBaseException
	 * @throws UserNotFoundException
	 */
	public void removeUser(String email) throws UnreachableDataBaseException, UserNotFoundException {
		userDAO.removeUser(email);
	}

	/**
	 * Método para pegar uma lista de todos os perfils registrado no banco.
	 * @return List<DTO> contendo todos os perfils.
	 * @throws UnreachableDataBaseException
	 * @throws ProfileNotFoundException - Exceção para quando não há perfils registrado no banco.
	 */
	public List<DTO> getAllAvailableProfiles() throws UnreachableDataBaseException, ProfileNotFoundException{
		return profileDAO.getAllProfiles();
	}
	
	private boolean isInit(String s){
		return s != null && !s.isEmpty();
	}
	
}
