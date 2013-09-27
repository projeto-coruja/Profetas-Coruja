package business.DAO.search;

import static business.DAO.search.DAOUtility.queryList;

import java.util.List;

import persistence.EntityManager;
import persistence.model.LocaisPersonagens;
import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.model.LocalsCharactersNotFoundException;
import datatype.SimpleDate;

public class LocaisPersonagensSearchDAO {
	
	private EntityManager manager;

	public LocaisPersonagensSearchDAO(){
		manager = new EntityManager();
	}
	
	/**
	 * Pesquisa por um LocaisPersonagens usando o Ano Chegada
	 * @param anoChegada - ano de chegada no locais personagens.
	 * @throws UnreachableDataBaseException
	 * @throws LocalsCharactersNotFoundException
	 */
	public List<LocaisPersonagens> findExactLocaisPersonagensByAnoChegada(SimpleDate anoChegada) throws LocalsCharactersNotFoundException, UnreachableDataBaseException{
		return executeQuery("anochegada", anoChegada);
	}
	
	/**
	 * Pesquisa por um LocaisPersonagens usando o Ano Saida
	 * @param anoChegada - ano de saida do locais personagens.
	 * @throws UnreachableDataBaseException
	 * @throws LocalsCharactersNotFoundException
	 */
	public List<LocaisPersonagens> findLocaisPersonagensByAnoSaida(SimpleDate anoSaida) throws LocalsCharactersNotFoundException, UnreachableDataBaseException{
		return executeQuery("anosaida", anoSaida);
	}
	
	/**
	 * Pesquisa por um LocaisPersonagens usando o local
	 * @param idLocal -id do local
	 * @throws UnreachableDataBaseException
	 * @throws LocalsCharactersNotFoundException
	 */
	public List<LocaisPersonagens> findLocaisPersonagensByLocal(long id) throws LocalsCharactersNotFoundException, UnreachableDataBaseException{
		return executeQuery("local", id);
	}
	
	private List<LocaisPersonagens> executeQuery(String field, Object info) throws LocalsCharactersNotFoundException, UnreachableDataBaseException {
		List<LocaisPersonagens> resultSet = queryList(manager, "locaispersonagens", field, info);
		if(resultSet.isEmpty()) {
			throw new  LocalsCharactersNotFoundException ("Local Personagem não encontrado.");
		}
		else return resultSet;
	}

}
