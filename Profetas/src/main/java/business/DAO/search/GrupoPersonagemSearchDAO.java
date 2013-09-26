package business.DAO.search;

import java.util.List;

import datatype.SimpleDate;
import persistence.EntityManager;
import persistence.model.EntityModel;
import persistence.model.GrupoPersonagem;
import persistence.util.DataAccessLayerException;
import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.model.GroupCharacterNotFoundException;
import business.exceptions.search.PersonagemNotFoundException;

public class GrupoPersonagemSearchDAO {
	
	private EntityManager manager;
	
	public GrupoPersonagemSearchDAO(){
		manager= new EntityManager();
	}
	private String getQueryNormalization(String var){
		var.replace("'", "\'");
				
		return "LOWER(TRANSLATE("+var+",'áàãâäÁÀÃÂÄéèêëÉÈÊËíìîïÍÌÎÏóòõôöÓÒÕÔÖúùûüÚÙÛÜñÑçÇÿýÝ','aaaaaAAAAAeeeeEEEEiiiiIIIIoooooOOOOOuuuuUUUUnNcCyyY'))";
	}
	/**
	 * Pesquisa por um  grupo personagem
	 * @param anoIngresso - ano de ingresso do grupo personagem.
	 * @throws UnreachableDataBaseException
	 * @throws PersonagemNotFoundException
	 */
	public GrupoPersonagem findExactGrupoByAnoIngresso(SimpleDate anoIngresso) throws GroupCharacterNotFoundException, UnreachableDataBaseException{
	
		List<EntityModel> resultSet = null;
		try {
			resultSet = manager.find("FROM GrupoPersonagem"+		
					" where anoingresso = '"+ anoIngresso+ "'"+
					" ORDER BY anoingresso ");
			
			if(resultSet == null) {
				throw new GroupCharacterNotFoundException ("Grupo  de  Personagem não encontrado.");
			}
			else{
				
				return (GrupoPersonagem) resultSet.get(0);
			}
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
		
	}
	
	/**
	 * Pesquisa por um  grupo personagem
	 * @param id - id do grupo personagem.
	 * @throws UnreachableDataBaseException
	 * @throws PersonagemNotFoundException
	 */
	public GrupoPersonagem findExactGrupoPersonagem(int id) throws GroupCharacterNotFoundException, UnreachableDataBaseException{
		
		List<EntityModel> resultSet = null;
		try {
			resultSet = manager.find("FROM GrupoPersonagem"+		
					" where id = "+id+" "+
					" ORDER BY nome ");
			
			if(resultSet == null) {
				throw new GroupCharacterNotFoundException ("Grupo  de  Personagem não encontrado.");
			}
			else{
				
				return (GrupoPersonagem) resultSet.get(0);
			}
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
	public List<EntityModel> findAllGrupoPersonagem() throws  UnreachableDataBaseException, GroupCharacterNotFoundException  {
		List<EntityModel> resultSet = null;
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
	
	
	
		
	

}
