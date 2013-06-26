package business.DAO.login;

import java.util.List;

import persistence.PersistenceAccess;
import persistence.dto.DTO;
import persistence.dto.Profile;
import persistence.dto.UserAccount;
import persistence.exceptions.UpdateEntityException;
import persistence.util.DataAccessLayerException;
import business.exceptions.login.IncorrectProfileInformationException;
import business.exceptions.login.NoDefaultProfileException;
import business.exceptions.login.ProfileNotFoundException;
import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.login.UserNotFoundException;

/**
 * DAO das contas de usuários. 
 *
 */
public class UserDAO {

	private PersistenceAccess manager;

	public UserDAO() {
		manager = new PersistenceAccess();
	}
	
	/**
	 * Busca um usuário a partir do email.
	 * @param email - email do usuário.
	 * @return Conta do usuário.
	 * @throws UnreachableDataBaseException
	 * @throws UserNotFoundException
	 */
	public UserAccount findUserByEmail(String email) throws UnreachableDataBaseException, UserNotFoundException {
		List<DTO> resultSet = null;
		try {
			resultSet = manager.findEntity("from UserAccountMO where email = '" + email +"'");
			if(resultSet == null) {
				throw new UserNotFoundException("Email não encontrado");
			}
			else return (UserAccount) resultSet.get(0);
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
	}
	
	
	/**
	 * Adiciona um novo usuário.
	 * @param email - Email do usuário.
	 * @param name - Nome do usuário.
	 * @param password - Senha do usuário.
	 * @param profile - Perfil do usuário.
	 * @throws UnreachableDataBaseException
	 * @throws ProfileNotFoundException
	 * @throws NoDefaultProfileException
	 */
	public void addUser(String email, String name, String password, Profile profile) throws UnreachableDataBaseException, ProfileNotFoundException, NoDefaultProfileException {
		ProfileDAO dao = new ProfileDAO();
		UserAccount newUser;
		if(profile == null){
			newUser = new UserAccount(name, dao.getDefaultProfile(), email, password, null, null);
		}
		else{
			newUser = new UserAccount(name, profile, email, password, null, null);
		}
		try {
			manager.saveEntity(newUser);
		} catch(DataAccessLayerException e){
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
	}
	
	/**
	 * Pega todos os usários presente no banco de dados.
	 * @return Lista de DTOs contendo todos os usuários.
	 * @throws UnreachableDataBaseException
	 * @throws UserNotFoundException
	 */
	public List<DTO> listAllUsers() throws UnreachableDataBaseException, UserNotFoundException {
		List<DTO> resultSet;
		try{
			resultSet = null;
			resultSet = manager.findEntity("from UserAccountMO order by name");
			if(resultSet == null)	throw new UserNotFoundException("Nenhum usuário encontrado");
		}catch(DataAccessLayerException e){
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
		return resultSet;
	}
	
	/**
	 * Remove um usuário a partir do email.
	 * @param email - Email do usuário.
	 * @throws UnreachableDataBaseException
	 * @throws UserNotFoundException
	 */
	public void removeUser(String email) throws UnreachableDataBaseException, UserNotFoundException{
		UserAccount check = null;
		try{
			check = findUserByEmail(email);
			manager.deleteEntity(check);
		} catch(DataAccessLayerException e){
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
	}
	
	/**
	 * Troca o perfil de um usuário.
	 * @param email - Email do usuário que terá o perfil trocado.
	 * @param new_profile - Novo perfil do usuário.
	 * @throws IncorrectProfileInformationException
	 * @throws UnreachableDataBaseException
	 * @throws UserNotFoundException
	 * @throws IllegalArgumentException
	 * @throws UpdateEntityException
	 */
	public void changeUserProfile(String email, Profile new_profile) throws IncorrectProfileInformationException, UnreachableDataBaseException, UserNotFoundException, IllegalArgumentException, UpdateEntityException {
		UserAccount check = null;
		try {
			check = findUserByEmail(email);
			if(check == null) throw new UserNotFoundException("Usuário não existe!");
			String old_profile = check.getProfile().getProfile();
			if(old_profile != new_profile.getProfile()) check.setProfile(new_profile);
			else throw new IncorrectProfileInformationException("Perfil já definido para esse usuário, escolha outro.");
			manager.updateEntity(check);
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
	}
	
	/**
	 * Atualiza um dados de um usuário.
	 * @param user - Objeto UserAccount com os dados atualizados.
	 * @throws UnreachableDataBaseException
	 * @throws UserNotFoundException
	 * @throws IllegalArgumentException
	 * @throws UpdateEntityException
	 */
	public void updateUser(UserAccount user) throws UnreachableDataBaseException, UserNotFoundException, IllegalArgumentException, UpdateEntityException{
		if(user.getId() == null){
			user = this.findUserByEmail(user.getEmail());
			if(user == null)	throw new UserNotFoundException("Usuário não encontrado");
		}
		else{
			try{
				manager.updateEntity(user);
			}catch(DataAccessLayerException e){
				e.printStackTrace();
				throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
			}
		}
	}
	
	/**
	 * Lista todos os usuários de um determinado perfil.
	 * @param profileName - Nome do perfil.
	 * @return Lista de DTOs contendo todos os usuários de um determinado perfil.
	 * @throws UnreachableDataBaseException
	 * @throws ProfileNotFoundException
	 * @throws UserNotFoundException
	 */
	public List<DTO> listUsersByProfile(String profileName) throws UnreachableDataBaseException, ProfileNotFoundException, UserNotFoundException {
		List<DTO> resultSet = null;
		ProfileDAO profileDAO = null;
		
		try{
			profileDAO = new ProfileDAO();
			Profile profile = profileDAO.findProfileByName(profileName);
			if(profile == null)	throw new ProfileNotFoundException();
			resultSet = null;
			resultSet = manager.findEntity("from UserAccountMO where profile = '" + profile.getId() + "' order by name");
			if(resultSet == null)	throw new UserNotFoundException("Nenhum usuário encontrado");
		}catch(DataAccessLayerException e){
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
		return resultSet;
	}
	
	public List<DTO> listUsersWithExpiredTokens(String threshold) throws UnreachableDataBaseException, UserNotFoundException{
		List<DTO> resultSet = null;
		try{
			resultSet = manager.findEntity("FROM UserAccountMO WHERE tokendate <= '"+ threshold +"'");
			if(resultSet == null)	throw new UserNotFoundException("Nenhum usuário com chave expirado");
		}catch(DataAccessLayerException e){
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
		return resultSet;
	}
	
}
