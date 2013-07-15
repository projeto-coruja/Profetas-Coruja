package business.DAO.search;

import java.util.List;

import datatype.SimpleDate;

import persistence.PersistenceAccess;
import persistence.dto.DTO;
import persistence.dto.GrupoMovimento;
import persistence.util.DataAccessLayerException;
import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.model.GroupMovementNotFoundException;

public class GrupoMovimentoSearchDAO {
	
	private PersistenceAccess manager;
	
	public GrupoMovimentoSearchDAO(){
		manager= new PersistenceAccess();
	}
	/**private String getQueryNormalization(String var){
		var.replace("'", "\'");
				
		return "LOWER(TRANSLATE("+var+",'áàãâäÁÀÃÂÄéèêëÉÈÊËíìîïÍÌÎÏóòõôöÓÒÕÔÖúùûüÚÙÛÜñÑçÇÿýÝ','aaaaaAAAAAeeeeEEEEiiiiIIIIoooooOOOOOuuuuUUUUnNcCyyY'))";
	}**/
	/**
	 * Pesquisa por um EXATO grupo movimento procurando pelo "nome"
	 * @param nome - nome do grupo personagem.
	 * @throws UnreachableDataBaseException
	 * @throws PersonagemNotFoundException
	 */
	public GrupoMovimento findExactGrupoMovimentoByNome(String nome) throws GroupMovementNotFoundException, UnreachableDataBaseException{
		//nome = getQueryNormalization(nome);
		List<DTO> resultSet = null;
		try {
			
			resultSet = manager.findEntity("FROM GrupoMovimentoMO WHERE nome = '"+ nome +"'"
					+ " ORDER BY nome");
			
			if(resultSet == null) {
				throw new GroupMovementNotFoundException ("Grupo  Movimento não encontrado.");
			}
			else{
				
				return  (GrupoMovimento) resultSet.get(0);
			}
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
		
	}
	
	/**
	 * Pesquisa por um  exato grupo movimento pesquisando por "id"
	 * @param id - id do grupo personagem.
	 * @throws UnreachableDataBaseException
	 * @throws GroupMovementNotFoundException
	 */
	public GrupoMovimento findExactGrupoMovimentoById(int id) throws GroupMovementNotFoundException, UnreachableDataBaseException{
		
		List<DTO> resultSet = null;
		try {
			resultSet = manager.findEntity("FROM GrupoMovimentoMO"+		
					" where id = "+id+" "+
					" ORDER BY nome ");
			
			if(resultSet == null) {
				throw new GroupMovementNotFoundException ("Grupo  Movimento não encontrado.");
			}
			else{
				
				return (GrupoMovimento) resultSet.get(0);
			}
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
		
	}
	/**
	 * Pesquisa por um EXATO grupo movimento procurando pelo "anoinicio"
	 * @param nome - nome do grupo movimento.
	 * @throws UnreachableDataBaseException
	 * @throws PersonagemNotFoundException
	 */
	public List<DTO> findExactGrupoMovimentoByAnoInicio(SimpleDate anoInicio) throws GroupMovementNotFoundException, UnreachableDataBaseException{
		
		List<DTO> resultSet = null;
		try {
			resultSet = manager.findEntity("FROM GrupoMovimentoMO"+		
					" where anoinicio = "+anoInicio+" "+
					" ORDER BY nome ");
			
			if(resultSet == null) {
				throw new GroupMovementNotFoundException ("Grupo  Movimento não encontrado.");
			}
			else{
				
				return resultSet;
			}
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
		
	}
	/**
	 * Pesquisa por um EXATO grupo movimento procurando pelo "anofim"
	 * @param nome - nome do grupo movimento.
	 * @throws UnreachableDataBaseException
	 * @throws PersonagemNotFoundException
	 */
	public List<DTO> findExactGrupoMovimentoByAnoFim(SimpleDate anofim) throws GroupMovementNotFoundException, UnreachableDataBaseException{
		
		List<DTO> resultSet = null;
		try {
			resultSet = manager.findEntity("FROM GrupoMovimentoMO"+		
					" where anoinicio = "+anofim+" "+
					" ORDER BY nome ");
			
			if(resultSet == null) {
				throw new GroupMovementNotFoundException ("Grupo  Movimento não encontrado.");
			}
			else{
				
				return resultSet;
			}
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
		
	}
	/**
	 * Pesquisa por uma "parcela" do nome do Grupo movimento
	 * @param nome - nome do grupo movimento.
	 * @throws UnreachableDataBaseException
	 * @throws PersonagemNotFoundException
	 */
	public List<DTO> findGrupoMovimento(String nome) throws  UnreachableDataBaseException,GroupMovementNotFoundException {
		List<DTO> resultSet = null;
		try {
			resultSet = manager.findEntity("from GrupoMovimentoMO where nome like '%" + nome +"%' "
					+ "order by nome");
			
			if(resultSet == null) {
				throw new GroupMovementNotFoundException ("Grupo Movimento não encontrado.");
			}
			else return resultSet;
		
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
	}
	/**
	 * Pesquisa por uma "parcela" da descricao do Grupo movimento 
	 * @param descricao - descricao do grupo movimento.
	 * @throws UnreachableDataBaseException
	 * @throws PersonagemNotFoundException
	 */
	public List<DTO> findGrupoMovimentoByDescricao(String descricao) throws  UnreachableDataBaseException,GroupMovementNotFoundException {
		List<DTO> resultSet = null;
		try {
			resultSet = manager.findEntity("from GrupoMovimentoMO where descricao like '%" + descricao +"%' "
					+ "order by descricao");
			
			if(resultSet == null) {
				throw new GroupMovementNotFoundException ("Grupo Movimento não encontrado por descricao.");
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
	public List<DTO> findAllGrupoMovimento() throws  UnreachableDataBaseException, GroupMovementNotFoundException  {
		List<DTO> resultSet = null;
		try {
			resultSet = manager.findEntity("from GrupoMovimentoMO order by nome");
			if(resultSet == null) {
				throw new GroupMovementNotFoundException("Não existe nenhum GrupoMovimento cadastrado.");
			}
			else return resultSet;
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
	}
	

}
