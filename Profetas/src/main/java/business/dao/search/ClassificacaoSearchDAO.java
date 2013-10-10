package business.dao.search;

import java.util.List;

import persistence.EntityManager;
import persistence.model.Classificacao;
import persistence.util.DataAccessLayerException;
import business.exceptions.login.UnreachableDataBaseException;

public class ClassificacaoSearchDAO {

    private EntityManager manager;

    public ClassificacaoSearchDAO() {
	manager = new EntityManager();
    }

    public Classificacao findExactClassificacao(String tipo) throws UnreachableDataBaseException {
	try {
	    return manager.find(Classificacao.class, tipo);
	} catch (DataAccessLayerException e) {
	    e.printStackTrace();
	    throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
	}
    }

    public List<Classificacao> findAllClassificacao() throws UnreachableDataBaseException {
	try {
	    return manager.find("from Classificacao order by tipo");
	} catch (DataAccessLayerException e) {
	    e.printStackTrace();
	    throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
	}
    }

}
