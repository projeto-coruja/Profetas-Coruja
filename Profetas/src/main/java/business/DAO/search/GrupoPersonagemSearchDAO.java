package business.DAO.search;

import java.util.List;

import persistence.PersistenceAccess;
import persistence.dto.DTO;
import persistence.dto.GrupoPersonagem;
import persistence.util.DataAccessLayerException;
import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.model.GroupCharacterNotFoundException;
import business.exceptions.search.PersonagemNotFoundException;

public class GrupoPersonagemSearchDAO {
	
	private PersistenceAccess manager;
	
	public GrupoPersonagemSearchDAO(){
		manager= new PersistenceAccess();
	}
	private String getQueryNormalization(String var){
		var.replace("'", "\'");
				
		return "LOWER(TRANSLATE("+var+",'áàãâäÁÀÃÂÄéèêëÉÈÊËíìîïÍÌÎÏóòõôöÓÒÕÔÖúùûüÚÙÛÜñÑçÇÿýÝ','aaaaaAAAAAeeeeEEEEiiiiIIIIoooooOOOOOuuuuUUUUnNcCyyY'))";
	}
	/**
	 * Pesquisa por um  grupo personagem
	 * @param nome - nome do grupo personagem.
	 * @throws UnreachableDataBaseException
	 * @throws PersonagemNotFoundException
	 */
	public GrupoPersonagem findExactGrupoPersonagem(String nome) throws GroupCharacterNotFoundException, UnreachableDataBaseException{
		nome = getQueryNormalization(nome);
		List<DTO> resultSet = null;
		try {
			resultSet = manager.findEntity("FROM GrupoPersonagemMO"+		
					" where nome = "+nome+" "+
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
	 * Pesquisa por um  grupo personagem
	 * @param nome - nome do grupo personagem.
	 * @throws UnreachableDataBaseException
	 * @throws PersonagemNotFoundException
	 */
	public GrupoPersonagem findExactGrupoPersonagem(int id) throws GroupCharacterNotFoundException, UnreachableDataBaseException{
		
		List<DTO> resultSet = null;
		try {
			resultSet = manager.findEntity("FROM GrupoPersonagemMO"+		
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
	 * Pesquisa por uma "parcela" do nome do Grupo personagem 
	 * @param nome - nome do personagem.
	 * @throws UnreachableDataBaseException
	 * @throws PersonagemNotFoundException
	 */
	public List<DTO> findGrupoPersonagem(String nome) throws  UnreachableDataBaseException, GroupCharacterNotFoundException {
		List<DTO> resultSet = null;
		try {
			resultSet = manager.findEntity("from GrupoPersonagemMO where nome like '%" + nome +"%' "
					+ "order by anoIngresso");
			
			if(resultSet == null) {
				throw new GroupCharacterNotFoundException ("Grupo Personagem não encontrado.");
			}
			else return resultSet;
		
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
	public List<DTO> findAllGrupoPersonagem() throws  UnreachableDataBaseException, GroupCharacterNotFoundException  {
		List<DTO> resultSet = null;
		try {
			resultSet = manager.findEntity("from GrupoPersonagemMO order by nome");
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
