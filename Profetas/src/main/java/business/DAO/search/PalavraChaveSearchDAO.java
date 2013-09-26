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



	public  PalavraChave findExactPalavraChave(String nome) 
			throws  UnreachableDataBaseException, KeywordNotFoundException  {

		List<IdentifiedEntity> resultSet = null;
		try {
			resultSet = manager.find("FROM PalavraChave WHERE tipo = '"+ nome +"'"
					+ " ORDER BY id, tipo");
			if(resultSet == null) {
				throw new KeywordNotFoundException ("PalavraChave não encontrada.");
			}
			else return (PalavraChave) resultSet.get(0);
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
	}

	public List<IdentifiedEntity> findPalavraChaveByNome(String nome) throws  UnreachableDataBaseException, KeywordNotFoundException {
		List<IdentifiedEntity> resultSet = null;
		try {
			resultSet = manager.find("from PalavraChaveMO where nome like '%" + nome +"%' "
					+ "order by id, nome");

			if(resultSet == null) {
				throw new KeywordNotFoundException ("PalavraChave não encontrada.");
			}
			else return resultSet;

		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
	}

	public List<IdentifiedEntity> findPalavraChaveById(int id) throws  UnreachableDataBaseException, KeywordNotFoundException  {
		List<IdentifiedEntity> resultSet = null;
		try {
			resultSet = manager.find("from PalavraChaveMO where id =" + id +"order by id, nome");
			if(resultSet == null) {
				throw new KeywordNotFoundException ("Id de PalavraChave não encontrado.");
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
			resultSet = manager.find("from PalavraChaveMO order by nome");
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


