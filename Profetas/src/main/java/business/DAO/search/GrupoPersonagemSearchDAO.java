package business.DAO.search;

import static business.DAO.search.DAOUtility.queryList;

import java.util.List;

import persistence.EntityManager;
import persistence.model.GrupoPersonagem;
import persistence.model.IdentifiedEntity;
import persistence.util.DataAccessLayerException;
import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.model.GroupCharacterNotFoundException;
import business.exceptions.search.PersonagemNotFoundException;
import datatype.SimpleDate;

public class GrupoPersonagemSearchDAO {
	
	private EntityManager manager;
	
	public GrupoPersonagemSearchDAO(){
		manager= new EntityManager();
	}

	/**
	 * Pesquisa por um  grupo personagem
	 * @param anoIngresso - ano de ingresso do grupo personagem.
	 * @throws UnreachableDataBaseException
	 * @throws PersonagemNotFoundException
	 */
	public List<GrupoPersonagem> findGrupoByAnoIngresso(SimpleDate anoIngresso) throws GroupCharacterNotFoundException, UnreachableDataBaseException{
		return executeQuery("anoingresso", anoIngresso);
	}
	
	/**
	 * Pesquisa por um  grupo personagem
	 * @param id - id do grupo personagem.
	 * @throws UnreachableDataBaseException
	 * @throws PersonagemNotFoundException
	 */
	public GrupoPersonagem findExactGrupoPersonagem(Long id) throws GroupCharacterNotFoundException, UnreachableDataBaseException{
		try {
			GrupoPersonagem result = manager.find(GrupoPersonagem.class, id);
			if(result == null) {
				throw new GroupCharacterNotFoundException ("Grupo  de  Personagem não encontrado.");
			}
			else return result;
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
		
	}
	
	/**
	 * Pesquisa todos os Grupos personagens 
	 * @throws UnreachableDataBaseException
	 * @throws PersonagemNotFoundException
	 */
	public List<IdentifiedEntity> findAllGrupoPersonagem() throws  UnreachableDataBaseException, GroupCharacterNotFoundException  {
		List<IdentifiedEntity> resultSet = null;
		try {
			resultSet = manager.find("from GrupoPersonagemMO order by nome");
			if(resultSet == null) {
				throw new GroupCharacterNotFoundException("Não existe nenhum GrupoPersonagem cadastrado.");
			}
			else return resultSet;
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
	}
	
	private List<GrupoPersonagem> executeQuery(String field, Object info) throws GroupCharacterNotFoundException, UnreachableDataBaseException {
		List<GrupoPersonagem> resultSet = queryList(manager, "grupopersonagem", field, info);
		if(resultSet.isEmpty()) {
			throw new  GroupCharacterNotFoundException("Grupo de Personagem não encontrado.");
		}
		else return resultSet;
	}

}
