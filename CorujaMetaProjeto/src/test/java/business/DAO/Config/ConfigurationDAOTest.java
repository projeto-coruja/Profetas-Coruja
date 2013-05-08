package business.DAO.Config;

import persistence.dto.Configuration;
import business.exceptions.general.ConfigNotFoundException;
import business.exceptions.login.UnreachableDataBaseException;

public class ConfigurationDAOTest {
	public static void main(String[] args) throws UnreachableDataBaseException, ConfigNotFoundException {
		ConfigurationDAO d = new ConfigurationDAO();
		d.addPropertie("email", "lalala@lalala.com");
		Configuration c;
		c = d.getEntry("email");
		d.removeEntry("email");
		System.out.println(c.getValue());
	}

}
