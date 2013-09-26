package business.DAO.model;

import java.util.List;

import persistence.EntityManager;
import persistence.model.Classificacao;
import persistence.model.IdentifiedEntity;
import persistence.util.DataAccessLayerException;
import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.model.ClassificationNotFoundException;
import business.exceptions.model.DuplicateClassificationException;

public class ClassificacaoDAO {

	private EntityManager manager;

	public ClassificacaoDAO() {
		manager = new EntityManager();	
	}
	
	public Classificacao addClassification(String type) throws UnreachableDataBaseException, DuplicateClassificationException {
		if(type.isEmpty() || type == null)	throw new IllegalArgumentException("Tipo vazio ou nulo.");
		Classificacao newClassificacao = new Classificacao(type);
		try {
			findClassificationByType(type);
			throw new DuplicateClassificationException("Classificação já existente.");
		} catch(DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");			
		} catch (ClassificationNotFoundException e) {
			manager.save(newClassificacao);
			return newClassificacao;
		}
	}
	
	public void removeClassification(String type) throws UnreachableDataBaseException, ClassificationNotFoundException {
		if(type.isEmpty() || type == null) throw new IllegalArgumentException("Tipo vazio ou nulo.");
		List<IdentifiedEntity> check = null;
		Classificacao select = null;
		try {
			check = findClassificationByType(type);
			for(IdentifiedEntity dto : check) {
				if (((Classificacao) dto).getTipo().equals(type))
					select = (Classificacao) dto;
			}
			if(select == null)	throw new ClassificationNotFoundException("Classificação não encontrada.");
			manager.delete(select);
		} catch(DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}
	
	public Classificacao updateClassification(String oldType, String newType) throws UnreachableDataBaseException, ClassificationNotFoundException {
		if(oldType.isEmpty() || oldType == null || newType.isEmpty() || newType == null)	throw new IllegalArgumentException("Tipo vazio ou nulo.");
		List<IdentifiedEntity> check = null;
		Classificacao select = null;
		try{
			check = findClassificationByType(oldType);
			for(IdentifiedEntity dto : check) {
				if (((Classificacao) dto).getTipo().equals(oldType))
					select = (Classificacao) dto;
			}
			try {
				check = findClassificationByType(newType);
				for(IdentifiedEntity dto : check) {
					if (((Classificacao) dto).getTipo().equals(newType))
						throw new IllegalArgumentException("Classificação já existente.");
				}
			} catch (ClassificationNotFoundException e){}

			select.setTipo(newType);

			manager.update(select);
			return select;
			
		} catch(DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}
	
	public List<IdentifiedEntity> findClassificationByType(String type) throws  UnreachableDataBaseException, ClassificationNotFoundException {
		List<IdentifiedEntity> resultSet = null;
		try {
			resultSet = manager.find("FROM Classificacao WHERE tipo LIKE '%" + type + "%' ORDER BY tipo");
			if(resultSet == null) {
				throw new ClassificationNotFoundException ("Classificação não encontrada.");
			}
			else return resultSet;
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}
	
	public List<IdentifiedEntity> getAllClassification() throws UnreachableDataBaseException, ClassificationNotFoundException {
		List<IdentifiedEntity> resultSet = null;
		try {
			resultSet = manager.find("FROM Classificacao ORDER BY tipo");
			if(resultSet == null) {
				throw new ClassificationNotFoundException ("Não existe nenhuma classificação cadastrada.");
			}
			else return resultSet;
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}
}
