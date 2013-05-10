package business.DAO.model;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import business.exceptions.model.ClassificationNotFoundException;
import business.exceptions.login.UnreachableDataBaseException;
import persistence.PersistenceAccess;
import persistence.dto.Classificacao;
import persistence.dto.DTO;
import persistence.exceptions.UpdateEntityException;
import persistence.util.DataAccessLayerException;

public class ClassificacaoDAO {

	private PersistenceAccess manager;

	public ClassificacaoDAO() {
		manager = new PersistenceAccess();	
	}
	
	public Classificacao addClassification(String type) throws UnreachableDataBaseException{
		Classificacao newClassificacao = new Classificacao(type);
		try{
			manager.saveEntity(newClassificacao);
		}catch(DataAccessLayerException e){
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");			
		}
		return newClassificacao;
	}
	
	public Classificacao updateClassification(String oldType, String newType) 
			throws UnreachableDataBaseException, ClassificationNotFoundException, IllegalAccessException, IllegalArgumentException, 
			InvocationTargetException, NoSuchMethodException, SecurityException, UpdateEntityException {
		List<DTO> check = null;
		Classificacao select = null;
		try{
			check = findClassification(oldType);
			for(DTO dto : check){
				if (((Classificacao) dto).getTipo().equals(oldType))
					select = (Classificacao) dto;
			}
			try{
				check = findClassification(newType);
				for(DTO dto : check){
					if (((Classificacao) dto).getTipo().equals(newType))
						throw new IllegalArgumentException("Classificação já existente.");
				}
			} catch (ClassificationNotFoundException e){}

			select.setTipo(newType);

			manager.updateEntity(select);
			return select;
			
		} catch(DataAccessLayerException e){
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}
	
	public void removeClassification(String type) throws UnreachableDataBaseException, ClassificationNotFoundException {
		List<DTO> check = null;
		Classificacao select = null;
		try{
			check = findClassification(type);
			for(DTO dto : check){
				if (((Classificacao) dto).getTipo().equals(type))
					select = (Classificacao) dto;
			}
			if(select == null)	throw new ClassificationNotFoundException("Classificação não encontrada.");
			manager.deleteEntity(select);
		} catch(DataAccessLayerException e){
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}
	
	public List<DTO> findClassification(String type) throws  UnreachableDataBaseException, ClassificationNotFoundException  {
		List<DTO> resultSet = null;
		try {
			resultSet = manager.findEntity("from ClassificacaoMO where tipo like '%" + type + "%' order by tipo");
			if(resultSet == null) {
				throw new ClassificationNotFoundException ("Classificação não encontrada.");
			}
			else return resultSet;
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}
	
	/*public List<DTO> getAllClassification() throws  UnreachableDataBaseException, ClassificationNotFoundException  {
		List<DTO> resultSet = null;
		try {
			resultSet = manager.findEntity("from ClassificacaoMO order by tipo");
			if(resultSet == null) {
				throw new ClassificationNotFoundException ("Nenhum classificação encontrada.");
			}
			else return resultSet;
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}*/
	
}
