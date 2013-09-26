package business.DAO.search;

import java.util.ArrayList;
import java.util.List;

import datatype.SimpleDate;
import persistence.EntityManager;
import persistence.model.IdentifiedEntity;
import persistence.model.GrupoMovimento;
import persistence.util.DataAccessLayerException;
import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.model.GroupMovementNotFoundException;
import business.exceptions.model.LocalNotFoundException;

public class GrupoMovimentoSearchDAO {

	private EntityManager manager;

	public GrupoMovimentoSearchDAO(){
		manager= new EntityManager();
	}
	private String getQueryNormalization(String var){
		var.replaceAll("'", "''");

		return "LOWER(TRANSLATE("+var+",'áàãâäÁÀÃÂÄéèêëÉÈÊËíìîïÍÌÎÏóòõôöÓÒÕÔÖúùûüÚÙÛÜñÑçÇÿýÝ','aaaaaAAAAAeeeeEEEEiiiiIIIIoooooOOOOOuuuuUUUUnNcCyyY'))";
	}
	
	public List<IdentifiedEntity> findGrupoMovimentoGeneric(String busca) throws GroupMovementNotFoundException{
		
		List<IdentifiedEntity>resultSet;
		resultSet = manager.find("FROM GrupoMovimento WHERE nome like "+ getQueryNormalization("'%"+ busca +"%'") 
				+ "' OR descricao like " + getQueryNormalization("'%"+ busca +"%'") 
				+ " ORDER BY nome");
		if(resultSet == null) {
			throw new GroupMovementNotFoundException ("Grupo  Movimento não encontrado.");
		}
		else{

			return  (List<IdentifiedEntity>) resultSet;
		}
		
	}
	public List<IdentifiedEntity> mainSearchAND(String nome, SimpleDate anoinicio, SimpleDate anofim, String descricao, List<IdentifiedEntity> local) throws GroupMovementNotFoundException, UnreachableDataBaseException, LocalNotFoundException{
		List<IdentifiedEntity> resultSetGrupos = null;
		List<IdentifiedEntity> resultSet = null;

		try {
		


			resultSetGrupos = manager.find("FROM GrupoMovimento WHERE nome like "+ getQueryNormalization("'%"+ nome +"%'") 
					+ "AND anoinicio  = '"+ anoinicio + "' AND anofim = '"+ anofim + "' AND descricao like " + getQueryNormalization("'%"+ descricao +"%'") 
					+ " ORDER BY nome");
			if(local != null){
				for(IdentifiedEntity l : local){
					if(resultSetGrupos!= null){
						for(IdentifiedEntity g :resultSetGrupos){
							resultSet = manager.find("FROM GrupoMovimentoMO_Local WHERE grupomovimentomo_id = "+g.getId()+" AND local_id = " + l.getId()+"");
						}
					}
				}
			}


			if(resultSet == null) {
				throw new GroupMovementNotFoundException ("Grupo  Movimento não encontrado.");
			}
			else{

				return  (List<IdentifiedEntity>) resultSet;
			}
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}


	}


	public List<IdentifiedEntity> findGrupoMovimentoByAll(String nome, SimpleDate anoinicio, SimpleDate anofim, String descricao, String local_grupomovimento,
			double latitude_grupomovimento, double longitude_grupomovimento) throws GroupMovementNotFoundException, UnreachableDataBaseException, LocalNotFoundException{
		List<IdentifiedEntity> resultSetGrupos = null;
		List<IdentifiedEntity> resultSet = null;

		LocalSearchDAO dao = new LocalSearchDAO();
		try {
			List<IdentifiedEntity> resultados = (List<IdentifiedEntity>) dao.findLocalByAll(local_grupomovimento, latitude_grupomovimento, longitude_grupomovimento);


			resultSetGrupos = manager.find("FROM GrupoMovimento WHERE nome like "+ getQueryNormalization("'%"+ nome +"%'") 
					+ "AND anoinicio  = '"+ anoinicio + "' AND anofim = '"+ anofim + "' AND descricao like " + getQueryNormalization("'%"+ descricao +"%'") 
					+ " ORDER BY nome");
			if(resultados != null){
				for(IdentifiedEntity l : resultados){
					if(resultSetGrupos != null){
						for(IdentifiedEntity g : resultSetGrupos){
							resultSet = manager.find("FROM GrupoMovimentoMO_Local WHERE grupomovimentomo_id = "+g.getId()+" AND local_id = " + l.getId()+"");
						}
					}
				}
			}


			if(resultSet == null) {
				//throw new GroupMovementNotFoundException ("Grupo  Movimento não encontrado.");
				return new ArrayList<IdentifiedEntity>();
			}
			else{

				return  (List<IdentifiedEntity>) resultSet;
			}
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}


	}

	/**
	 * Pesquisa por um EXATO grupo movimento procurando pelo "nome"
	 * @param nome - nome do grupo personagem.
	 * @throws UnreachableDataBaseException
	 * @throws PersonagemNotFoundException
	 */
	public GrupoMovimento findExactGrupoMovimentoByNome(String nome) throws GroupMovementNotFoundException, UnreachableDataBaseException{
		List<IdentifiedEntity> resultSet = null;
		try {

			resultSet = manager.find("FROM GrupoMovimento WHERE nome = "+ getQueryNormalization("'"+ nome +"'") 
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

		List<IdentifiedEntity> resultSet = null;
		try {
			resultSet = manager.find("FROM GrupoMovimento"+		
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
	public List<IdentifiedEntity> findExactGrupoMovimentoByAnoInicio(SimpleDate anoInicio) throws GroupMovementNotFoundException, UnreachableDataBaseException{

		List<IdentifiedEntity> resultSet = null;
		try {
			resultSet = manager.find("FROM GrupoMovimento"+		
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
	public List<IdentifiedEntity> findExactGrupoMovimentoByAnoFim(SimpleDate anofim) throws GroupMovementNotFoundException, UnreachableDataBaseException{

		List<IdentifiedEntity> resultSet = null;
		try {
			resultSet = manager.find("FROM GrupoMovimento"+		
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
	public List<IdentifiedEntity> findGrupoMovimento(String nome) throws  UnreachableDataBaseException,GroupMovementNotFoundException {
		List<IdentifiedEntity> resultSet = null;
		try {
			resultSet = manager.find("from GrupoMovimentoMO where nome like '%" + nome +"%' "
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
	public List<IdentifiedEntity> findGrupoMovimentoByDescricao(String descricao) throws  UnreachableDataBaseException,GroupMovementNotFoundException {
		List<IdentifiedEntity> resultSet = null;
		try {
			resultSet = manager.find("from GrupoMovimentoMO where descricao like '%" + descricao +"%' "
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
	public List<IdentifiedEntity> findAllGrupoMovimento() throws  UnreachableDataBaseException, GroupMovementNotFoundException  {
		List<IdentifiedEntity> resultSet = null;
		try {
			resultSet = manager.find("from GrupoMovimentoMO order by nome");
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
