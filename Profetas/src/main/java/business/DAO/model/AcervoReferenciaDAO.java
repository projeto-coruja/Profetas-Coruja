package business.DAO.model;

import java.util.List;

import persistence.PersistenceAccess;
import persistence.dto.AcervoReferencia;
import persistence.dto.DTO;
import persistence.util.DataAccessLayerException;
import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.model.DuplicateReferenceException;
import business.exceptions.model.ReferenceNotFoundException;

public class AcervoReferenciaDAO {

	private PersistenceAccess manager;

	public AcervoReferenciaDAO() {
		manager = new PersistenceAccess();	
	}
	
	public AcervoReferencia addReference(String name) throws UnreachableDataBaseException, DuplicateReferenceException {
		if(name.isEmpty() || name == null) throw new IllegalArgumentException("Nome vazio ou nulo.");
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
	
	public void removeReference(String name) throws UnreachableDataBaseException, ReferenceNotFoundException {
		if(name == null) throw new IllegalArgumentException("Nenhum Nome especificado.");
		AcervoReferencia select = null;
		try{
			select = findReferenceByName(name);
			if(select == null) throw new ReferenceNotFoundException ("Referência não encontrada.");
			manager.deleteEntity(select);
		} catch(DataAccessLayerException e){
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}
	
	/*public void removeReference(AcervoReferencia reference) throws UnreachableDataBaseException {
		if(reference == null)	throw new IllegalArgumentException("Nenhum Acervo/Referência especificado.");
		try{
			manager.deleteEntity(reference);
		} catch(DataAccessLayerException e){
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}*/
	
	public void updateReference(String oldName, String newName) throws UnreachableDataBaseException, ReferenceNotFoundException {
		if(oldName.isEmpty() || oldName == null || newName.isEmpty() || newName == null) throw new IllegalArgumentException("Nome vazio ou nulo.");
		AcervoReferencia select = null;
		try{
			select = findReferenceByName(oldName);
			if(select == null) throw new ReferenceNotFoundException ("Referência não encontrada.");
			select.setNome(newName);
			manager.updateEntity(select);
		} catch(DataAccessLayerException e){
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}	
	
	/*public void updateReference(AcervoReferencia reference) throws UnreachableDataBaseException, IllegalArgumentException, UpdateEntityException{
		try{
			manager.updateEntity(reference);
		} catch(DataAccessLayerException e){
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
	}*/	
	
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
