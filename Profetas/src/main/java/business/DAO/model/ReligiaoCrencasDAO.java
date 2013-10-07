package business.DAO.model;

import persistence.EntityManager;
import persistence.model.ReligiaoCrencas;
import persistence.util.DataAccessLayerException;
import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.model.DuplicateReligionException;
import business.exceptions.model.ReligionNotFoundException;
import datatype.SimpleDate;

public class ReligiaoCrencasDAO {

	private EntityManager manager;

	public ReligiaoCrencasDAO() {
		manager = new EntityManager();	
	}
	
	public ReligiaoCrencas addReligion(String name, SimpleDate yearBegin, SimpleDate yearEnd, String description) throws UnreachableDataBaseException {
		return addReligion(new ReligiaoCrencas(name, yearBegin, yearEnd, description));
	}
	
	public ReligiaoCrencas addReligion(ReligiaoCrencas religion) throws UnreachableDataBaseException {
		if(religion == null)	throw new IllegalArgumentException("Religiao nula.");
		try {
			manager.save(religion);
		} catch(DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");			
		}
		return religion;
	}
	
	public void removeReligion(ReligiaoCrencas religion) throws UnreachableDataBaseException {
		if(religion == null) throw new IllegalArgumentException("Nenhuma Religião/Crença especificada.");
		try{
			manager.delete(religion);
		} catch(DataAccessLayerException e){
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}
	
	public void removeReligionByName(String name) throws UnreachableDataBaseException, ReligionNotFoundException {
		try {
			ReligiaoCrencas result = findReligionByName(name);
			manager.delete(result);
		} catch(DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}
	
	public void updateReligion(ReligiaoCrencas religion) throws UnreachableDataBaseException, DuplicateReligionException  {		
		if(religion == null) throw new IllegalArgumentException("Nenhuma Religião/Crença especificada.");
		try { 
			manager.update(religion);
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}
	
	public ReligiaoCrencas findReligionByName(String religionName) throws  UnreachableDataBaseException, ReligionNotFoundException {
		ReligiaoCrencas resultSet = null;
		try {
			resultSet = manager.findByNaturalId(ReligiaoCrencas.class, religionName);
			if(resultSet == null) {
				throw new ReligionNotFoundException ("Religião/Crença não encontrada.");
			}
			else return resultSet;
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}
	
	
	
		
}
