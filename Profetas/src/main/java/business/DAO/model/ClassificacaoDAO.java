package business.DAO.model;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import business.exceptions.model.ClassificationNotFoundException;
import business.exceptions.model.DuplicateClassificationException;
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
	
	public Classificacao addClassification(String type) throws UnreachableDataBaseException, DuplicateClassificationException {
		Classificacao newClassificacao = new Classificacao(type);
		try {
			findClassificationByType(type);
			throw new DuplicateClassificationException("Classificação já existente.");
		} catch(DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");			
		} catch (ClassificationNotFoundException e) {
			manager.saveEntity(newClassificacao);
			return newClassificacao;
		}
	}
	
	public void removeClassification(String type) throws UnreachableDataBaseException, ClassificationNotFoundException {
		List<DTO> check = null;
		Classificacao select = null;
		try {
			check = findClassificationByType(type);
			for(DTO dto : check) {
				if (((Classificacao) dto).getTipo().equals(type))
					select = (Classificacao) dto;
			}
			if(select == null)	throw new ClassificationNotFoundException("Classificação não encontrada.");
			manager.deleteEntity(select);
		} catch(DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}
	
	public Classificacao updateClassification(String oldType, String newType) 
			throws UnreachableDataBaseException, ClassificationNotFoundException, IllegalAccessException, IllegalArgumentException, 
			InvocationTargetException, NoSuchMethodException, SecurityException, UpdateEntityException {
		List<DTO> check = null;
		Classificacao select = null;
		try{
			check = findClassificationByType(oldType);
			for(DTO dto : check) {
				if (((Classificacao) dto).getTipo().equals(oldType))
					select = (Classificacao) dto;
			}
			try {
				check = findClassificationByType(newType);
				for(DTO dto : check) {
					if (((Classificacao) dto).getTipo().equals(newType))
						throw new IllegalArgumentException("Classificação já existente.");
				}
			} catch (ClassificationNotFoundException e){}

			select.setTipo(newType);

			manager.updateEntity(select);
			return select;
			
		} catch(DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}
	
	public List<DTO> findClassificationByType(String type) throws  UnreachableDataBaseException, ClassificationNotFoundException {
		List<DTO> resultSet = null;
		try {
			resultSet = manager.findEntity("FROM ClassificacaoMO WHERE tipo LIKE '%" + type + "%' ORDER BY tipo");
			if(resultSet == null) {
				throw new ClassificationNotFoundException ("Classificação não encontrada.");
			}
			else return resultSet;
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}
	
	/*public List<DTO> getAllClassification() throws UnreachableDataBaseException, ClassificationNotFoundException {
		List<DTO> resultSet = null;
		try {
			resultSet = manager.findEntity("FROM ClassificacaoMO ORDER BY tipo");
			if(resultSet == null) {
				throw new ClassificationNotFoundException ("Não existe nenhuma classificação cadastrada.");
			}
			else return resultSet;
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}*/	
}
