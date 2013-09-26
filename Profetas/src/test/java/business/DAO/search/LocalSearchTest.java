package business.DAO.search;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import persistence.model.EntityModel;
import persistence.model.Local;
import business.DAO.search.LocalSearchDAO;
import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.model.ClassificationNotFoundException;
import business.exceptions.model.LocalNotFoundException;

public class LocalSearchTest {
	
	@Test(expected =LocalNotFoundException.class)
	public void notfindLocalByAlltest() throws LocalNotFoundException, UnreachableDataBaseException {
		LocalSearchDAO dao = new LocalSearchDAO();				
		EntityModel tipo  = (EntityModel) dao.findLocalByAll("local",0,0);	
		System.out.println(tipo.getId());
		
	
	
	}
	@Test
	public void findLocalByAlltest() throws LocalNotFoundException, UnreachableDataBaseException {
		LocalSearchDAO dao = new LocalSearchDAO();				
		EntityModel tipo  = (EntityModel) dao.findLocalByAll("Grecia Central", 38.6, 22.71);	
		System.out.println(tipo.getId());
		
	
	
	}
		
}
	


