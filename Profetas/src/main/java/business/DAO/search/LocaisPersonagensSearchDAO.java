package business.DAO.search;

import java.util.List;

import datatype.SimpleDate;
import persistence.EntityManager;
import persistence.model.IdentifiedEntity;
import persistence.model.LocaisPersonagens;
import persistence.util.DataAccessLayerException;
import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.model.LocalsCharactersNotFoundException;



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
	public LocaisPersonagens findExactLocaisPersonagensByAnoChegada(SimpleDate anoChegada) throws LocalsCharactersNotFoundException, UnreachableDataBaseException{
		//nome = getQueryNormalization(nome);
		List<IdentifiedEntity> resultSet = null;
		try {
			
			resultSet = manager.find("FROM locaispersonagens WHERE anochegada = '"+ anoChegada +"'"
					+ " ORDER BY id");
			
			if(resultSet == null) {
				throw new  LocalsCharactersNotFoundException ("Local Personagem não encontrado.");
			}
			else{
				
				return  (LocaisPersonagens) resultSet.get(0);
			}
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
		
	}
	/**
	 * Pesquisa por um LocaisPersonagens usando o Ano Saida
	 * @param anoChegada - ano de saida do locais personagens.
	 * @throws UnreachableDataBaseException
	 * @throws LocalsCharactersNotFoundException
	 */
	public LocaisPersonagens findLocaisPersonagensByAnoSaida(SimpleDate anoSaida) throws LocalsCharactersNotFoundException, UnreachableDataBaseException{
		//nome = getQueryNormalization(nome);
		List<IdentifiedEntity> resultSet = null;
		try {
			
			resultSet = manager.find("FROM locaispersonagens WHERE anosaida = '"+ anoSaida +"'"
					+ " ORDER BY id");
			
			if(resultSet == null) {
				throw new  LocalsCharactersNotFoundException ("Local Personagem não encontrado.");
			}
			else{
				
				return  (LocaisPersonagens) resultSet.get(0);
			}
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
		
	}
	/**
	 * Pesquisa por um LocaisPersonagens usando o local
	 * @param idLocal -id do local
	 * @throws UnreachableDataBaseException
	 * @throws LocalsCharactersNotFoundException
	 */
	public LocaisPersonagens findLocaisPersonagensByLocal(long idLocal) throws LocalsCharactersNotFoundException, UnreachableDataBaseException{
		//nome = getQueryNormalization(nome);
		List<IdentifiedEntity> resultSet = null;
		try {
			
			resultSet = manager.find("FROM locaispersonagens WHERE local_id = '"+ idLocal +"'"
					+ " ORDER BY id");
			
			if(resultSet == null) {
				throw new  LocalsCharactersNotFoundException ("Local Personagem não encontrado.");
			}
			else{
				
				return  (LocaisPersonagens) resultSet.get(0);
			}
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
		
	}

}
