package business.dao.search;

import static business.dao.search.DAOUtility.queryList;

import java.util.List;

import persistence.EntityManager;
import persistence.model.Encontro;
import persistence.model.Local;
import persistence.model.exceptions.EncounterNotFoundException;
import business.exceptions.login.UnreachableDataBaseException;
import datatype.SimpleDate;

public class EncontroSearchDAO {

    private EntityManager manager;

    public EncontroSearchDAO() {
	manager = new EntityManager();
    }

    /**
     * Pesquisa por Encontros usando a data
     * 
     * @param data
     *            - data do encontro
     * @throws UnreachableDataBaseException
     * @throws EncounterNotFoundException
     */
    public List<Encontro> findEncontroByData(SimpleDate data) throws EncounterNotFoundException, UnreachableDataBaseException {
	return executeQuery("data", data);
    }

    /**
     * Pesquisa por um Encontro usando o local
     * 
     * @param id
     *            - id do local do encontro
     * @throws UnreachableDataBaseException
     * @throws EncounterNotFoundException
     */
    public List<Encontro> findEncontroByLocal(Long id) throws EncounterNotFoundException, UnreachableDataBaseException {
	return executeQuery("local", id);
    }

    /**
     * Pesquisa por um Encontro usando o local
     * 
     * @param l
     *            - local do encontro
     * @throws UnreachableDataBaseException
     * @throws EncounterNotFoundException
     */
    public List<Encontro> findEncontroByLocal(Local l) throws EncounterNotFoundException, UnreachableDataBaseException {
	return executeQuery("local", l.getId());
    }

    private List<Encontro> executeQuery(String field, Object info) throws EncounterNotFoundException, UnreachableDataBaseException {
	List<Encontro> resultSet = queryList(manager, "encontro", field, info);
	if (resultSet.isEmpty()) {
	    throw new EncounterNotFoundException("Encontro não encontrada.");
	} else {
	    return resultSet;
	}
    }

}
