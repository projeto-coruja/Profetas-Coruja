package business.DAO.login;

import java.util.List;

import persistence.EntityManager;
import persistence.model.IdentifiedEntity;
import persistence.model.Profile;
import persistence.util.DataAccessLayerException;
import business.exceptions.login.NoDefaultProfileException;
import business.exceptions.login.ProfileNotFoundException;
import business.exceptions.login.UnreachableDataBaseException;

/**
 * DAO dos perfils de usuário.
 * 
 */
public class ProfileDAO {

	private EntityManager manager;
	
	public ProfileDAO() {
		manager = new EntityManager();
	}
	
	/**
	 * Busca o profile padrão.
	 * @return Perfil padrão.
	 * @throws UnreachableDataBaseException
	 * @throws NoDefaultProfileException
	 */
	public Profile getDefaultProfile() throws UnreachableDataBaseException, NoDefaultProfileException {
		EntityManager manager;
		manager = new EntityManager();
		List<Profile> resultSet = null;
		try {
			resultSet = manager.find("FROM Profile WHERE isDefault = 'TRUE'");
			if(resultSet.isEmpty()) {
				throw new NoDefaultProfileException();
			}
			else return resultSet.get(0);
		} catch(DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
	}
	
	/**
	 * Cria um novo perfil de usuário baseado no perfil padrão.<br>
	 * Se o perfil padrão não existe, o perfil novo não conterá nenhuma permissão.
	 * @param profile - Nome do perfil.
	 * @throws UnreachableDataBaseException
	 */
	public void createProfile(String profile) throws UnreachableDataBaseException{
		Profile newProfile;
		try {
			Profile def = getDefaultProfile();
			newProfile = new Profile(profile, def.getPermissions(), false);
			manager.save(newProfile);
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		} catch (NoDefaultProfileException e) {
			newProfile = new Profile(profile, new String[]{}, false);
			manager.save(newProfile);
		}
	}
	
	/**
	 * Cria um novo perfil com permissões pré-atribuidos.
	 * @param profile - Nome do perfil.
	 * @param permissions - <b>String[]</b> contendo todas as permissões do perfil.
	 * @throws UnreachableDataBaseException
	 */
	public void createProfile(String profile, String[] permissions) throws UnreachableDataBaseException{
		Profile newProfile = new Profile(profile, permissions, false);
		try {
			manager.save(newProfile);
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
	}
	
	/**
	 * Remove um perfil de usuário.
	 * @param profile - Nome do perfil.
	 * @throws UnreachableDataBaseException
	 * @throws ProfileNotFoundException
	 */
	public void removeProfile(String profile) throws UnreachableDataBaseException, ProfileNotFoundException{
		try {
			Profile result = manager.find(Profile.class, profile);
			if(result == null) {
				throw new ProfileNotFoundException();
			}
			else manager.delete(result);
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
	}
	
	/**
	 * Atualiza um perfil de usuário.
	 * @param profile - Nome do perfil.
	 * @param permissions - <b>String[]</b> contendo todas as permissões atribuidos ao perfil.
	 * @param isDefault - TRUE se o perfil for o perfil padrão, FALSE caso contrário.
	 * @throws ProfileNotFoundException
	 * @throws UnreachableDataBaseException
	 */
	public void updateProfile(String profile, String[] permissions, boolean isDefault) throws ProfileNotFoundException, UnreachableDataBaseException{
		Profile check;
		check = findProfileByName(profile);
		if(permissions != null && permissions.length > 0)	check.setPermissions(permissions);
		if((Object)isDefault != null)	check.setDefault(isDefault);
		manager.update(check);
	}
	
	/**
	 * Busca um perfil a partir do nome.
	 * @param profile - Nome do perfil
	 * @return
	 * @throws UnreachableDataBaseException
	 * @throws ProfileNotFoundException
	 */
	public Profile findProfileByName(String profile) throws UnreachableDataBaseException, ProfileNotFoundException {
		try {
			Profile result = manager.find(Profile.class, profile);
			if(result == null) {
				throw new ProfileNotFoundException();
			}
			return result;
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
	}
	
	/**
	 * Pega todos os perfils presente no banco de dados.
	 * @return Lista de DTOs contendo todos os perfils.
	 * @throws UnreachableDataBaseException
	 * @throws ProfileNotFoundException
	 */
	public List<IdentifiedEntity> getAllProfiles() throws UnreachableDataBaseException, ProfileNotFoundException{
		List<IdentifiedEntity> resultSet = null;
		try{
			resultSet = manager.find("from ProfileMO order by profile");
			if(resultSet == null)	throw new ProfileNotFoundException("Nenhum perfil encontrado");
			else return resultSet;
		} catch(DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
	}
	
}
