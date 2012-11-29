package web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import persistence.dto.Group;
import persistence.dto.User;
import persistence.util.PersistenceAccess;
import persistence.util.UpdateEntityException;

@Service
public class UserManagementService {

	@Autowired
	private PersistenceAccess pa;
	
	@Transactional
	public Group getGroup(String name) {
		return (Group) pa.findEntity("from GroupMO where groupname = '" + name + "'").get(0);
	}
	
	@Transactional
	public String addNewUser(User newUser) {
		String result = "Erro interno: ";
		try {
			pa.saveEntity(newUser);
			result = "";
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			result += e.toString();
		} catch (InstantiationException e) {
			e.printStackTrace();
			result += e.toString();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			result += e.toString();
		} catch (UpdateEntityException e) {
			e.printStackTrace();
			result += e.toString();
		}
		
		return result;
	}
	
	@Transactional
	public boolean existingUser(String username) {
		return pa.findEntity("from UserMO where username = '" + username + "'") != null;
	}

}
