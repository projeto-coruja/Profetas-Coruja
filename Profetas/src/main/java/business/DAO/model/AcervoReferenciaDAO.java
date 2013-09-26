package business.DAO.model;

import java.util.List;

import persistence.EntityManager;
import persistence.model.AcervoReferencia;
import persistence.model.IdentifiedEntity;
import persistence.util.DataAccessLayerException;
import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.model.DuplicateReferenceException;
import business.exceptions.model.ReferenceNotFoundException;

public class AcervoReferenciaDAO {

	private EntityManager manager;

	public AcervoReferenciaDAO() {
		manager = new EntityManager();	
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
			manager.save(newAcervoReferencia);
			return newAcervoReferencia;
		}
	}
	
	public void removeReference(String name) throws UnreachableDataBaseException, ReferenceNotFoundException {
		if(name == null) throw new IllegalArgumentException("Nenhum Nome especificado.");
		AcervoReferencia select = null;
		try{
			select = findReferenceByName(name);
			if(select == null) throw new ReferenceNotFoundException ("Referência não encontrada.");
			manager.delete(select);
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
			manager.update(select);
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
		List<IdentifiedEntity> resultSet = null;
		try {
			resultSet = manager.find("FROM AcervoReferencia WHERE nome LIKE '%" + name + "%' ORDER BY nome");
			if(resultSet == null) {
				throw new ReferenceNotFoundException ("Referência não encontrada.");
			}
			else return (AcervoReferencia) resultSet.get(0);
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}
	
	public List<IdentifiedEntity> getAllReferences() throws  UnreachableDataBaseException, ReferenceNotFoundException  {
		List<IdentifiedEntity> resultSet = null;
		try {
			resultSet = manager.find("FROM AcervoReferencia ORDER BY nome");
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
