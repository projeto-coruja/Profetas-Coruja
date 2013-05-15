package business.DAO.model;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import datatype.SimpleDate;

import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.model.DuplicateReligionException;
import business.exceptions.model.ReligionNotFoundException;
import persistence.PersistenceAccess;
import persistence.dto.DTO;
import persistence.dto.ReligiaoCrencas;
import persistence.exceptions.UpdateEntityException;
import persistence.util.DataAccessLayerException;

public class ReligiaoCrencasDAO {

	private PersistenceAccess manager;

	public ReligiaoCrencasDAO() {
		manager = new PersistenceAccess();	
	}
	
	public ReligiaoCrencas addReligion(String name, SimpleDate yearBegin, SimpleDate yearEnd, String description) throws UnreachableDataBaseException, DuplicateReligionException {
		ReligiaoCrencas newReligiaoCrencas = new ReligiaoCrencas(name, yearBegin, yearEnd, description);
		try {
			findReligionByName(name);
			throw new DuplicateReligionException("Religião/Crença já existente.");
		} catch(DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");			
		} catch (ReligionNotFoundException e) {
			manager.saveEntity(newReligiaoCrencas);
			return newReligiaoCrencas;
		}
	}
	
	public void removeReligion(ReligiaoCrencas religion) throws UnreachableDataBaseException {
		if(religion == null) throw new IllegalArgumentException("Nenhuma Religião/Crença especificada.");
		try{
			manager.deleteEntity(religion);
		} catch(DataAccessLayerException e){
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}
	
	public void removeReligionByName(String name) throws UnreachableDataBaseException, ReligionNotFoundException {
		List<DTO> check = null;
		ReligiaoCrencas select = null;
		try {
			check = findReligionByName(name);
			for(DTO dto : check){
				if (((ReligiaoCrencas) dto).getNome().equals(name))
					select = (ReligiaoCrencas) dto;
			}
			if(select == null) throw new ReligionNotFoundException("Religião/Crença não encontrada.");
			manager.deleteEntity(select);
		} catch(DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}
	
	public void updateReligion(ReligiaoCrencas religion) 
			throws UnreachableDataBaseException, IllegalAccessException, IllegalArgumentException, 
			InvocationTargetException, NoSuchMethodException, SecurityException, UpdateEntityException, DuplicateReligionException {
		
		if(religion == null) throw new IllegalArgumentException("Nenhuma Religião/Crença especificada.");
		try { 
			if(religion.getId() == null) addReligion(religion.getNome(), religion.getAnoInicio(), religion.getAnoFim(), religion.getDescricao());
			else manager.updateEntity(religion);
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}
	
	public List<DTO> findReligionByName(String religionName) throws  UnreachableDataBaseException, ReligionNotFoundException {
		List<DTO> resultSet = null;
		try {
			resultSet = manager.findEntity("FROM ReligiaoCrencasMO WHERE nome LIKE '%" + religionName + "%' ORDER BY nome");
			if(resultSet == null) {
				throw new ReligionNotFoundException ("Religião/Crença não encontrada.");
			}
			else return resultSet;
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}
	
	public List<DTO> getAllReligions() throws  UnreachableDataBaseException, ReligionNotFoundException {
		List<DTO> resultSet = null;
		try {
			resultSet = manager.findEntity("FROM ReligiaoCrencasMO ORDER BY nome");
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
