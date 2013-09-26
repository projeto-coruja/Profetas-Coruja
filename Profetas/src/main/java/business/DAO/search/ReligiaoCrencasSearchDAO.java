package business.DAO.search;

import java.util.List;

import persistence.EntityManager;
import persistence.model.IdentifiedEntity;
import persistence.model.ReligiaoCrencas;
import persistence.util.DataAccessLayerException;
import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.model.ReligionNotFoundException;
import business.exceptions.search.PersonagemNotFoundException;

public class ReligiaoCrencasSearchDAO {
	private EntityManager manager;

	public ReligiaoCrencasSearchDAO(){
		manager= new EntityManager();
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
	public ReligiaoCrencas findExactReligiaoCrencas(String nome) throws ReligionNotFoundException, UnreachableDataBaseException{
		nome = getQueryNormalization(nome);
		List<IdentifiedEntity> resultSet = null;
		try {
			resultSet = manager.find("FROM ReligiaoCrencas"+		
					" where nome = "+nome+" "+
					" ORDER BY nome ");

			if(resultSet == null) {
				throw new ReligionNotFoundException ("Religiao não encontrada.");
			}
			else{

				return (ReligiaoCrencas) resultSet.get(0);
			}
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}

	}

	/**
	 * Pesquisa por uma Religiao/Crenca exata por Id
	 * @param nome - nome da Religiao.
	 * @throws UnreachableDataBaseException
	 * @throws PersonagemNotFoundException
	 */
	public ReligiaoCrencas findExactReligiaoCrencaById(int id) throws ReligionNotFoundException, UnreachableDataBaseException{

		List<IdentifiedEntity> resultSet = null;
		try {
			resultSet = manager.find("FROM ReligiaoCrencas"+		
					" where id = "+id+" "+
					" ORDER BY nome ");

			if(resultSet == null) {
				throw new ReligionNotFoundException ("Religiao não encontrada.");
			}
			else{

				return (ReligiaoCrencas) resultSet.get(0);
			}
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}

	}
	/**
	 * Pesquisa por uma "parcela" do nome da Religiao 
	 * @param nome - nome da religiao.
	 * @throws UnreachableDataBaseException
	 * @throws PersonagemNotFoundException
	 */
	public List<IdentifiedEntity> findReligiaoCrencaByNome(String nome) throws  UnreachableDataBaseException, ReligionNotFoundException {
		List<IdentifiedEntity> resultSet = null;
		try {
			resultSet = manager.find("from religiaocrencasmo where nome like '%" + nome +"%' "
					+ "order by anoIngresso");

			if(resultSet == null) {
				throw new ReligionNotFoundException ("Religiao não encontrada.");
			}
			else return resultSet;

		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
	}
	/**
	 * Pesquisa por uma "parcela" da descricao da Religiao/Crenca
	 * @param descricao - descricao do grupo movimento.
	 * @throws UnreachableDataBaseException
	 * @throws PersonagemNotFoundException
	 */
	public List<IdentifiedEntity> findReligiaoCrencaByDescricao(String descricao) throws  UnreachableDataBaseException, ReligionNotFoundException {
		List<IdentifiedEntity> resultSet = null;
		try {
			resultSet = manager.find("from religiaocrencasmo where descricao like '%" + descricao +"%' "
					+ "order by descricao");
			
			if(resultSet == null) {
				throw new ReligionNotFoundException ("Religiao/Crenca não encontrada por descricao.");
			}
			else return resultSet;
		
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
	}
	

	/**
	 * Pesquisa todas as Religioes/crencas
	 * @throws UnreachableDataBaseException
	 * @throws PersonagemNotFoundException
	 */
	public List<IdentifiedEntity> findAllReligiaoCrenca() throws  UnreachableDataBaseException, ReligionNotFoundException  {
		List<IdentifiedEntity> resultSet = null;
		try {
			resultSet = manager.find("from religiaocrencamo order by nome");
			if(resultSet == null) {
				throw new ReligionNotFoundException("Não existe nenhuma Religiao Crenca cadastrada.");
			}
			else return resultSet;
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
	}








}
