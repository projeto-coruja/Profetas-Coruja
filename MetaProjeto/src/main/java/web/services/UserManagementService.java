package web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;

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
	public void addNewUser(User newUser, Errors errors) {
		try {
			pa.saveEntity(newUser);
		} catch (IllegalArgumentException e) {
			errors.reject("object.persist", "Erro interno: IllegalArgumentException.");
			e.printStackTrace();
		} catch (InstantiationException e) {
			errors.reject("object.persist", "Erro interno: InstantiationException.");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			errors.reject("object.persist", "Erro interno: IllegalAccessException.");
			e.printStackTrace();
		} catch (UpdateEntityException e) {
			errors.reject("object.persist", "Erro interno: UpdateEntityException.");
			e.printStackTrace();
		}
	}
	
	@Transactional
	public boolean existingUser(String username) {
		return pa.findEntity("from UserMO where username = '" + username + "'") != null;
	}

}
