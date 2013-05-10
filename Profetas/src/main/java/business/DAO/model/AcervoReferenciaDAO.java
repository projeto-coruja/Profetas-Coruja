package business.DAO.model;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import business.exceptions.model.ReferenceNotFoundException;
import business.exceptions.login.UnreachableDataBaseException;
import persistence.PersistenceAccess;
import persistence.dto.AcervoReferencia;
import persistence.dto.DTO;
import persistence.exceptions.UpdateEntityException;
import persistence.util.DataAccessLayerException;

public class AcervoReferenciaDAO {

	private PersistenceAccess manager;

	public AcervoReferenciaDAO() {
		manager = new PersistenceAccess();	
	}
	
	public AcervoReferencia addReference(String name) throws UnreachableDataBaseException{
		AcervoReferencia newAcervoReferencia = new AcervoReferencia(name);
		try{
			manager.saveEntity(newAcervoReferencia);
		}catch(DataAccessLayerException e){
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");			
		}
		return newAcervoReferencia;
	}
	
	public AcervoReferencia updateReference(String oldName, String newName) 
			throws UnreachableDataBaseException, ReferenceNotFoundException, IllegalAccessException, IllegalArgumentException, 
			InvocationTargetException, NoSuchMethodException, SecurityException, UpdateEntityException {
		List<DTO> check = null;
		AcervoReferencia select = null;
		try{
			check = findReference(oldName);
			for(DTO dto : check){
				if (((AcervoReferencia) dto).getNome().equals(oldName))
					select = (AcervoReferencia) dto;
			}
			try{
				check = findReference(newName);
				for(DTO dto : check){
					if (((AcervoReferencia) dto).getNome().equals(newName))
						throw new IllegalArgumentException("Referência já existente.");
				}
			} catch (ReferenceNotFoundException e){}

			select.setNome(newName);

			manager.updateEntity(select);
			return select;
			
		} catch(DataAccessLayerException e){
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}
	
	public void removeReference(String name) throws UnreachableDataBaseException, ReferenceNotFoundException {
		List<DTO> check = null;
		AcervoReferencia select = null;
		try{
			check = findReference(name);
			for(DTO dto : check){
				if (((AcervoReferencia) dto).getNome().equals(name))
					select = (AcervoReferencia) dto;
			}
			if(select == null)	throw new ReferenceNotFoundException("Referência não encontrada.");
			manager.deleteEntity(select);
		} catch(DataAccessLayerException e){
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}
	
	public List<DTO> findReference(String name) throws  UnreachableDataBaseException, ReferenceNotFoundException  {
		List<DTO> resultSet = null;
		try {
			resultSet = manager.findEntity("from AcervoReferenciaMO where nome like '%" + name + "%' order by nome");
			if(resultSet == null) {
				throw new ReferenceNotFoundException ("Referência não encontrada.");
			}
			else return resultSet;
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}
	
	/*public List<DTO> getAllReferences() throws  UnreachableDataBaseException, ReferenceNotFoundException  {
		List<DTO> resultSet = null;
		try {
			resultSet = manager.findEntity("from AcervoReferenciaMO order by nome");
			if(resultSet == null) {
				throw new ThemeNotFoundException ("Nenhum referência encontrada.");
			}
			else return resultSet;
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}*/
	
}
