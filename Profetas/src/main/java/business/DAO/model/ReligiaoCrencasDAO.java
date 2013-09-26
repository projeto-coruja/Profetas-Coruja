package business.DAO.model;

import java.util.List;

import persistence.EntityManager;
import persistence.model.IdentifiedEntity;
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
		ReligiaoCrencas newReligiaoCrencas = new ReligiaoCrencas(name, yearBegin, yearEnd, description);
		try {
			manager.save(newReligiaoCrencas);
		} catch(DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");			
		}
			return newReligiaoCrencas;
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
		List<IdentifiedEntity> check = null;
		ReligiaoCrencas select = null;
		try {
			check = findReligionByName(name);
			for(IdentifiedEntity dto : check){
				if (((ReligiaoCrencas) dto).getNome().equals(name))
					select = (ReligiaoCrencas) dto;
			}
			if(select == null) throw new ReligionNotFoundException("Religião/Crença não encontrada.");
			manager.delete(select);
		} catch(DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}
	
	public void updateReligion(ReligiaoCrencas religion) throws UnreachableDataBaseException, DuplicateReligionException  {		
		if(religion == null) throw new IllegalArgumentException("Nenhuma Religião/Crença especificada.");
		try { 
			if(religion.getId() == null) addReligion(religion.getNome(), religion.getAnoInicio(), religion.getAnoFim(), religion.getDescricao());
			else manager.update(religion);
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}
	
	public List<IdentifiedEntity> findReligionByName(String religionName) throws  UnreachableDataBaseException, ReligionNotFoundException {
		List<IdentifiedEntity> resultSet = null;
		try {
			resultSet = manager.find("FROM ReligiaoCrencas WHERE nome LIKE '%" + religionName + "%' ORDER BY nome");
			if(resultSet == null) {
				throw new ReligionNotFoundException ("Religião/Crença não encontrada.");
			}
			else return resultSet;
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}
	
	public ReligiaoCrencas findSingleReligionByName(String religionName) throws  UnreachableDataBaseException, ReligionNotFoundException {
		List<IdentifiedEntity> resultSet = null;
		try {
			resultSet = manager.find("FROM ReligiaoCrencas WHERE nome LIKE '%" + religionName + "%' ORDER BY nome");
			if(resultSet == null) {
				throw new ReligionNotFoundException ("Religião/Crença não encontrada.");
			}
			else return (ReligiaoCrencas)resultSet.get(0);
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}
	
	public List<IdentifiedEntity> getAllReligions() throws  UnreachableDataBaseException, ReligionNotFoundException {
		List<IdentifiedEntity> resultSet = null;
		try {
			resultSet = manager.find("FROM ReligiaoCrencas ORDER BY nome");
			if(resultSet == null) {
				throw new ReligionNotFoundException ("Não existe nenhuma religião/crença cadastrada.");
			}
			else return resultSet;
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}	
}
