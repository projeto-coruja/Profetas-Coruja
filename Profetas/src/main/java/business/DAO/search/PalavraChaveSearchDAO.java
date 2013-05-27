package business.DAO.search;

import java.util.List;

import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.model.KeywordNotFoundException;

import persistence.PersistenceAccess;
import persistence.dto.DTO;
import persistence.dto.PalavraChave;
import persistence.util.DataAccessLayerException;

public class PalavraChaveSearchDAO {



	private PersistenceAccess manager;

	public PalavraChaveSearchDAO() {
		manager = new PersistenceAccess();
	}



	public  PalavraChave findExactPalavraChave(String nome) 
			throws  UnreachableDataBaseException, KeywordNotFoundException  {

		List<DTO> resultSet = null;
		try {
			resultSet = manager.findEntity("FROM PalavraChaveMO WHERE tipo = '"+ nome +"'"
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

	public List<DTO> findPalavraChaveByNome(String nome) throws  UnreachableDataBaseException, KeywordNotFoundException {
		List<DTO> resultSet = null;
		try {
			resultSet = manager.findEntity("from PalavraChaveMO where nome like '%" + nome +"%' "
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

	public List<DTO> findPalavraChaveById(int id) throws  UnreachableDataBaseException, KeywordNotFoundException  {
		List<DTO> resultSet = null;
		try {
			resultSet = manager.findEntity("from PalavraChaveMO where id =" + id +"order by id, nome");
			if(resultSet == null) {
				throw new KeywordNotFoundException ("Id de PalavraChave não encontrado.");
			}
			else return resultSet;
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
	}

	public List<DTO> findAllPalavraChave() throws  UnreachableDataBaseException, KeywordNotFoundException  {
		List<DTO> resultSet = null;
		try {
			resultSet = manager.findEntity("from PalavraChaveMO order by nome");
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


