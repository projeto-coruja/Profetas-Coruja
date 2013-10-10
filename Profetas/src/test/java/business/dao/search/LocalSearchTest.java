package business.dao.search;

import org.junit.Test;

import persistence.model.exceptions.LocalNotFoundException;
import business.dao.search.LocalSearchDAO;
import business.exceptions.login.UnreachableDataBaseException;

public class LocalSearchTest {

    @Test(expected = LocalNotFoundException.class)
    public void notfindLocalByAlltest() throws LocalNotFoundException, UnreachableDataBaseException {
	LocalSearchDAO dao = new LocalSearchDAO();
	IdentifiedEntity tipo = (IdentifiedEntity) dao.findLocalByAll("local", 0, 0);
	System.out.println(tipo.getId());

    }

    @Test
    public void findLocalByAlltest() throws LocalNotFoundException, UnreachableDataBaseException {
	LocalSearchDAO dao = new LocalSearchDAO();
	IdentifiedEntity tipo = (IdentifiedEntity) dao
		.findLocalByAll("Grecia Central", 38.6, 22.71);
	System.out.println(tipo.getId());

    }

}
