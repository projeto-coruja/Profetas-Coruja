//Tratar correspondencia! Pesquisas nao funcionam!!!!!
package business.dao.search;

import static business.dao.search.DAOUtility.queryList;

import java.util.List;

import persistence.EntityManager;
import persistence.model.Correspondencia;
import persistence.model.Local;
import persistence.model.Personagem;
import persistence.model.exceptions.CorrespondenceNotFoundException;
import business.exceptions.login.UnreachableDataBaseException;
import datatype.SimpleDate;

public class CorrespondenciaSearchDAO {

    private EntityManager manager;

    public CorrespondenciaSearchDAO() {
	manager = new EntityManager();
    }

    /**
     * Pesquisa por correspondências usando o remetente
     * 
     * @param id
     *            - id do rementente
     * @throws UnreachableDataBaseException
     * @throws CorrespondenceNotFoundException
     */
    public List<Correspondencia> findCorrespondenciaByRemetente(Long id) throws UnreachableDataBaseException, CorrespondenceNotFoundException {
	return executeQuery("remetente", id);
    }

    /**
     * Pesquisa por correspondências usando o remetente
     * 
     * @param p
     *            - rementente
     * @throws UnreachableDataBaseException
     * @throws CorrespondenceNotFoundException
     */
    public List<Correspondencia> findCorrespondenciaByRemetente(Personagem p) throws UnreachableDataBaseException, CorrespondenceNotFoundException {
	return executeQuery("remetente", p.getId());
    }

    /**
     * Pesquisa por uma correspondencia usando o destinatario
     * 
     * @param id
     *            - id do destinatario
     * @throws UnreachableDataBaseException
     * @throws CorrespondenceNotFoundException
     */
    public List<Correspondencia> findCorrespondenciaByDestinatario(Long id) throws UnreachableDataBaseException, CorrespondenceNotFoundException {
	return executeQuery("destinatario", id);
    }

    /**
     * Pesquisa por uma correspondencia usando o destinatario
     * 
     * @param p
     *            - destinatario
     * @throws UnreachableDataBaseException
     * @throws CorrespondenceNotFoundException
     */
    public List<Correspondencia> findCorrespondenciaByDestinatario(Personagem p) throws UnreachableDataBaseException, CorrespondenceNotFoundException {
	return executeQuery("destinatario", p.getId());
    }

    /**
     * Pesquisa por uma correspondencia usando o local
     * 
     * @param id
     *            - id do local
     * @throws UnreachableDataBaseException
     * @throws CorrespondenceNotFoundException
     */
    public List<Correspondencia> findCorrespondenciaByLocal(long id) throws UnreachableDataBaseException, CorrespondenceNotFoundException {
	return executeQuery("local", id);
    }

    /**
     * Pesquisa por uma correspondencia usando o local
     * 
     * @param l
     *            - local
     * @throws UnreachableDataBaseException
     * @throws CorrespondenceNotFoundException
     */
    public List<Correspondencia> findCorrespondenciaByLocal(Local l) throws UnreachableDataBaseException, CorrespondenceNotFoundException {
	return executeQuery("local", l.getId());
    }

    /**
     * Pesquisa por uma correspondencia usando a data
     * 
     * @param id
     *            - id do local
     * @throws UnreachableDataBaseException
     * @throws CorrespondenceNotFoundException
     */
    public List<Correspondencia> findCorrespondenciaByData(SimpleDate data) throws UnreachableDataBaseException, CorrespondenceNotFoundException {
	return executeQuery("data", data);
    }

    private List<Correspondencia> executeQuery(String field, Object info) throws CorrespondenceNotFoundException, UnreachableDataBaseException {
	List<Correspondencia> resultSet = queryList(manager, "correspondencia", field, info);
	if (resultSet.isEmpty()) {
	    throw new CorrespondenceNotFoundException("Correspondencia não encontrada.");
	} else {
	    return resultSet;
	}
    }
}
