package business.DAO.search;

import java.util.List;

import persistence.PersistenceAccess;

import persistence.dto.Classificacao;
import persistence.dto.DTO;
import persistence.util.DataAccessLayerException;
import business.exceptions.model.ClassificationNotFoundException;
import business.exceptions.login.UnreachableDataBaseException;

public class ClassificacaoSearchDAO {
private PersistenceAccess manager;
	
	public ClassificacaoSearchDAO() {
		manager = new PersistenceAccess();
	}
	
	
	
	public Classificacao findExactClassificacao(String tipo) 
			throws  UnreachableDataBaseException, ClassificationNotFoundException  {
		
		List<DTO> resultSet = null;
		try {
			resultSet = manager.findEntity("FROM ClassificacaoMO WHERE tipo = '"+ tipo +"'"
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
	
	public List<DTO> findClassificacaoByTipo(String tipo) throws  UnreachableDataBaseException,ClassificationNotFoundException {
		List<DTO> resultSet = null;
		try {
			resultSet = manager.findEntity("from ClassificacaoMO where tipo like '%" + tipo +"%' "
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
	
	public List<DTO> findClassificacaoById(int id) throws  UnreachableDataBaseException,ClassificationNotFoundException  {
		List<DTO> resultSet = null;
		try {
			resultSet = manager.findEntity("from ClassificacaoMO where id =" + id +"order by id, tipo");
			if(resultSet == null) {
				throw new ClassificationNotFoundException ("Id de Classificacao não encontrado.");
			}
			else return resultSet;
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
	}
	
	public List<DTO> findAllClassificacao() throws  UnreachableDataBaseException, ClassificationNotFoundException  {
		List<DTO> resultSet = null;
		try {
			resultSet = manager.findEntity("from ClassificacaoMO order by tipo");
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
