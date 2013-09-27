package business.DAO.search;

import java.util.List;

import persistence.EntityManager;
import persistence.model.Classificacao;
import persistence.model.IdentifiedEntity;
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
		try {
			Classificacao result = manager.find(Classificacao.class, tipo);
			if(result == null) {
				throw new ClassificationNotFoundException ("Classificacao não encontrada.");
			}
			return result;
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
	}
	
	public List<IdentifiedEntity> findAllClassificacao() throws  UnreachableDataBaseException, ClassificationNotFoundException  {
		List<IdentifiedEntity> resultSet = null;
		try {
			resultSet = manager.find("from Classificacao order by tipo");
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
