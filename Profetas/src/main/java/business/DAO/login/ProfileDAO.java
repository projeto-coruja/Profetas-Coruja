package business.DAO.login;

import java.util.List;

import persistence.PersistenceAccess;
import persistence.dto.DTO;
import persistence.dto.Profile;
import persistence.util.DataAccessLayerException;
import business.exceptions.login.NoDefaultProfileException;
import business.exceptions.login.ProfileNotFoundException;
import business.exceptions.login.UnreachableDataBaseException;

public class ProfileDAO {

	private PersistenceAccess manager;
	
	public ProfileDAO() {
		manager = new PersistenceAccess();
	}
	
	public Profile getDefaultProfile() throws UnreachableDataBaseException, NoDefaultProfileException {
		PersistenceAccess manager;
		manager = new PersistenceAccess();
		List<DTO> resultSet = null;
		try {
			resultSet = manager.findEntity("FROM ProfileMO WHERE isDefault = 'TRUE'");
			if(resultSet == null) {
				throw new NoDefaultProfileException();
			}
			else return (Profile) resultSet.get(0);
		} catch(DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
	}
	
	public void createProfile(String profile) throws UnreachableDataBaseException{
		Profile newProfile = new Profile(profile, new String[]{"default"}, false);
		try {
			manager.saveEntity(newProfile);
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
	}
	
	public void createProfile(String profile, String[] permissions) throws UnreachableDataBaseException{
		Profile newProfile = new Profile(profile, permissions, false);
		try {
			manager.saveEntity(newProfile);
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
	}
	
	public void removeProfile(String profile) throws UnreachableDataBaseException, ProfileNotFoundException{
		List<DTO> resultSet = null;
		try {
			resultSet = manager.findEntity("from ProfileMO where profile = '" + profile + "'");
			if(resultSet == null) {
				throw new ProfileNotFoundException();
			}
			else{
				for(DTO check : resultSet){
					if(((Profile)check).getProfile().equals(profile))	manager.deleteEntity(check);
				}
			}
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
	}
	
	public void updateProfile(String profile, String[] permissions, boolean isDefault) throws ProfileNotFoundException, UnreachableDataBaseException{
		Profile check;
		check = findProfileByName(profile);
		if(permissions != null && permissions.length > 0)	check.setPermissions(permissions);
		if((Object)isDefault != null)	check.setDefault(isDefault);
		manager.updateEntity(check);
	}
	
	public Profile findProfileByName(String profile) throws UnreachableDataBaseException, ProfileNotFoundException {
		List<DTO> resultSet = null;
		try {
			resultSet = manager.findEntity("from ProfileMO where profile = '" + profile + "'");
			if(resultSet == null) {
				throw new ProfileNotFoundException();
			}
			else return (Profile) resultSet.get(0);
		} catch(DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
	}
	
	public List<DTO> getAllProfiles() throws UnreachableDataBaseException, ProfileNotFoundException{
		List<DTO> resultSet = null;
		try{
			resultSet = manager.findEntity("from ProfileMO order by profile");
			if(resultSet == null)	throw new ProfileNotFoundException("Nenhum perfil encontrado");
			else return resultSet;
		} catch(DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
	}
	
}
