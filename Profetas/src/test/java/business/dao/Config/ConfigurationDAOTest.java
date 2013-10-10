package business.dao.Config;

import persistence.EntityManager;
import persistence.model.Configuration;
import persistence.model.exceptions.EntityPersistenceException;
import business.dao.config.ConfigurationDAO;
import business.exceptions.general.DuplicatedEntryException;
import business.exceptions.login.UnreachableDataBaseException;

public class ConfigurationDAOTest {

    public static EntityManager em = new EntityManager();

    public static void main(String[] args) throws UnreachableDataBaseException, DuplicatedEntryException, EntityPersistenceException {
	Configuration b = new Configuration("email", "lalala@lalala.com");
	b.save(em);

	ConfigurationDAO cfgdao = new ConfigurationDAO(em);
	Configuration c = cfgdao.getEntry("email");
	System.out.println(c.getValue());
    }

}
