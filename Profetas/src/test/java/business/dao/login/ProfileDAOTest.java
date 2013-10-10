package business.dao.login;

import persistence.EntityManager;
import persistence.model.Profile;
import persistence.model.exceptions.EntityPersistenceException;
import business.dao.login.ProfileDAO;
import business.exceptions.login.ProfileNotFoundException;
import business.exceptions.login.UnreachableDataBaseException;

public class ProfileDAOTest {

    public static EntityManager em = new EntityManager();

    public static boolean checkPermission(String[] userPermission, String requiredPermission) {
	for (String permission : userPermission) {
	    if (permission.equals(requiredPermission)) {
		return true;
	    }
	}
	return false;
    }

    public static void main(String args[]) throws UnreachableDataBaseException, ProfileNotFoundException, EntityPersistenceException {
	ProfileDAO dao = new ProfileDAO(em);
	new Profile("teste", new String[] { "default", "user", "admin", "master", "legend" }, false)
		.save(em);
	Profile l = dao.findProfileByName("teste");
	l.remove(em);
	for (String s : l.getPermissions()) {
	    System.out.println(s);
	}
	System.out.println(l.getProfile());
	l = dao.findProfileByName("teste");
	System.out.println(l);
    }
}
