package business.dao.search;

import java.util.List;

import persistence.EntityManager;
import persistence.model.PalavraChave;
import persistence.model.exceptions.KeywordNotFoundException;
import persistence.util.DataAccessLayerException;
import business.exceptions.login.UnreachableDataBaseException;

public class PalavraChaveSearchDAO {

    private EntityManager manager;

    public PalavraChaveSearchDAO() {
	manager = new EntityManager();
    }

    public PalavraChave findExactPalavraChave(String nome) throws UnreachableDataBaseException, KeywordNotFoundException {
	try {
	    PalavraChave result = manager.findByNaturalId(PalavraChave.class, nome);
	    if (result == null) {
		throw new KeywordNotFoundException("PalavraChave não encontrada.");
	    } else {
		return result;
	    }
	} catch (DataAccessLayerException e) {
	    e.printStackTrace();
	    throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
	}
    }

    public List<PalavraChave> findPalavraChaveByNome(String nome) throws UnreachableDataBaseException, KeywordNotFoundException {
	try {
	    List<PalavraChave> resultSet = manager
		    .find("from PalavraChave where palavrachave like '%" + nome + "%' "
			    + "order by palavrachave");
	    if (resultSet.isEmpty()) {
		throw new KeywordNotFoundException("PalavraChave não encontrada.");
	    } else {
		return resultSet;
	    }
	} catch (DataAccessLayerException e) {
	    e.printStackTrace();
	    throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
	}
    }

    public List<PalavraChave> findAllPalavraChave() throws UnreachableDataBaseException, KeywordNotFoundException {
	try {
	    List<PalavraChave> resultSet = manager.find("from PalavraChave order by nome");
	    if (resultSet.isEmpty()) {
		throw new KeywordNotFoundException("Não existe nenhuma PalavraChave cadastrada.");
	    } else {
		return resultSet;
	    }
	} catch (DataAccessLayerException e) {
	    e.printStackTrace();
	    throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
	}
    }

}
