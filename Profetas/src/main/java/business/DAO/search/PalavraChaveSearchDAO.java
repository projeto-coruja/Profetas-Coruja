package business.DAO.search;

import java.util.List;

import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.model.KeywordNotFoundException;
import persistence.EntityManager;
import persistence.model.IdentifiedEntity;
import persistence.model.PalavraChave;
import persistence.util.DataAccessLayerException;

public class PalavraChaveSearchDAO {

	private EntityManager manager;

	public PalavraChaveSearchDAO() {
		manager = new EntityManager();
	}

	public PalavraChave findExactPalavraChave(String nome) throws UnreachableDataBaseException, KeywordNotFoundException  {
		try {
			PalavraChave result = manager.find(PalavraChave.class, nome);
			if(result == null) {
				throw new KeywordNotFoundException ("PalavraChave não encontrada.");
			}
			else return result;
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
	}

	public List<PalavraChave> findPalavraChaveByNome(String nome) throws  UnreachableDataBaseException, KeywordNotFoundException {
		try {
			List<PalavraChave> resultSet = manager.find("from PalavraChave where palavrachave like '%" + nome + "%' "
					+ "order by palavrachave");
			if(resultSet.isEmpty()) {
				throw new KeywordNotFoundException ("PalavraChave não encontrada.");
			}
			else return resultSet;
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
	}

	public List<IdentifiedEntity> findAllPalavraChave() throws  UnreachableDataBaseException, KeywordNotFoundException  {
		List<IdentifiedEntity> resultSet = null;
		try {
			resultSet = manager.find("from PalavraChave order by nome");
			if(resultSet == null) {
				throw new KeywordNotFoundException("Não existe nenhuma PalavraChave cadastrada.");
			}
			else return resultSet;
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
	}

}


