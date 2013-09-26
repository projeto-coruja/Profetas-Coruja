package business.DAO.search;

import java.util.List;

import persistence.EntityManager;
import persistence.model.Classificacao;
import persistence.model.EntityModel;
import persistence.util.DataAccessLayerException;
import business.exceptions.model.ClassificationNotFoundException;
import business.exceptions.login.UnreachableDataBaseException;

public class ClassificacaoSearchDAO {
	
	private EntityManager manager;
	
	public ClassificacaoSearchDAO() {
		manager = new EntityManager();
	}
	
	public Classificacao findExactClassificacao(String tipo) 
			throws  UnreachableDataBaseException, ClassificationNotFoundException  {
		List<EntityModel> resultSet = null;
		try {
			resultSet = manager.find("FROM Classificacao WHERE tipo = '"+ tipo +"'"
					+ " ORDER BY id, tipo");
			if(resultSet == null) {
				throw new ClassificationNotFoundException ("Classificacao não encontrada.");
			}
			else return (Classificacao) resultSet.get(0);
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
	}
	
	public List<EntityModel> findClassificacaoByTipo(String tipo) throws  UnreachableDataBaseException, ClassificationNotFoundException {
		List<EntityModel> resultSet = null;
		try {
			resultSet = manager.find("from ClassificacaoMO where tipo like '%" + tipo +"%' "
					+ "order by id, tipo");
			
			if(resultSet == null) {
				throw new ClassificationNotFoundException ("Classificacao não encontrada.");
			}
			else return resultSet;
		
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
	}
	
	public List<EntityModel> findClassificacaoById(int id) throws  UnreachableDataBaseException,ClassificationNotFoundException  {
		List<EntityModel> resultSet = null;
		try {
			resultSet = manager.find("from ClassificacaoMO where id =" + id +"order by id, tipo");
			if(resultSet == null) {
				throw new ClassificationNotFoundException ("Id de Classificacao não encontrado.");
			}
			else return resultSet;
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
	}
	
	public List<EntityModel> findAllClassificacao() throws  UnreachableDataBaseException, ClassificationNotFoundException  {
		List<EntityModel> resultSet = null;
		try {
			resultSet = manager.find("from ClassificacaoMO order by tipo");
			if(resultSet == null) {
				throw new ClassificationNotFoundException("Não existe nenhuma Classificacao cadastrada.");
			}
			else return resultSet;
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
	}

}
