package business.DAO.model;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import business.exceptions.model.DuplicateReferenceException;
import business.exceptions.model.ReferenceNotFoundException;
import business.exceptions.login.UnreachableDataBaseException;
import persistence.PersistenceAccess;
import persistence.dto.AcervoReferencia;
import persistence.dto.Autor;
import persistence.dto.DTO;
import persistence.exceptions.UpdateEntityException;
import persistence.util.DataAccessLayerException;

public class AcervoReferenciaDAO {

	private PersistenceAccess manager;

	public AcervoReferenciaDAO() {
		manager = new PersistenceAccess();	
	}
	
	public AcervoReferencia addReference(String name) throws UnreachableDataBaseException, DuplicateReferenceException {
		AcervoReferencia newAcervoReferencia = new AcervoReferencia(name);
		try {
			findReferenceByName(name);
			throw new DuplicateReferenceException("Referência já existente.");
		} catch(DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");			
		} catch (ReferenceNotFoundException e) {
			manager.saveEntity(newAcervoReferencia);
			return newAcervoReferencia;
		}
	}
	
	public void removeReference(AcervoReferencia reference) throws UnreachableDataBaseException {
		if(reference == null)	throw new IllegalArgumentException("Nenhum Acervo/Referência especificado.");
		try{
			manager.deleteEntity(reference);
		} catch(DataAccessLayerException e){
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}
	
	public AcervoReferencia updateReference(AcervoReferencia reference) throws UnreachableDataBaseException, IllegalArgumentException, UpdateEntityException{
		try{
			manager.updateEntity(reference);
		} catch(DataAccessLayerException e){
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
	}	
	
	public AcervoReferencia findReferenceByName(String name) throws  UnreachableDataBaseException, ReferenceNotFoundException {
		List<DTO> resultSet = null;
		try {
			resultSet = manager.findEntity("FROM AcervoReferenciaMO WHERE nome LIKE '%" + name + "%' ORDER BY nome");
			if(resultSet == null) {
				throw new ReferenceNotFoundException ("Referência não encontrada.");
			}
			else return (AcervoReferencia) resultSet.get(0);
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}
	
	public List<DTO> getAllReferences() throws  UnreachableDataBaseException, ReferenceNotFoundException  {
		List<DTO> resultSet = null;
		try {
			resultSet = manager.findEntity("FROM AcervoReferenciaMO ORDER BY nome");
			if(resultSet == null) {
				throw new ReferenceNotFoundException ("Não existe nenhuma referência cadastrada.");
			}
			else return resultSet;
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}
}
